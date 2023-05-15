package Model.val;

import Model.stmt.IStmt;
import Model.type.IType;

public interface IValue {

    IType getType();

    IValue deepCopy();

    @Override
    boolean equals(Object o);

    @Override
    String toString();
}
