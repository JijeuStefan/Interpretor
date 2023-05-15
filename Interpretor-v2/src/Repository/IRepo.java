package Repository;

import Model.Exceptions.MyException;
import Model.Exceptions.PrintWException;
import Model.PrgState;
import Model.adt.MyIList;

import java.util.List;

public interface IRepo {
    void addPrg(PrgState newPrg);

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgList);

    void logPrgStateExec(PrgState prg) throws MyException;

    void clearFile() throws MyException;


}
