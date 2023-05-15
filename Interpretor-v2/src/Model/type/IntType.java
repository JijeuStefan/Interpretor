package Model.type;

import Model.val.IValue;
import Model.val.IntValue;

public class IntType implements IType{

    @Override
    public IValue defaultValue(){
        return new IntValue();
    }

    @Override
    public IType deepCopy(){
        return new IntType();
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof IntType;
    }
    @Override
    public String toString(){
        return "int";
    }
}
