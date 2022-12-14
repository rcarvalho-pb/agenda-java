package view;

import exception.ContatoNaoEncontradoException;
import model.Contato;
import model.Endereco;
import model.Telefone;
import model.TipoEndereco;
import model.enums.TipoTelefone;
import util.Constantes;
import util.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AgendaView {


    Scanner scan = new Scanner(System.in);
    Mensagens mensagens = new Mensagens();

    public String opcaoMenu() {
        System.out.println("---------- MENU ----------");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Listar");
        System.out.println("3 - Buscar Contato");
        System.out.println("4 - Remover um contato");
        System.out.println("5 - Remover todos os contatos");
        System.out.println("6 - Informações Contatos -> ");
        System.out.println("7 - Exibir a lista de contatos com paginação");
        System.out.println("8 - Exibir a lista de telefones com paginação");
        System.out.println("9 -  Exibir a lista de endereços com paginação");
        System.out.println("10 - Exportar todos os contatos para um arquivo texto ");
        System.out.println("11 - Importar todos os contatos de um arquivo texto ");
        System.out.println("12 - Sair do Programa ");
        System.out.print("\n> ");


        return scan.nextLine();

    }

    public String opcaoMenuContato() {
        System.out.println("---------- MENU INFORMAÇÕES CONTATO ----------");
        System.out.println("1 - Adicionar telefone para contato");
        System.out.println("2 - Adicionar um endereço a um contato");
        System.out.println("3 - Remover um telefone de um contato da agenda");
        System.out.println("4 - Remover um endereço de um contato da agenda");
        System.out.println("5 - Exibir todas as informações de um contato da agenda");
        System.out.println("6 - Listar todos os telefones de um contato da agenda");
        System.out.println("7 - Listar todos os endereços de um contato da agenda");
        System.out.println("8 - Exibir todas as informações de um telefone de um contato da agenda");
        System.out.println("9 - Exibir todas as informações de um endereço de um contato da agenda");
        System.out.println("10 - Retornar ao menu principal");
        System.out.print("\n> ");

        return scan.nextLine();

    }

    public String buscarContato(String frase) { //3
        System.out.println(frase);
        System.out.println("Qual o contato? ");
        System.out.print("> ");
        return scan.nextLine();
    }

    public String buscarEndereco(String frase) { //3
        System.out.println(frase);
        System.out.println("Qual o endereço? ");
        System.out.print("> ");
        return scan.nextLine();
    }


    public Contato AdicionarContato() { //1
        System.out.println("Nome do Contato: ");
        System.out.print("> ");
        String nome = scan.nextLine();

        System.out.println("Sobrenome: ");
        System.out.print("> ");
        String sobrenome = scan.nextLine();

        System.out.println("E-mail do Contato: ");
        System.out.print("> ");
        String email = scan.nextLine();

        return new Contato(nome, sobrenome, email);
    }

    public Contato escolherContato(List<Contato> contatos) { //Para buscar um contato para editar


        int contador = Constantes.INDEX_FATOR;
        for (Contato contato : contatos) {
            System.out.println(contador + ": " + contato);
            contador++;
        }

        System.out.println(" ");
        System.out.println("Digite o indice do contato: ");
        System.out.print("> ");
        int opcao = Integer.parseInt(scan.nextLine()) - Constantes.INDEX_FATOR;
        return contatos.get(opcao);

    }

    public Endereco escolherEnderecoRemover(Contato contato) { //8
        for (int i = 0; i < contato.getEnderecos().size(); i++) {
            System.out.println((i + 1) + "\nEndereco: \n " + contato.getEnderecos().get(i));
        }
        System.out.println("Qual endereco a remover? ");
        System.out.print("> ");
        int opcao = scan.nextInt() - Constantes.INDEX_FATOR;
        scan.nextLine();
        return contato.getEnderecos().get(opcao);

    }

    public Endereco pegarEndereco() {

        System.out.println("Qual o tipo de endereco? ");
        System.out.println("[1] RESIDENCIAL - [2] COMERCIAL - [3] CAIXA_POSTAL ");
        System.out.print("> ");
        TipoEndereco tipoEndereco = TipoEndereco.pegarEndereco(scan.nextLine());
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

        return new Endereco(logradouro, cep, numero, cidade, estado, tipoEndereco);
    }


    public List<Endereco> pegarEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        boolean continuarLoop;
        do {
            System.out.println("Qual o tipo de endereco? ");
            System.out.println("[1] RESIDENCIAL - [2] COMERCIAL - [3] CAIXA_POSTAL ");
            System.out.print("> ");
            TipoEndereco tipoEndereco = TipoEndereco.pegarEndereco(scan.nextLine());
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


            enderecos.add(new Endereco(logradouro, cep, numero, cidade, estado, tipoEndereco));
            continuarLoop = perguntarAoUsuario("Deseja adicionar outro endereço?");

        } while (continuarLoop);

        return enderecos;
    }

    public Telefone pegarTelefone() {
        System.out.println("Informe o Tipo de Telefone: ");
        System.out.println("[1] RESIDENCIAL - [2] CELULAR - [3] COMERCIAL - [4] FIXO  ");
        System.out.print("> ");
        TipoTelefone tipoTelefone = TipoTelefone.pegarTipoTelefone(scan.nextLine());
        System.out.println("Informe o DDD: ");
        System.out.print("> ");
        String numeroDDD = scan.nextLine();
        System.out.println("Informe o número de telefone: ");
        System.out.print("> ");
        String numeroTelefone = scan.nextLine();

        return new Telefone(numeroDDD, numeroTelefone, tipoTelefone);
    }

    public Telefone escolherTelefoneRemover(Contato contato) {


        for (int i = 0; i < contato.getTelefones().size(); i++) {
            System.out.println((i + Constantes.INDEX_FATOR) + ": Telefone: " + contato.getTelefones().get(i));
        }

        System.out.println("Qual telefone a remover? ");
        System.out.print("> ");
        int opcao = scan.nextInt() - Constantes.INDEX_FATOR;
        scan.nextLine();


        return contato.getTelefones().get(Constantes.INDEX_ZERO);


    }

    public void mostrarTodasInformacoesParaContato(Contato contato) {

        if (contato == null) {
            throw new ContatoNaoEncontradoException();
        }

        System.out.println(imprimirInformacoesContato(contato));
    }

    public void mostrarTodasInformacoesParaContato(long id, Contato contato) {

        if (contato == null) {
            throw new ContatoNaoEncontradoException();
        }

        System.out.println(id + " : " + imprimirInformacoesContato(contato));
    }


    public String imprimirInformacoesContato(Contato contato) {
        if (!contato.getTelefones().isEmpty() && !contato.getEnderecos().isEmpty()) {
            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("""
//                    Nome: %s
//                    Sobrenome: %s
//                    Email: %s
//                    """,
//                    contato.getNome(), contato.getSobrenome(), contato.getEmail()));

            sb.append("Endereços: \n");
            contato.getEnderecos().forEach(sb::append);

            sb.append("Telefones: \n");
            contato.getTelefones().forEach(sb::append);

            return sb.toString();
        }

        if (contato.getTelefones().isEmpty() && !contato.getEnderecos().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("""
                            Nome: %s
                            Sobrenome: %s
                            Email: %s
                            """,
                    contato.getNome(), contato.getSobrenome(), contato.getEmail()));

            sb.append("Endereços: \n");
            contato.getEnderecos().forEach(sb::append);
            return sb.toString();
        }

        if (!contato.getTelefones().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("""
                            Nome: %s
                            Sobrenome: %s
                            Email: %s
                            """,
                    contato.getNome(), contato.getSobrenome(), contato.getEmail()));

            sb.append("Telefones: \n");
            contato.getTelefones().forEach(sb::append);
            return sb.toString();
        }

        return "Nome: " + contato.getNome() + ", Sobrenome: " + contato.getSobrenome() + ", Email: " + contato.getEmail();
    }


    public void mostrarTelefones(Contato contato) {
        mensagens.mensagemNomeDoContatoParaTelefone(contato.getNome());
        for (int i = 0; i < contato.getTelefones().size(); i++) {
            System.out.println("Telefone " + (i + Constantes.INDEX_FATOR) + ": " + contato.getTelefones().get(i));
        }
    }

    public void mostrarTodosEnderecosParaContato(Contato contato) {
        contato.getEnderecos().forEach(System.out::println);
    }

    public boolean sairPrograma() {
        scan.close();
        System.out.println("Encerrando o programa. ");
        return false;
    }

    public Endereco escolherEndereco(Contato contato) {

        if (!(contato.getEnderecos().size() == Constantes.REGISTRO_UNICO)) {
            System.out.println("Qual endereco a remover? ");
            System.out.print("> ");
            int opcao = scan.nextInt() - Constantes.INDEX_FATOR;
            scan.nextLine();
            return contato.getEnderecos().get(opcao);
        }

        return contato.getEnderecos().get(Constantes.INDEX_ZERO);
    }

    public void mostrarEnderecos(Contato contato) {
        mensagens.mensagemNomeDoContatoParaEndereco(contato.getNome());
        for (int i = 0; i < contato.getEnderecos().size(); i++) {
            System.out.println("Endereco " + (i + 1) + ": \n" + contato.getEnderecos().get(i));
        }
    }

    public String buscarContatoPorTelefone() {
        System.out.println("Telefone sem DDD: ");
        System.out.print("> ");
        return scan.nextLine();
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

    public boolean perguntarAoUsuario(String pedido) {
        System.out.println(pedido + " [1] sim [2] não");
        System.out.print("> ");
        switch (scan.nextLine()) {
            case Constantes.RESP_SIM:
                return true;
            case Constantes.RESP_NAO:
                return false;
            default:
                System.err.println("Comando Inválido.");
                return false;
        }
    }

    public void excibirMenuPaginacao(int numeroDaPagina, int ultimaPagina) {
        System.out.println("EXIBINDO PAGINA " + numeroDaPagina + " / " + ultimaPagina);

        if (numeroDaPagina != ultimaPagina)
            System.out.println("1 - Para exibir a proxima pagina");

        if(numeroDaPagina != 1)
            System.out.println("2 - Para voltar para a pagina anterior");

        System.out.println("3 - Para escolher pagina");

        System.out.println("0 - Para retornar ao menu");
    }

    public <T> void exbirListaPaginada(List<T> lista, int tamanho) {
        Pageable<T> conteudo = new Pageable<>(lista, tamanho);
        int numeroDaPagina = 0;
        conteudo.get().forEach(System.out::println);
        System.out.println();

        System.out.println("-------------------------------------");
        excibirMenuPaginacao(1, lista.size()/tamanho + 1);
        System.out.println("-------------------------------------");

        System.out.print("> ");
        numeroDaPagina = scan.nextInt();
    }
}