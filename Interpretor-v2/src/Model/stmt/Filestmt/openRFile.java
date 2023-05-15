package Model.stmt.Filestmt;

import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyFileDict;
import Model.adt.MyIDict;
import Model.adt.MyIList;
import Model.exp.Exp;
import Model.stmt.IStmt;
import Model.stmt.PrintStmt;
import Model.type.IType;
import Model.type.StringType;
import Model.val.IValue;
import Model.val.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {
    Exp exp;

    public openRFile(Exp exp){
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

        if(FileTable.isDefined(file_path.getValue()))
            throw new StmtException("Open File failed since header already exists!");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_path.getValue()));
            FileTable.add(file_path.getValue(), reader);

            return state;
        }catch (FileNotFoundException e){
            throw new StmtException("File does not exits!");
        }
    }

    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException{
        if(this.exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StmtException("OpenRFile requires a string exp");
    }
    @Override
    public IStmt deepCopy(){
        return new openRFile(this.exp.deepCopy());
    }

    @Override
    public String toString(){
        return "open("+exp.toString()+")";
    }
}
