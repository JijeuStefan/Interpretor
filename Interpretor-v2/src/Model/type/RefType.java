package Model.type;

import Model.val.IValue;
import Model.val.RefValue;

public class RefType implements IType{
    private IType inner;
    
    public RefType(IType inner) {this.inner=inner;}
    public IType getInner() {return inner;}

    @Override
    public IValue defaultValue() { return new RefValue(0,inner.deepCopy());}

    @Override
    public IType deepCopy(){
        return new RefType(inner.deepCopy());
    }

    @Override
    public boolean equals(Object another){
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString() { return "Ref(" +inner.toString()+")";}
}
