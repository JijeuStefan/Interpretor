package Model.stmt;

import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.adt.MyIStack;
import Model.exp.Exp;
import Model.type.BoolType;
import Model.type.IType;
import Model.val.BoolValue;
import Model.val.IValue;


public class WhileStmt implements IStmt {

    Exp exp;
    IStmt stmt1;

    public WhileStmt(Exp e,IStmt st1){
        this.exp = e;
        this.stmt1 = st1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getSTk();
        MyIDict<String, IValue> symTbl = state.getsymTbl();
        IValue v = exp.eval(symTbl,state.getheap());

        if(!v.getType().equals(new BoolType()))
            throw new StmtException("Conditional expr is not a boolean!");

        BoolValue b = (BoolValue) v;

        if(b.getValue()) {
            stk.push(new WhileStmt(this.exp,this.stmt1));
            stk.push(stmt1);
        }

        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        IType typeexp = exp.typecheck(typeEnv);
        if(typeexp.equals(new BoolType()))
        {
            try {
                stmt1.typecheck(typeEnv.clone());
                return typeEnv;
            }catch (Exception e)
            {
                throw new MyException("Something went wrong");
            }
        }
        else
            throw  new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public IStmt deepCopy(){return new WhileStmt(this.exp,this.stmt1);};

    @Override
    public String toString(){return "( WHILE(" + this.exp.toString() +") " + this.stmt1.toString() + ");";}
}

