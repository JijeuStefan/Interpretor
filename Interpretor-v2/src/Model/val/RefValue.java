package Model.val;

import Model.type.IType;
import Model.type.RefType;

public class RefValue implements IValue{
    private int address;
    private IType locationType;

    public RefValue(int address, IType locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public IType getlocationType() {return locationType;}

    public int getAddr() {return address;}

    @Override
    public IType getType() { return new RefType(locationType.deepCopy());}

    @Override
    public IValue deepCopy(){
        return new RefValue(this.address,this.locationType.deepCopy());
    }

    @Override
    public boolean equals(Object o){return o instanceof RefValue;}

    @Override
    public String toString(){
        return "(" + Integer.toString(this.address) + ", " + this.locationType.toString() + ")";
    }
}

