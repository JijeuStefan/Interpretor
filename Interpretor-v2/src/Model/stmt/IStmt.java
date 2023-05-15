package Model.stmt;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.exp.Exp;
import Model.type.IType;

public interface IStmt {

    PrgState execute(PrgState state)throws MyException;

    MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException;
    IStmt deepCopy();

    String toString();

}
