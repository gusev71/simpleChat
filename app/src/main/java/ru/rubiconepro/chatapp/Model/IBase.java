package ru.rubiconepro.chatapp.Model;

import org.json.JSONObject;

public abstract class IBase {
    public abstract JSONObject pack();
    public abstract void unpack(JSONObject obj);
}
