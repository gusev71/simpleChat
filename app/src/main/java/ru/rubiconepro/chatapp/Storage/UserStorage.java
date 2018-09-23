package ru.rubiconepro.chatapp.Storage;

import java.util.Collection;

import ru.rubiconepro.chatapp.Model.IUser;

public class UserStorage implements IStorage <IUser> {
    @Override
    public void storeElement(IUser element) throws Exception {

    }

    @Override
    public void deleteElement(IUser element) throws Exception {

    }

    @Override
    public void updateElement(IUser element) throws Exception {

    }

    @Override
    public IUser getElementByID(Integer id) {
        return null;
    }

    @Override
    public Collection<IUser> getElementByField(String field, String value) {
        return null;
    }
}
