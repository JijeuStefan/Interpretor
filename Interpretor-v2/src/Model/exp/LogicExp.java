package Model.exp;

import Model.Exceptions.ExpException;
import Model.Exceptions.MyException;
import Model.adt.MyHeap;
import Model.adt.MyIDict;
import Model.adt.MyIHeap;
import Model.type.BoolType;
import Model.type.IType;
import Model.type.IntType;
import Model.val.BoolValue;
import Model.val.IValue;


import java.util.Objects;

public class LogicExp implements Exp{
    private final Exp e1, e2;
    private final String op;

    public LogicExp(String op,Exp e1,Exp e2){
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public IValue eval(MyIDict<String, IValue> symTable, MyIHeap<Integer,IValue> hp) throws MyException {
        IValue v1 = this.e1.eval(symTable,hp);
        if(!(v1.getType().equals(new BoolType())))
            throw new ExpException("First expression is not of type BOOL");

        IValue v2 = this.e2.eval(symTable,hp);
        if(!(v2.getType().equals(new BoolType())))
            throw new ExpException("Second expression is not of type BOOL");

        BoolValue b1 = (BoolValue) v1;
        BoolValue b2 = (BoolValue) v2;

        boolean n1,n2;
        n1 = b1.getValue();
        n2 = b2.getValue();

        if(Objects.equals(op, "and")) return new BoolValue(n1 && n2);
        if(Objects.equals(op, "or")) return  new BoolValue(n1 || n2);

        throw new ExpException("Unknown operator!");

    }

    @Override
    public IType typecheck(MyIDict<String,IType> typeEnv)throws MyException{
        IType type1,type2;
        type1 = this.e1.typecheck(typeEnv);
        type2 = this.e2.typecheck(typeEnv);
        if (type1.equals(new BoolType()))
        {
            if (type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw  new MyException("firs operand is not an integer");
    }

    @Override
    public Exp deepCopy(){
        return new LogicExp(this.op,this.e1.deepCopy(),this.e2.deepCopy());
    }
    @Override
    public String toString() { return e1.toString() + " " + op + " " + e2.toString(); }
}

