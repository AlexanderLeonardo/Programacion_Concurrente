import java.util.ArrayList;

public class MergeTask extends Task {

    private SplitBuffer splitBuffer;
    private ListBuffer listBuffer;

    public MergeTask(SplitBuffer splitBuffer, ListBuffer buffer){
        super();
        this.splitBuffer = splitBuffer;
        this.listBuffer = buffer;
    }

    @Override
    public synchronized void run(){
        ArrayList<Integer> listaParcial = splitBuffer.pop();
        listBuffer.push(listaParcial);
    }
}
