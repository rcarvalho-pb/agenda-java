package exception;

public class AgendaVaziaException extends RuntimeException{

    public AgendaVaziaException() {
        super("Agenda Vazia!");
    }
}
