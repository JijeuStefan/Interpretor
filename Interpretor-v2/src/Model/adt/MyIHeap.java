package Model.adt;

import Model.Exceptions.DictException;

import java.util.HashMap;

public interface MyIHeap<T1,T2> {

     void add(T1 key, T2 val);
     int new_location();
     int getLocation();
     MyIDict<T1,T2> clone();
     T2 remove(T1 key) throws DictException;
     void update(T1 key, T2 val) throws DictException;
     T2 lookup(T1 key) throws DictException;
     boolean isDefined(T1 key);
     HashMap<T1,T2> getContent();
     void setContent(HashMap<T1,T2> map);
     String toString();
}
