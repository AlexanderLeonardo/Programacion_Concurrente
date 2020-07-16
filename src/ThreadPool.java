import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private int cantidadThreads;
    private Buffer myBuffer;
    private List<Worker> hilosAguardar = new ArrayList<Worker>();

    public ThreadPool(int cantidadThread, int tambuffer ){
        this.cantidadThreads=cantidadThread;
        this.myBuffer=new Buffer(tambuffer);
    }

    public int getCantidadThreads() {
        return cantidadThreads;
    }

    public void launch(Task task){

      for(int i = 1; i < this.cantidadThreads; i++){
            Worker thread = new Worker(this.myBuffer);
            hilosAguardar.add(thread);
            thread.start();
      }
    }

    public void addTask(Task task){
        this.myBuffer.push(task);
    }



    public void stop(){
        for(Worker w: this.hilosAguardar){
            PoisonPill p = new PoisonPill(new ArrayList<Integer>(), 0);
            p.run();
        }
        // Por cada worker que ejecutó su respectivo Task, finaliza el mismo mediante el uso de PoisonPill.
    }

}
