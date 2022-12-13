package view;


public class Mensagens{

    public String contatoExiste(){
        return "Contato existe.";
    }

    public void mensagemNomeDoContatoParaTelefone(String contato){
        System.out.println("Telefone(s) do(a) "+contato+":");
    }
    public void mensagemNomeDoContatoParaEndereco(String contato){
        System.out.println("Endereco(s) do(a) "+contato+":");
    }



    public void mensagemContatoCriado(){
        System.out.println("Contato criado com sucesso!");
    }

    public void mensagemTelefoneAdicionadoSucesso(){
        System.out.println("Telefone(s) adicionado(s) com sucesso!");
    }

    public void mensagemEnderecoAdicionadoSucesso(){
        System.out.println("Endereco(s) adicionado(s) com sucesso!");
    }

    public String nomeContato(){
        return "Nome: ";
    }
    public String sobrrenomeContato(){
        return "Sobrenome: ";
    }

    public String emailContato(){
        return  "email: ";
    }


}