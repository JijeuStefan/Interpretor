package Model.stmt;

import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyIDict;

import Model.exp.Exp;
import Model.type.IType;
import Model.val.IValue;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDict<String, IValue> symTbl= state.getsymTbl();

        if (symTbl.isDefined(id)){
            IValue val = exp.eval(symTbl,state.getheap());
            IType typId= (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new StmtException("declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else
            throw new StmtException("the used variable" +id + " was not declared before");
        return null;


    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookup(this.id);
        IType typeexp = exp.typecheck(typeEnv);
        if(typevar.equals(typeexp))
            return typeEnv;
        else
            throw new StmtException("Assignment: right hand side and left hand side have different types");
    }

    @Override
    public IStmt deepCopy(){
        return new AssignStmt(this.id,this.exp.deepCopy());
    }

    @Override
    public String toString(){
        return id+"="+exp.toString();
    }

}
