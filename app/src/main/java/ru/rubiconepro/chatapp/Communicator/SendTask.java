package ru.rubiconepro.chatapp.Communicator;

import android.os.AsyncTask;

import java.io.OutputStream;

public class SendTask extends AsyncTask<String, Void, Void> {

    OutputStream ostr;

    public SendTask(OutputStream ostr) {
        this.ostr = ostr;
    }

    @Override
    protected Void doInBackground(String... strings) {
        System.out.println("test");
        for (String s:strings) {
            try {
                ostr.write(s.getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
