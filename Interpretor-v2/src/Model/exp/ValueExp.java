package Model.exp;

import Model.Exceptions.MyException;
import Model.adt.MyHeap;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.type.IType;
import Model.val.IValue;

public class ValueExp implements Exp{
    private final IValue e;

    public ValueExp(IValue e){
        this.e = e;
    }

    @Override
    public IValue eval(MyIDict<String,IValue> symTable, MyIHeap<Integer,IValue> hp) {
        return this.e;
    }

    @Override
    public IType typecheck(MyIDict<String,IType> typeEnv)throws MyException {
        return this.e.getType();
    }
    @Override
    public Exp deepCopy(){
        return new ValueExp(this.e.deepCopy());
    }

    @Override
    public String toString() {
        return this.e.toString();
    }
}
