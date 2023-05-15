package Model.stmt.HeapStmt;

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


public class HeapAlloc implements IStmt {
    String id;
    Exp exp;

    public HeapAlloc(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, IValue> symTbl = state.getsymTbl();
        MyIHeap<Integer, IValue> heap = state.getheap();

        if (!symTbl.isDefined(id))
            throw new StmtException("the used variable" +id + " was not declared before");

        IValue val = symTbl.lookup(this.id);

        if(!(val.getType() instanceof RefType))
            throw new StmtException("the used variable" +id + " doesn't have the RefType");

        IValue expression = this.exp.eval(symTbl,heap);
        IType locationType = ((RefValue)val).getlocationType();

        if(!locationType.equals(expression.getType()))
            throw new StmtException("the used variable" +id + " doesn't have the right inner type");

        heap.add(heap.new_location(),expression);

        symTbl.update(this.id,new RefValue(heap.getLocation(),locationType));

        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(this.id);
        IType typeExpr = this.exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExpr)))
            return typeEnv;
        else throw new StmtException("Different type in heap allocation statement");
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAlloc(this.id,this.exp.deepCopy());
    }

    @Override
    public String toString() {
        return "new("+this.id+", " + this.exp.toString()+")";
    }
}
