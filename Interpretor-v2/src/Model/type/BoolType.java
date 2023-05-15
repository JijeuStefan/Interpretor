package Model.type;

import Model.val.BoolValue;
import Model.val.IValue;

public class BoolType implements IType{
    @Override
    public IValue defaultValue(){
        return new BoolValue();
    }

    @Override
    public IType deepCopy(){
        return new BoolType();
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof BoolType;
    }
    @Override
    public String toString(){
        return "bool";
    }
}
