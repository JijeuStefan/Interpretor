package Model.stmt.HeapStmt;

import Model.Exceptions.ExpException;
import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.exp.Exp;
import Model.stmt.IStmt;
import Model.type.IType;
import Model.type.RefType;
import Model.val.IValue;
import Model.val.RefValue;

public class HeapWrite implements IStmt {
    String id;
    Exp exp;

    public HeapWrite(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state)throws MyException{
        MyIDict<String, IValue> symTbl = state.getsymTbl();
        MyIHeap<Integer, IValue> heap = state.getheap();

        if(!symTbl.isDefined(this.id))
            throw new ExpException("The variable is not defined");

        IValue Value = symTbl.lookup(this.id);

        if(!(Value.getType() instanceof RefType))
            throw new ExpException("The variable it's not of type Ref");

        RefValue refValue = (RefValue) Value;

        if(!heap.isDefined(refValue.getAddr()))
            throw new ExpException("The address of the expression is not in the Heap");

        IValue expression = this.exp.eval(symTbl,heap);
        IType locationType = refValue.getlocationType();

        if(!locationType.equals(expression.getType()))
            throw new StmtException("the used variable" +id + " dosen't have the right inner type");

        heap.update(refValue.getAddr(),expression);

        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        if(typeEnv.lookup(this.id).equals((new RefType(this.exp.typecheck(typeEnv)))))
            return typeEnv;
        else
            throw new StmtException("Different type in heap writing statement");
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWrite(this.id,this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "wH("+this.id+", " + this.exp.toString()+")";
    }
}

