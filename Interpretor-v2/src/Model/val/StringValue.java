package Model.val;

import Model.type.IType;
import Model.type.StringType;

public class StringValue implements IValue{
    private String value;

    public StringValue(){
        this.value= "";
    }

    public StringValue(String elem){
        this.value=elem;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public IType getType(){
        return new StringType();
    }

    @Override
    public IValue deepCopy(){
        return new StringValue(this.value);
    }

    @Override
    public boolean equals(Object o){
        return o instanceof IntValue;

    }

    @Override
    public String toString(){
        return this.value;
    }

}
