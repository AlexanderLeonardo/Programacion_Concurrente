import java.util.*;


public class SplitTask extends Task {

    private final List<Integer> batch;
    private SplitBuffer splitBuffer;
    private int bit;
    private int priority;

    public SplitTask(List<Integer> batch, SplitBuffer splitBuffer, int bit, int priority){

        this.batch = batch;
        this.splitBuffer = splitBuffer;
        this.bit = bit;
        this.priority = priority;
    }

    @Override
    public synchronized void run(){
        ArrayList tupla = split(batch, bit);
        splitBuffer.push(tupla, priority);
    }

    private ArrayList split(List<Integer> list, int bit){
        ArrayList<Integer> zeros = new ArrayList<>();
        ArrayList<Integer> ones = new ArrayList<>();
        int mask = 1 << bit;

        for(Integer element: list){
            if((element & mask) != 0){
                ones.add(element);
            }else{
                zeros.add(element);
            }
        }
        ArrayList<Integer> aux = new ArrayList<>(zeros);
        aux.addAll(ones);
        return aux;
    }
}
