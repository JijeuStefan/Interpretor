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

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el){
        this.exp = e;
        this.thenS = t;
        this.elseS = el;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getSTk();
        MyIDict<String,IValue> symTbl= state.getsymTbl();

        IValue value = this.exp.eval(symTbl,state.getheap());

        if (!(value.getType().equals(new BoolType())))
            throw new StmtException("Conditional expr is not a boolean!");

        BoolValue b = (BoolValue) value;

        if(b.getValue())
            stk.push(thenS);
        else
            stk.push(elseS);

        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        IType typeexp = exp.typecheck(typeEnv);
        if(typeexp.equals(new BoolType()))
        {
            try {
                thenS.typecheck(typeEnv.clone());
                elseS.typecheck(typeEnv.clone());
                return typeEnv;
            }catch (Exception e)
            {
                throw new StmtException("Something went wrong");
            }
        }
        else
            throw  new StmtException("The condition of If has not the type bool");
    }

    @Override
    public IStmt deepCopy(){
        return new IfStmt(this.exp.deepCopy(),this.thenS.deepCopy(),this.elseS.deepCopy());
    }
    @Override
    public String toString(){
        return "(IF("+exp.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+"))";
    }

}
