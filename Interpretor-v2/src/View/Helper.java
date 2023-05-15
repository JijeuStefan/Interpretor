package View;

import Controller.Controller;
import Model.PrgState;
import Model.adt.*;
import Model.stmt.IStmt;
import Repository.IRepo;
import Repository.Repo;

public class Helper {
    private final Controller controller;
    public Helper(IStmt ex, String path){
        PrgState myPrgState1 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(),new MyFileDict<>(),new MyHeap<>(),ex);
        IRepo repo = new Repo(path);
        this.controller = new Controller(repo);
        this.controller.addProgram(myPrgState1);
    }

    public Controller getController(){return this.controller;}
}

