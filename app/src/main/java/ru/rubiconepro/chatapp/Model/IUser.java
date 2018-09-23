package ru.rubiconepro.chatapp.Model;

/**
 * Базовый класс для пользователя
 * Этот класс ОБЯЗАТЕЛЬНО нужно переопределить для дальнейшего использования
 */
public abstract class IUser extends IBase {
    protected Integer userID;
    protected String userName;
    protected String statusMessage;
}
