package ru.rubiconepro.chatapp.Communicator;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPConnector extends AsyncTask<Void, String, Void> implements ICommunicator {
    InputStream istr;
    OutputStream ostr;
    IResiver iResiver;
    SendTask task = null;

    public  void setDeligate(IResiver iResiver){this.iResiver = iResiver;}
    @Override
    public void sendMessage(final String message) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ostr.write(message.getBytes());
                    ostr.write(new String("\n").getBytes());
                    ostr.flush();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        //ExecutorService
//        if (task == null)
//            task = new SendTask(ostr);

//        new SendTask(ostr).execute(message);

//        try {
//            ostr.write(message.getBytes());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

//        new AsyncTask<String, Void, Void>() {
//            @Override
//            protected Void doInBackground(String... strings) {
//                System.out.println("fdgsdfgdsfg");
//                int a = 4;
//                a = a + 1;
//                for (String s:strings) {
//                    try {
//                        ostr.write(s.getBytes());
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//                return null;
//            }
//        }.execute(message);
    }

    @Override
    public void subscribe(IResiver reciver) {
    }

    @Override
    public void unsubscribe(IResiver reciver) {
    }

    @Override
    public void stopConnector() {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("192.168.30.90", 60000));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            istr = socket.getInputStream();
            ostr = socket.getOutputStream();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        while (true) {
            InputStreamReader inputReader = new InputStreamReader(istr);
            BufferedReader sReader = new BufferedReader(inputReader);

            String str = "";
            try {
                str = sReader.readLine();
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }

            publishProgress(str);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        for (String s: values){
            iResiver.resiveMessage(s);
        }
        //TODO отправить строки в наше приложение
        //TODO создать интерфейс взаимодействия с приложением

        //GET /index.php HTTP/1.0


    }
}
