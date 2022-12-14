package exception;

public class TelefoneJaRegistradoException extends RuntimeException {


    public TelefoneJaRegistradoException() {
        super("Telefone já existe!");
    }
}
