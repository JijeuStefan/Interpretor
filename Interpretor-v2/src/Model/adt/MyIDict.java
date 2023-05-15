package Model.adt;

import Model.Exceptions.DictException;

import java.util.Collection;
import java.util.HashMap;

public interface MyIDict<T1,T2> {

    void add(T1 key, T2 val);
    MyIDict<T1,T2> clone();
    T2 remove(T1 key) throws DictException;
    void update(T1 key, T2 val) throws DictException;
    T2 lookup(T1 key) throws DictException;
    boolean isDefined(T1 key);
    Collection<T2> values();
    HashMap<T1,T2> getContent();
    void setContent(HashMap<T1,T2> map);
    String toString();
}
