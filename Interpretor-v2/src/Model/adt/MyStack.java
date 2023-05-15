package Model.adt;

import Model.Exceptions.StackException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;


    public MyStack(){
        this.stack = new Stack<T>();
    }

    @Override
    public T pop() throws StackException {
        if (this.isEmpty())
            throw new StackException("Empty stack!");
        return this.stack.pop();
    }

    @Override
    public void push(T elem){
        this.stack.push(elem);
    }

    @Override
    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[ ");
        for (T elem:this.stack)
        {
            s.append(elem.toString()).append(" - ");
        }
        return s.append(" ]").toString();
    }
}
