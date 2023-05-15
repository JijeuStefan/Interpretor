package Model.stmt;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.adt.MyIStack;
import Model.adt.MyStack;
import Model.type.IType;

public class ForkStmt implements IStmt{
    private IStmt stmt;

    public ForkStmt(IStmt stmt)
    {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> new_stack = new MyStack<>();
        new_stack.push(this.stmt);
        return new PrgState(new_stack,state.getsymTbl().clone(),state.getout(),state.getFileTable(),state.getheap());
    }

    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        this.stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public IStmt deepCopy(){
        return new ForkStmt(this.stmt.deepCopy());
    }

    @Override
    public String toString(){
        return "fork(" + this.stmt.toString()+")";
    }
}
