import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;


public class ConcurRadixSort {

    private final ThreadPool threadPool;
    private final int threads;
    private int batchSize;

    public ConcurRadixSort(int bufferSize, int threads, int batchSize){

        this.threads = threads;
        this.threadPool = new ThreadPool(this.threads, bufferSize);
        this.batchSize = batchSize;
    }

    public ArrayList<Integer> radixSort(ArrayList<ArrayList> list){

        ListBuffer listBuffer = new ListBuffer(list);

        for(int bit = 0; bit < 32; bit++){
            Collection<List<Integer>> batches = takeBatches(listBuffer.pop());
            SplitBuffer splitBuffer = new SplitBuffer(batches.size());
            int expectedPosition = 0;

            for(List<Integer> batch: batches){
                threadPool.launch(new SplitTask(batch, splitBuffer, bit, expectedPosition));
                expectedPosition++;
            }

            threadPool.launch(new MergeTask(splitBuffer, listBuffer));
        }

        threadPool.stop();

        return listBuffer.pop();
    }

    private Collection<List<Integer>> takeBatches(ArrayList<Integer> list){
        AtomicInteger count = new AtomicInteger();
        return list.stream().collect(Collectors.groupingBy(x -> count.getAndIncrement() / batchSize)).values();
    }

    /*
    public void taskDivision(){

        int tam=listaDeNumeros.size();
        int thread = myThreadPool.getCantidadThreads();
        int res = tam / thread;

        for(int i = 0; i < res; i++){
            myThreadPool.addTask(new Task(listaDeNumeros));

        }
    }
    */
}
