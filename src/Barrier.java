
public class Barrier {

    private int cantidadT;
    private int cantBloqueados;

    public Barrier(int cantidadThreads){
        this.cantidadT=cantidadThreads;
        this.cantBloqueados=0;
    }

    public synchronized void esperar(){

        cantBloqueados++;
        System.out.println("cantidad bloquedos "+cantBloqueados);

        while(!(cantBloqueados==cantidadT)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
    }

}
