package ru.rubiconepro.chatapp.Storage;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import ru.rubiconepro.chatapp.Model.IBase;


public interface IStorage <T extends IBase> {
    void storeElement(T element) throws Exception;
    void deleteElement(T element) throws Exception;
    void updateElement(T element) throws Exception;

    T getElementByID(Integer id);
    Collection<T> getElementByField(String field, String value);
}


//public interface IStorage {
//    void storeElement(IBase element) throws Exception;
//    void deleteElement(IBase element) throws Exception;
//    void updateElement(IBase element) throws Exception;
//
//    <T extends IBase> T getElementByID(Integer id);
//}