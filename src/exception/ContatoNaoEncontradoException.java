package exception;

public class ContatoNaoEncontradoException extends RuntimeException {

    public ContatoNaoEncontradoException() {
        super("Nenhum contato foi encontrado.");
    }

    public ContatoNaoEncontradoException(String numeroTelefone) {
        super("Nenhum contato encontrado para o telefone: " + numeroTelefone);
    }
}
