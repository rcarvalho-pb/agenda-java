package view;

import model.Contato;
import model.Endereco;
import model.Telefone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaView {

    Scanner scan = new Scanner(System.in);

    public String opcaoMenu(){
        System.out.println("---------- MENU ----------");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Listar");
        System.out.println("3 - Buscar Contato");
        System.out.println("4 - Remover um contato");
        System.out.println("5 - Remover todos os contatos");
        System.out.println("6 - Adicionar telefone para contato");
        System.out.println("7 - Adicionar um endereço a um contato");
        System.out.println("8 - Remover um telefone de um contato da agenda");
        System.out.println("9 - Remover um endereço de um contato da agenda");
        System.out.println("10 - Exibir todas as informações de um contato da agenda");
        System.out.println("11 - Listar todos os telefones de um contato da agenda");
        System.out.println("12 - Listar todos os endereços de um contato da agenda");
        System.out.println("13 - Exibir todas as informações de um telefone de um contato da agenda");
        System.out.println("14 - Exibir todas as informações de um endereço de um contato da agenda");
        System.out.println("15 - Exibir a lista de contatos com paginação");
        System.out.println("16 - Exibir a lista de telefones com paginaçã");
        System.out.println("17 -  Exibir a lista de endereços com paginação");
        System.out.println("18 - Exportar todos os contatos para um arquivo texto ");
        System.out.println("19 - Importar todos os contatos de um arquivo texto ");
        System.out.println("20 - Sair do Programa ");
        System.out.print("\n> ");
        


        return scan.nextLine();

    }

    public String buscarContato(String frase){
        System.out.println(frase);
        System.out.println("Qual o contato? ");
        System.out.print("> ");
        String nome = scan.nextLine();
        return nome;
    }

    public Contato AdicionarContato(){ //1
        System.out.println("Nome do Contato: ");
        System.out.print("> ");
        String nome = scan.nextLine();
        
        System.out.println("Sobrenome do Contato: ");
        System.out.print("> ");
        String sobrenome = scan.nextLine();

        System.out.println("E-mail do Contato");
        System.out.print("> ");
        String email = scan.nextLine();

        return new Contato(nome, sobrenome, email);
    }

    public Contato escolherContato(List<Contato> contatos) {

        if(!(contatos.size() == 1)){
            contatos.forEach(System.out::println);
            System.out.println("Qual contato? ");
            System.out.print("> ");
            Integer opcao = scan.nextInt() - 1;
            scan.nextLine();
            return contatos.get(opcao);
        }
        
        return contatos.get(0);
    }

    public List<Telefone> pegarNovoTelefone(){
        List<Telefone> telefones = new ArrayList<>();
        boolean continuarLoop = true;
        do{
            System.out.println("Informe o DDD e o Número de telefone: ");
            System.out.print("> ");
            String numeroTelefone = scan.nextLine();
            Telefone telefone = new Telefone(numeroTelefone);
            telefones.add(telefone);

            System.out.println("Deseja adicionar outro telefone? ");
            switch (scan.nextLine()){
                case "s", "sim":
                    continue;
                case "n", "nao": 
                    continuarLoop = false;
                    break;
                default:
                    System.err.println("Comando Inválido. ");
                    break;
            }
        }while(continuarLoop);
        

        return telefones;
    }

    public List<Endereco> pegarEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        boolean continuarLoop = true;
        do{
            
            System.out.println("Qual o logradouro? ");
            System.out.print("> ");
            String logradouro = scan.nextLine();
            System.out.println("Qual o CEP? ");
            System.out.print("> ");
            String cep = scan.nextLine();
            System.out.println("Qual o número? ");
            System.out.print("> ");
            String numero = scan.nextLine();
            System.out.println("Qual o Cidade? ");
            System.out.print("> ");
            String cidade = scan.nextLine();
            System.out.println("Qual o Estado? ");
            System.out.print("> ");
            String estado = scan.nextLine();

            enderecos.add(new Endereco(logradouro, cep, numero, cidade, estado));
            System.out.println("Deseja adicionar outro endereço? ");
            switch (scan.nextLine()){
                case "s", "sim":
                    continue;
                case "n", "nao": 
                    continuarLoop = false;
                    break;
                default:
                    System.err.println("Comando Inválido. ");
                    break;
            }

        }while(continuarLoop);
        
        return enderecos;
    }

    public Telefone escolherTelefoneRemover(Contato contato) {
        
        if(!(contato.getTelefones().size() == 1)){
            System.out.println("Qual telefone a remover? ");
            System.out.print("> ");
            Integer opcao = scan.nextInt() - 1;
            scan.nextLine();
            return contato.getTelefones().get(opcao);
        }
        
        return contato.getTelefones().get(0);
    }

    public void mostrarTodasInformacoesParaContato(Contato contato) {
        // if(contato.getTelefones() == null && contato.getEndereco() != null) System.out.println(contato.getNome() 
        //     + ", " + contato.getEmail() 
        //     + "\nEndereço: \n " + contato.getEndereco());

        // if(contato.getTelefones() != null && contato.getEndereco() == null) System.out.println(contato.getNome() + ", " 
        //     + contato.getEmail()
        //     + "\nTelefone(s): \n " + contato.getTelefones());

        // if(contato.getTelefones() != null && contato.getEndereco() != null) System.out.println(contato.getNome() + ", " + contato.getEmail()
        //     + "\nEndereço: \n" + contato.getEndereco()
        //     + "\nTelefone(s): \n " + contato.getTelefones());

        // if(contato.getTelefones() == null && contato.getEndereco() == null) System.out.println(contato);
        if(contato == null) {
            System.err.println("Contato inexistente. ");
            return;
        }
        System.out.println(contato);
    }

    public boolean sairPrograma() {
        scan.close();
        System.out.println("Encerrando o programa. ");
        return false;
    }

    public void mostrarTelefones(Contato contato) {
        contato.getTelefones().forEach(System.out::println);;
    }

    public Endereco escolherEndereco(Contato contato) {

        if(!(contato.getEnderecos().size() == 1)){
            System.out.println("Qual telefone a remover? ");
            System.out.print("> ");
            Integer opcao = scan.nextInt() - 1;
            scan.nextLine();
            return contato.getEnderecos().get(opcao);
        }
        
        return contato.getEnderecos().get(0);
    }

    public void mostrarEnderecos(Contato contato) {
        contato.getEnderecos().forEach(System.out::println);
    }

    public String buscarContatoPorTelefone() {
        System.out.println("Telefone sem DDD: ");
        System.out.print("> ");
        String numeroTelefone = scan.nextLine();
        return numeroTelefone;
    }

    public String buscarContatoPorEndereco() {
        String endereco;
        System.out.println("""
                ------- BUSCAR -------
                1 - LOGRADOURO
                2 - CEP
                """);
        System.out.print("> ");
        switch (scan.nextLine()) {
            case "1" -> {
                endereco = "1,";
                System.out.println("Logradouro: ");
                System.out.print("> ");
                String logradouro = scan.nextLine();
                endereco += logradouro;
            }
            case "2" -> {
                endereco = "2,";
                System.out.println("CEP: ");
                System.out.print("> ");
                String cep = scan.nextLine();
                endereco += cep;
            }
            default -> {
                endereco = null;
            }
        }
        return endereco;
    }
}