package ru.rubiconepro.chatapp.Model;

import java.util.Calendar;

/**
 * Базовый класс для сообщения
 * Этот класс ОБЯЗАТЕЛЬНО нужно переопределить для дальнейшего использования
 */
public abstract class IMessage extends IBase {
    public enum MessageType {
        TextMessage,
        PicMessage
    }

    protected Integer messageID; //ID ссобщения (ОБЯЗАТЕЛЬНО)
    protected Calendar sendDate; //Дата отправки (ОБЯЗАТЕЛЬНО)
    protected IUser fromUser; //Кем отправлено (ОБЯЗАТЕЛЬНО)
    protected IUser toUser; //Кому отправлено (можно использовать для отложенной отправки)
    protected MessageType messageType; //Тип сообщения (ОБЯЗАТЕЛЬНО) (ПОКА ТОЛЬКО текст)
    protected String message; //(НЕОБЯЗАТЕЛЬНО) текст сообщения
    protected byte[] binaryMessage; //(НЕОБЯЗАТЕЛЬНО) например картинка
}
