package ru.rubiconepro.chatapp.Communicator.Packeges;

public class TextMessage {
    public TextMessage(String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }
    public String from;
    public String to;
    public String text;
}
