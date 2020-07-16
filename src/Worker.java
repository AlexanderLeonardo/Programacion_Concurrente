import java.util.ArrayList;

public class Worker extends Thread {

    Buffer myBuffer;
    Object task;

    public Worker(Buffer buffer) {

        myBuffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            task = myBuffer.pop();
            task.run();
            PoisonPill p = new PoisonPill(new ArrayList<Integer>(), 0);
            p.run();
            // Ejecuta la tarea Task que extrae del buffer y al terminar de ejecutarlo, se arroja la excepcion
            // de tipo PoisonException a traves de la tarea PoisonPill.
        }
    }
}