package Model.stmt.Filestmt;

import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.exp.Exp;
import Model.stmt.IStmt;
import Model.type.IType;
import Model.type.StringType;
import Model.val.IValue;
import Model.val.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    Exp exp;

    public closeRFile(Exp exp){
        this.exp=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, IValue> symTbl = state.getsymTbl();
        MyIDict<String, BufferedReader> FileTable = state.getFileTable();

        IValue file_name = this.exp.eval(symTbl,state.getheap());

        if(!file_name.getType().equals(new StringType()))
            throw new StmtException("Expression is not a String!");

        StringValue file_path = (StringValue) file_name;

        if(!FileTable.isDefined(file_path.getValue()))
            throw new StmtException("Close File failed since header doesn't exist!");


        try {
            BufferedReader reader = FileTable.lookup(file_path.getValue());
            reader.close();
            FileTable.remove(file_path.getValue());

            return state;
        }catch (IOException e){
            throw new StmtException("Can't close the file!");
        }
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException{
        if(this.exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StmtException("CloseRFile requires a string exp");
    }
    @Override
    public IStmt deepCopy(){
        return new closeRFile(this.exp.deepCopy());
    }

    @Override
    public String toString(){
        return "close("+exp.toString()+")";
    }
}
