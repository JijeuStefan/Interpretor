package Model.type;

import Model.stmt.IStmt;
import Model.val.IValue;

public interface IType {

    IValue defaultValue();

    IType deepCopy();

    @Override
    boolean equals(Object o);

    @Override
    String toString();
}
