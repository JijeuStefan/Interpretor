package Controller;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.val.IValue;
import Model.val.RefValue;
import Repository.IRepo;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo Repo;

    private ExecutorService executor;

    public Controller(IRepo repo){this.Repo = repo;}

    public void addProgram(PrgState newPrg) {
        Repo.addPrg(newPrg);
    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<IValue> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symTableAddr,List<Integer> heapAddr, Map<Integer,IValue> heap){
        return heap.entrySet().stream()
                .filter(e -> ( symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public void conservativeGarbageCollector(List<PrgState> programStates) {
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddrFromSymTable(p.getsymTbl().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> {
            p.getheap().setContent((HashMap<Integer, IValue>) safeGarbageCollector(symTableAddresses, getAddrFromHeap(p.getheap().getContent().values()), p.getheap().getContent()));
        });
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws MyException, InterruptedException {
        prgList.forEach(prg -> {
            try {
                this.Repo.logPrgStateExec(prg);
            } catch (MyException e) {
                System.err.println("error: " + e);
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        System.err.println("error: " + e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newProgramList);

        prgList.forEach(programState -> {
            try {
                this.Repo.logPrgStateExec(programState);
                System.out.println("\n" + programState + "\n");
            } catch (MyException e) {
                System.err.println("error: ");
            }
        });
        this.Repo.setPrgList(prgList);
    }

    public void allStep(){
        try{
            this.Repo.clearFile();
            executor = Executors.newFixedThreadPool(2);

            List<PrgState> prgList=removeCompletedPrg(this.Repo.getPrgList());
            while(prgList.size() > 0){
                conservativeGarbageCollector(prgList);
                oneStepForAllPrg(prgList);
                prgList=removeCompletedPrg( this.Repo.getPrgList());
            }
            executor.shutdownNow();

        }
        catch (MyException | RuntimeException | InterruptedException e){
            System.err.println("Error: " + e);
        }
    }
}
