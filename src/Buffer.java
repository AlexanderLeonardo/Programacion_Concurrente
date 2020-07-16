
public class Buffer {

    private Object[] data;
    private int inicio=0;
    private int fin=0;
    private int size ;

    public Buffer(int n){
        this.data = new Object[n + 1];
        this.size = n;
    }


    public boolean isEmpty() {return  inicio == fin; }
    public boolean isFull() { return next(inicio) == fin;}
    public int next(int i){return (i+1)% (this.size + 1);}

    public synchronized void push(Object obj){

        while(isFull()){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[inicio]=obj;
        this.inicio=next(inicio);
        notifyAll();
    }

    public synchronized Object pop(){
        while(isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object aux = data[fin];
        fin = next(fin);
        notifyAll();
        return aux;
    }
}
