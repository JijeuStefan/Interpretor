package Model.exp.HeapExp;

import Model.Exceptions.ExpException;
import Model.Exceptions.MyException;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.exp.Exp;
import Model.type.IType;
import Model.type.IntType;
import Model.type.RefType;
import Model.val.IValue;
import Model.val.RefValue;

public class HeapRead implements Exp {
    private Exp exp;

    public HeapRead(Exp exp){
        this.exp = exp;
    }

    @Override
    public IValue eval(MyIDict<String, IValue> symTable, MyIHeap<Integer,IValue> hp) throws MyException{
        IValue val = this.exp.eval(symTable,hp);

        if(!(val instanceof RefValue refValue))
            throw new ExpException("Expression is not RefValue");

        if(!hp.isDefined(refValue.getAddr()))
            throw new ExpException("The address of the expression is not in the Heap");

        return hp.lookup(refValue.getAddr());
    }

    @Override
    public IType typecheck(MyIDict<String,IType> typeEnv) throws MyException{
        IType type = this.exp.typecheck(typeEnv);
        if (type instanceof RefType type1)
        {
            return type1.getInner();
         }
        else throw new ExpException("The th argument is not a RefType");
    }

    @Override
    public Exp deepCopy(){
        return new HeapRead(this.exp.deepCopy());
    }

    @Override
    public String toString(){
        return "rH("+this.exp+")";
    }
}
