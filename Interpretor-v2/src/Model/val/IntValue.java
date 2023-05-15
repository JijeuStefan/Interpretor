package Model.val;

import Model.type.IType;
import Model.type.IntType;

public class IntValue implements IValue{
    private int value;

    public IntValue(){
        this.value=0;
    }

    public IntValue(int elem){
        this.value=elem;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public IType getType(){
        return new IntType();
    }

    @Override
    public IValue deepCopy(){
        return new IntValue(this.value);
    }

    @Override
    public boolean equals(Object o){return o instanceof IntValue;}

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }

}
