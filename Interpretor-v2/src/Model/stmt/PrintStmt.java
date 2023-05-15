package Model.stmt;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.adt.MyIList;
import Model.exp.Exp;
import Model.type.IType;
import Model.val.IValue;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<IValue> output = state.getout();
        output.add(this.exp.eval(state.getsymTbl(),state.getheap()));
        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
    @Override
    public IStmt deepCopy(){
        return new PrintStmt(this.exp.deepCopy());
    }
    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }

}
