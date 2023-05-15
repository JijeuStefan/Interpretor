package Model.stmt;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.type.IType;


public class NopStmt implements IStmt {

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public IStmt deepCopy(){
        return new NopStmt();
    }

    @Override
    public String toString() {
        return "no operation";
    }
}
