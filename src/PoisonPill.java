
import java.util.List;


class PoisonPill extends Task {

    private static final String POISON_PILL_MESSAGE = "END OF TASK";

    private List numeros;
    private int indice;

    public PoisonPill(List numeros, int indice){
        super();
    }

    @Override
    public void run(){
        try {
            throw new PoisonException(POISON_PILL_MESSAGE);
        }catch (PoisonException p){
            System.out.println(POISON_PILL_MESSAGE);
            p.printStackTrace();
        }
    }
}
