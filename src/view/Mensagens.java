package view;

public class Mensagens{

    public String contatoExiste(){
        return "Contato existe.";
    }

    public void contatoRemovido(){
        System.out.println("Contato removido com sucesso! ");
    }

    public void confirmacaoExcluirContato(){
        System.out.println("Deseja realmente excluir o contato: [1] sim [2] não");
    }

    public void confirmacaoExcluirContatos(){
        System.out.println("Deseja realmente excluir todos os contatos: [1] sim [2] não");
    }

    public void confirmacaoExcluirTelefone(){
        System.out.println("Deseja realmente excluir o telefone: [1] sim [2] não");
    }

    public void confirmacaoExcluirEndereco(){
        System.out.println("Deseja realmente excluir o endereco: [1] sim [2] não");
    }
    public void enderecosApagados(long telefones){
        System.out.println("Foram deletado(s) " +telefones+ " enderecos(s)!");
    }
    public void telefonesApagados(long telefones){
        System.out.println("Foram deletado(s) " +telefones+ " telefone(s)!");
    }

    public void contatosRemovidos(){
        System.out.println("Todos os contatos foram removidos!");
    }
    public void operacaoCancelada(){
        System.out.println("Operacao cancelada!");
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