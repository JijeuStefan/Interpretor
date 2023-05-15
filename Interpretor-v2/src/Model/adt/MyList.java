package Model.adt;

import java.util.Vector;

public class MyList<T> implements MyIList<T> {
    private Vector<T> list;

    public MyList(){
        this.list = new Vector<T>();
    }

    @Override
    public void add(T elem){
        this.list.add(elem);
    }

    @Override
    public T elemAt(int index){
        return this.list.elementAt(index);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("( ");
        for (T elem:this.list)
        {
            s.append(elem.toString()).append(" , ");
        }
        return s.append(" )").toString();
    }
}
