package Model.stmt;

import Model.Exceptions.DictException;
import Model.Exceptions.MyException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.type.IType;
import Model.val.IValue;

public class VarDeclStmt implements IStmt{
    private String name;
    private IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDict<String, IValue> symTbl= state.getsymTbl();

        if(symTbl.isDefined(this.name))
            throw new DictException("Variable already declared!");

        symTbl.add(this.name,this.type.defaultValue());
        return null;
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy(){
        return new VarDeclStmt(this.name,this.type.deepCopy());
    }

    @Override
    public String toString(){
        return this.type.toString()+" "+this.name;
    }

}
