package Repository;

import Model.Exceptions.MyException;
import Model.Exceptions.PrintWException;
import Model.PrgState;
import Model.adt.MyIList;
import Model.adt.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class Repo implements IRepo{

    private List<PrgState> myPrgStates;

    private final String logFilePath;

    public Repo(String logFilePath) {
        this.myPrgStates = new Vector<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        this.myPrgStates.add(newPrg);
    }

    @Override
    public List<PrgState> getPrgList(){
        return this.myPrgStates;
    }

    @Override
    public void setPrgList(List<PrgState> prgList){
        this.myPrgStates = prgList;
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException{
        try{
            PrintWriter logFile=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Lenovo\\Documents\\GitHub\\Intepretor-v2\\Interpretor-v2\\src\\Repository\\"+this.logFilePath,true)));
            logFile.write(prg.toString()+"\n\n");
            logFile.close();
        }catch(IOException e)
        {
            throw new PrintWException(e.getMessage());
        }
    }

    @Override
    public void clearFile() throws PrintWException {
        try{
            PrintWriter logFile = new PrintWriter(new FileWriter("C:\\Users\\Lenovo\\Documents\\GitHub\\Intepretor-v2\\Interpretor-v2\\src\\Repository\\"+this.logFilePath));
            logFile.close();
        }catch(IOException e)
        {
            throw new PrintWException(e.getMessage());
        }
    }


}
