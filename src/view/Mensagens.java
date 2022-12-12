package view;

public class Mensagens{

    public String contatoExiste(){
        return "Contato existe.";
    }

    public void mensagemContatoCriado(){
        System.out.println("Contato criado com sucesso!");;
    }

    public void mensagemTelefoneAdicionadoSucesso(){
        System.out.println("Telefone(s) adicionado(s) com sucesso!");
    }

    public void mensagemEnderecoAdicionadoSucesso(){
        System.out.println("Endereco(s) adicionado(s) com sucesso!");
    }

}