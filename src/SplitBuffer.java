import java.util.ArrayList;

public class SplitBuffer {

    private ArrayList[] data;
    private int inicio=0;
    private int fin=0;
    private int size ;
    private int priority;

    public SplitBuffer(int n){
        this.data = new ArrayList[n + 1];
        this.size = n;
    }


    public boolean isEmpty() {return  inicio == fin; }
    public boolean isFull() { return next(inicio) == fin;}
    public int next(int i){return (i+1)% (this.size + 1);}

    public synchronized void push(ArrayList obj, int priority){

        while(isFull()){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[inicio]=obj;
        this.inicio=next(inicio);
        this.priority = priority;
        notifyAll();
    }

    public synchronized ArrayList pop(){
        while(isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList aux = data[fin];
        fin = next(fin);
        notifyAll();
        return aux;
    }
}
