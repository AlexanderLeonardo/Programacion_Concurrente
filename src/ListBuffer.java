import java.util.ArrayList;

public class ListBuffer {

    private ArrayList<ArrayList> data;
    private int inicio=0;
    private int fin=0;
    private int size ;

    public ListBuffer(ArrayList<ArrayList> list){
        this.data = list;
        this.size = list.size();
    }


    public boolean isEmpty() {return  inicio == fin; }
    public boolean isFull() { return next(inicio) == fin;}
    public int next(int i){return (i+1)% (this.size + 1);}

    public synchronized void push(ArrayList obj){

        while(isFull()){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data.set(inicio, obj);
        this.inicio=next(inicio);
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
        ArrayList aux = this.data.get(fin);
        fin = next(fin);
        notifyAll();
        return aux;
    }
}