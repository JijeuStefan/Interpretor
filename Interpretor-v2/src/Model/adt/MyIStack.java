package Model.adt;

import Model.Exceptions.StackException;

public interface MyIStack<T> {

    T pop() throws StackException;

    void push(T elem);

    boolean isEmpty();

    String toString();
}
