package Model.stmt.Filestmt;

import Model.Exceptions.MyException;
import Model.Exceptions.StmtException;
import Model.PrgState;
import Model.adt.MyIDict;
import Model.exp.Exp;
import Model.stmt.IStmt;
import Model.type.IType;
import Model.type.IntType;
import Model.type.StringType;
import Model.val.IValue;
import Model.val.IntValue;
import Model.val.StringValue;

import java.io.BufferedReader;

public class readFile implements IStmt {
    Exp exp;
    Exp var_name;

    public readFile(Exp exp, Exp var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, IValue> symTbl = state.getsymTbl();
        MyIDict<String, BufferedReader> FileTable = state.getFileTable();

        IValue file_name = this.exp.eval(symTbl,state.getheap());

        IValue variable = this.var_name.eval(symTbl,state.getheap());

        if (!(file_name.getType().equals(new StringType())))
            throw new StmtException("Expression is not a String!");

        if (!(variable.getType().equals(new IntType())))
            throw new StmtException("Variable is not of type int!");

        StringValue file_path = (StringValue) file_name;

        if (!FileTable.isDefined(file_path.getValue()))
            throw new StmtException("Close File failed since header doesn't exist!");

        BufferedReader reader = FileTable.lookup(file_path.getValue());

        int fileValue;
        try {
            fileValue = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            fileValue = 0;
        }
        symTbl.update(this.var_name.toString(), new IntValue(fileValue));

        return state;

    }
    @Override
    public MyIDict<String, IType> typecheck(MyIDict<String,IType> typeEnv) throws MyException {
        IType typ = exp.typecheck(typeEnv);
        if (!typ.equals(new StringType()))
            throw new MyException("Variable is not a file name!");
        return typeEnv;
    }

    @Override
    public openRFile deepCopy() {return new openRFile(this.exp.deepCopy());}

    @Override
    public String toString(){
        return "readFile("+this.exp.toString()+","+this.var_name.toString()+")";
    }
}
