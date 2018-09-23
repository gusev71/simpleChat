package ru.rubiconepro.chatapp.Communicator;

public interface ICommunicator {
    void sendMessage( String message);
    void subscribe(IResiver reciver);
    void unsubscribe(IResiver reciver);

    void stopConnector();
}
