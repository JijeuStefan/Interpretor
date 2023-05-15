package View;

import Model.Exceptions.MyException;
import Model.adt.MyDict;
import Model.exp.ArithExp;
import Model.exp.HeapExp.HeapRead;
import Model.exp.RelExp;
import Model.exp.ValueExp;
import Model.exp.VarExp;
import Model.stmt.*;
import Model.stmt.Filestmt.closeRFile;
import Model.stmt.Filestmt.openRFile;
import Model.stmt.Filestmt.readFile;
import Model.stmt.HeapStmt.HeapAlloc;
import Model.stmt.HeapStmt.HeapWrite;
import Model.type.*;
import Model.val.BoolValue;
import Model.val.IntValue;
import Model.val.StringValue;
import View.Commands.ExitCommand;
import View.Commands.RunExample;



public class Main {
    public static void main(String[] args) {

        // ex 1:  int v; v = 2; Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        // ex 2: a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(
                        new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        // ex 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

         //ex 4: string varf;varf="test.in";openRFile(varf);int varc;readFile(varf,varc);
        //      print(varc); readFile(varf,varc);print(varc);closeRFile(varf);
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new
                ValueExp(new StringValue("C:\\Users\\Lenovo\\Documents\\GitHub\\Intepretor-v2\\Interpretor-v2\\src\\View\\test"))),
                new CompStmt(new openRFile(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),
                        new CompStmt(new readFile(new VarExp("varf"),new VarExp("varc")),new CompStmt(new PrintStmt(new VarExp("varc"))
                                ,new CompStmt(new readFile(new VarExp("varf"),new VarExp("varc")),new CompStmt(new PrintStmt(new VarExp("varc")),
                                new closeRFile(new VarExp("varf"))))))))));

        //ex 5: Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAlloc("v",new
                ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAlloc("a",new VarExp("v")),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt( new VarExp("a")))))));

        //ex 6: Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAlloc("v",new
                ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAlloc("a",new VarExp("v")),new CompStmt(new PrintStmt(new HeapRead(new VarExp("v"))),
                        new PrintStmt(new ArithExp('+',new HeapRead(new HeapRead(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));

        //ex 7: Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5)
        IStmt ex7 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAlloc("v",new
                ValueExp(new IntValue(20))),new CompStmt(new PrintStmt(new HeapRead(new VarExp("v"))),
                new CompStmt(new HeapWrite("v",new ValueExp(new IntValue(30))),new PrintStmt(new ArithExp('+',
                        new HeapRead(new VarExp("v")),new ValueExp(new IntValue(5))))))));


        //ex 8: int v;v=4;(while (v>0) print(v);v=v-1);print(v);
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4)))
                ,new CompStmt(new WhileStmt(new RelExp(">",new VarExp("v"),new ValueExp(new IntValue(0))),
                new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                new PrintStmt(new VarExp("v")))));

        //ex 9: Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)));
        IStmt ex9 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAlloc("v",new
                ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAlloc("a",new VarExp("v")),new CompStmt(new HeapAlloc("v",new
                        ValueExp(new IntValue(30))), new PrintStmt(new HeapRead(new HeapRead(new VarExp("a")))))))));


        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAlloc("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new HeapWrite("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapRead(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapRead(new VarExp("a")))))))));


        MyDict<String, IType> typchk = new MyDict<String, IType>();

        try {


            ex1.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex2.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex3.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex4.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex5.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex6.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex7.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex8.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex9.typecheck(typchk);
            typchk = new MyDict<String, IType>();
            ex10.typecheck(typchk);

            Helper h1 = new Helper(ex1, "ex1");
            Helper h2 = new Helper(ex2, "ex2");
            Helper h3 = new Helper(ex3, "ex3");
            Helper h4 = new Helper(ex4, "ex4");
            Helper h5 = new Helper(ex5, "ex5");
            Helper h6 = new Helper(ex6, "ex6");
            Helper h7 = new Helper(ex7, "ex7");
            Helper h8 = new Helper(ex8, "ex8");
            Helper h9 = new Helper(ex9, "ex9");
            Helper h10 = new Helper(ex10, "ex10");


            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1", ex1.toString(), h1.getController()));
            menu.addCommand(new RunExample("2", ex2.toString(), h2.getController()));
            menu.addCommand(new RunExample("3", ex3.toString(), h3.getController()));
            menu.addCommand(new RunExample("4", ex4.toString(), h4.getController()));
            menu.addCommand(new RunExample("5", ex5.toString(), h5.getController()));
            menu.addCommand(new RunExample("6", ex6.toString(), h6.getController()));
            menu.addCommand(new RunExample("7", ex7.toString(), h7.getController()));
            menu.addCommand(new RunExample("8", ex8.toString(), h8.getController()));
            menu.addCommand(new RunExample("9", ex9.toString(), h9.getController()));
            menu.addCommand(new RunExample("10", ex10.toString(), h10.getController()));

            menu.show();
        }catch (MyException e){
            System.err.println("Error: " + e);
        }
    }
}