package Model.val;

import Model.type.BoolType;
import Model.type.IType;

public class BoolValue implements IValue{
    private boolean value;

    public BoolValue(){
        this.value=false;
    }

    public BoolValue(boolean elem){
        this.value=elem;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public IType getType(){
        return new BoolType();
    }

    @Override
    public IValue deepCopy(){
        return new BoolValue(this.value);
    }

    @Override
    public boolean equals(Object o){
        return o instanceof BoolValue;

    }

    @Override
    public String toString(){
        return Boolean.toString(this.value);
    }

}
