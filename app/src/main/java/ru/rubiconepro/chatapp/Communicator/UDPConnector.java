package ru.rubiconepro.chatapp.Communicator;

import android.os.AsyncTask;

import java.net.DatagramSocket;


/**
 *    UDP
 *   1 - открывается сокет по 8000
 *   2 - сразу ждет сообщений
 *
 *
 *
 *
 */

//Клиент и сервер для UDP
public class UDPConnector extends AsyncTask<Void, byte[], Void> implements ICommunicator {
    DatagramSocket soc;

    public void SendAll() {

    }


    @Override
    protected Void doInBackground(Void... voids) {
//        //TODO сделать отлов ошибок
//        // Используем DatagramSocket для сервера
//        soc = new DatagramSocket();
//
//        int bufSize = 1024;
//        byte[] buffer = new byte[bufSize];
//        DatagramPacket pac = new DatagramPacket(buffer, bufSize);
//        pac.setPort(8000);
//
//        //TODO сделать прослушивание сокета (добавить адрес, порт (8000))
//        //TODO сделать завершение цикла
//        //Вместо true какая то переменная, которая говорит о завершении
//        while (true/*TODO поставить переменную*/) {
//            soc.receive(pac);
//
//            publishProgress(pac.getData());
//        }


        return null;
    }

    @Override
    protected void onProgressUpdate(byte[]... values) {
        //TODO разобрать сообщение, и отправить его
        //TODO сделать систему подписок
        super.onProgressUpdate(values);
    }


    //*****************IConnector*********************
    @Override
    public void sendMessage(String message) {
//        //TODO отправлять широковещательно
//        DatagramSocket s = new DatagramSocket();
//
//        byte[] b;// = new byte[10];
//        String str = "MyMessage";
//        b = str.getBytes();
//        DatagramPacket p = new DatagramPacket(b,
//                b.length,
//                InetAddress.getByName("255.255.255.255"),
//                8000);
//
//        s.send(p);
    }

    @Override
    public void subscribe(IResiver reciver) {

    }

    @Override
    public void unsubscribe(IResiver reciver) {

    }

    @Override
    public void stopConnector() {
        //TODO отстанавливать полкчение
    }
    //*****************IConnector*********************

}
