package exception;

public class ContatoJaRegistradoException extends RuntimeException {

    public ContatoJaRegistradoException() {
    }

    public ContatoJaRegistradoException(String nomeContato) {
        super("O contato " + nomeContato + " já está registrado!");
    }

}
