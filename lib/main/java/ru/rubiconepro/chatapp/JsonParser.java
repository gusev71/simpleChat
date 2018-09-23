package ru.rubiconepro.chatapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import ru.rubiconepro.chatapp.Communicator.Packeges.CMD;
import ru.rubiconepro.chatapp.Communicator.Packeges.TextMessage;

public class JsonParser {

    Map<String, Type> types;


    CMD cmd;
    Gson gson ;


    String cmd_text;

    public JsonParser(){

        types = new HashMap<>();
        types.put("Text", TextMessage.class);


        cmd = new CMD();
        gson = new GsonBuilder().create();
    }

    public void registerType (Type type) {
        this.types.put(type.toString().replace("class ", ""), type);
    }

    public String getMessage(Object type) {
        CMD c = new CMD();
        c.type = type.getClass().getName();
        c.data = gson.toJson(type);

        return gson.toJson(c);
    }

//    public String getJsonMessage(String username, String to, String message) {
//        TextMessage textMessage = new TextMessage();
//        textMessage.from = username;
//        textMessage.to = to;
//        textMessage.text = message;
//        cmd.type = "Message";
//        cmd.data = gson.toJson(textMessage);
//        return  gson.toJson(cmd);
//    }


    public Object getFromString(String json) {
        CMD c = gson.fromJson(json, CMD.class);
        Type t = types.get(c.type);
        if (t == null) return null;
        return gson.fromJson(c.data, t);
    }

}
