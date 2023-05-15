package Model.adt;

import Model.Exceptions.DictException;

import java.util.HashMap;

public class MyHeap<T1,T2> implements MyIHeap<T1,T2>{
    HashMap<T1, T2> dictionary;
    int location;

    public MyHeap() {
        dictionary = new HashMap<T1,T2>();
        location = 0;
    }

    @Override
    public int new_location(){
        this.location ++;
        return this.location;
    }

    @Override
    public int getLocation(){
        return this.location;
    }

    @Override
    public void add(T1 key,T2 val){
        this.dictionary.put(key,val);
    }

    @Override
    public T2 remove(T1 key){return this.dictionary.remove(key);}

    @Override
    public void update(T1 key,T2 val){
        this.dictionary.put(key,val);
    }

    @Override
    public T2 lookup(T1 key) throws DictException {
        if (!isDefined(key))
            throw new DictException("Undefined key!");
        return this.dictionary.get(key);
    }

    @Override
    public boolean isDefined(T1 key){
        return this.dictionary.containsKey(key);
    }

    @Override
    public MyIDict<T1,T2> clone(){
        MyIDict<T1,T2> map = new MyDict<T1,T2>();
        for (HashMap.Entry<T1, T2> entry : this.dictionary.entrySet()) {
            map.add(entry.getKey(),entry.getValue());
        }
        return map;
    }

    @Override
    public HashMap<T1,T2> getContent(){
        return this.dictionary;
    }

    @Override
    public void setContent(HashMap<T1,T2> map){
        this.dictionary=map;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("{ ");
        for (HashMap.Entry<T1, T2> entry : this.dictionary.entrySet()) {
            T1 key = entry.getKey();
            T2 value = entry.getValue();
            s.append(key.toString()).append("->").append(value.toString()).append(", ");
        }
        return s.append(" }").toString();
    }
}

