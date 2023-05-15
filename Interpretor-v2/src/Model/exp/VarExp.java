package Model.exp;

import Model.Exceptions.MyException;
import Model.adt.MyHeap;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.type.IType;
import Model.val.IValue;

public class VarExp implements Exp{
    private String var;

    public VarExp(String var){
        this.var = var;
    }

    @Override
    public IValue eval(MyIDict<String, IValue> symTable, MyIHeap<Integer,IValue> hp) throws MyException {
        return symTable.lookup(this.var);
    }
    @Override
    public IType typecheck(MyIDict<String,IType> typeEnv)throws MyException {
        return typeEnv.lookup(this.var);
    }

    @Override
    public Exp deepCopy(){
        return new VarExp(this.var);
    }
    @Override
    public String toString() {return this.var;}
}
