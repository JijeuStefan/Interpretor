package Model.exp;

import Model.Exceptions.ExpException;
import Model.Exceptions.MyException;
import Model.adt.MyHeap;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.type.IType;
import Model.val.IValue;

public interface Exp {

    IValue eval(MyIDict<String, IValue> symTable, MyIHeap<Integer,IValue> hp) throws MyException;

    IType typecheck(MyIDict<String,IType> typeEnv) throws MyException;

    Exp deepCopy();

    @Override
    String toString();
}
