package Model.type;

import Model.val.IValue;
import Model.val.StringValue;

public class StringType implements IType{
    @Override
    public IValue defaultValue(){
        return new StringValue();
    }

    @Override
    public IType deepCopy(){
        return new StringType();
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof StringType;
    }
    @Override
    public String toString(){
        return "string";
    }

}
