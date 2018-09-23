package ru.rubiconepro.chatapp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;

        import ru.rubiconepro.chatapp.Communicator.IResiver;
        import ru.rubiconepro.chatapp.Communicator.Packeges.TextMessage;
        import ru.rubiconepro.chatapp.Communicator.TCPConnector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IResiver {

    EditText edittext;
    Button   button;
    ListView listview;

    MainAdapter adapter;
    TCPConnector tcpConnector;
    JsonParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonParser = new JsonParser();

        jsonParser.registerType(TextMessage.class);

        edittext = findViewById(R.id.editText);
        button   = findViewById(R.id.button);
        button.setOnClickListener(this);
        listview = findViewById(R.id.listView);

        tcpConnector  = new TCPConnector();
        tcpConnector.setDeligate(this);
        tcpConnector.execute();

        adapter = new MainAdapter(this);
        listview.setAdapter(adapter);

//        for (int i = 0; i < 10; i++) {
//            MessageModel m = new MessageModel();
//            m.user = "dasfdasf";
//            m.message = "adsfasdfasdf";
//            adapter.addMessage(m);
//        }

    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.button:
               // jsonParser.getMessage(new TextMessage("", "All", edittext.getText().toString()));

                tcpConnector.sendMessage(jsonParser.getMessage(new TextMessage("", "All", edittext.getText().toString())));
             break;
        }
    }

    @Override
    public void resiveMessage(String message) {
        MessageModel m = new MessageModel();
        Object f = jsonParser.getFromString(message);
        if (f instanceof TextMessage) {
            TextMessage tm = (TextMessage)f;
            m.user = tm.from;
            m.message = tm.from +" > " + tm.text;
            adapter.addMessage(m);
        }

    }
}
