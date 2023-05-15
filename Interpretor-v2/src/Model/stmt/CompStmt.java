package Model.stmt;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.adt.MyIStack;
import Model.type.IType;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first,IStmt snd){
        this.first=first;
        this.snd=snd;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getSTk();
        stk.push(this.snd);
        stk.push(this.first);
        return null;
    }

    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException{
        return snd.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public IStmt deepCopy(){
        return new CompStmt(this.first.deepCopy(),this.snd.deepCopy());
    }

    @Override
    public String toString(){
        return first.toString()+" | "+snd.toString();
    }

}
