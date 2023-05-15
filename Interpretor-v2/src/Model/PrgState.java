package Model;

import Model.Exceptions.MyException;
import Model.adt.*;
import Model.stmt.IStmt;
import Model.val.IValue;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDict<String, IValue> symTable;
    private MyIList<IValue> out;

    private MyIDict<String,BufferedReader> FileTable;

    private MyIHeap<Integer,IValue> heap;

    private int id;

    private static int lastId = 0;

    public PrgState(MyIStack<IStmt> stk, MyIDict<String,IValue> symtbl, MyIList<IValue> ot,MyIDict<String,BufferedReader> FileTable, MyIHeap<Integer,IValue> heap)
    {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = ot;
        this.FileTable = FileTable;
        this.heap = heap;

        this.id = setId();
    }

    public PrgState(MyIStack<IStmt> stk, MyIDict<String,IValue> symtbl, MyIList<IValue> ot,MyIDict<String,BufferedReader> FileTable, MyIHeap<Integer,IValue> heap,IStmt stmt)
    {
        this.exeStack = stk;
        this.exeStack.push(stmt);
        this.symTable = symtbl;
        this.out = ot;
        this.FileTable = FileTable;
        this.heap = heap;

        this.id = setId();
    }

    public PrgState oneStep() throws MyException{
        if(this.exeStack.isEmpty()) throw new MyException("PrgState stack is empty");
        IStmt crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }

    public boolean isNotCompleted(){ return !this.exeStack.isEmpty();}

    public int setId() {
        PrgState.lastId++;
        return lastId;
    }

    public MyIStack<IStmt> getSTk() {return this.exeStack;}

    public void setSTk(MyIStack<IStmt> s) {this.exeStack=s;}

    public MyIDict<String, IValue> getsymTbl() {return this.symTable;}

    public void setsymTbl(MyIDict<String, IValue> s) {this.symTable=s;}

    public MyIList<IValue> getout() {return this.out;}

    public void setout(MyIList<IValue> s) {this.out=s;}

    public MyIDict<String, BufferedReader> getFileTable() {return this.FileTable;}

    public void setFileTable( MyIDict<String, BufferedReader> FileTable) {this.FileTable=FileTable;}

    public MyIHeap<Integer,IValue> getheap() {return this.heap;}

    public void setheap(MyIHeap<Integer,IValue>heap) {this.heap=heap;}

    @Override
    public String toString(){return "id: " + this.id + "\nExeStack: " + this.exeStack.toString()+"\nSymTable: " +this.symTable+"\nFileTable: " + this.FileTable+"\nHeap: " + this.heap+"\nOut: " + this.out;}


}
