package service;

import model.*;
import view.AgendaView;
import view.Mensagens;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import common.Constantes;
import exception.ContatoJaRegistradoException;

public class AgendaService {

    Mensagens mensagens = new Mensagens();

    AgendaView view = new AgendaView();
    Agenda agenda = new Agenda();

    public void menu() {
        boolean continueMenu = true;
        while (continueMenu) {
            String option = (view.opcaoMenu());

            switch (option) {
                //1 ---- pede somente nome -- tem que adicionar sobrenome
                case Constantes.ADICIONAR_CONTATO -> adicionarContato(); // 1
                //2 falta cabeçalho para facilitar visualizacao - padronizar todos os nomes com a primeira maiuscula?
                case Constantes.LISTAR_CONTATO -> listarContatos(); // 2
                //3 ---- nao encontra com partes do nome -- o maior problema é se o nome é composto, como Ana Luiza, se eu digito só ana, não retona.
                //3 ---- Caso não encontre, dar opcao de digitar nomamente, acesar menu ou finalizar?
                //3 ---- Só retorna o nome -- qual a finalidade? Acho que pode ser interessante retornar todas as infos do contato? Mas aí se confunde com o item 10.
                case Constantes.BUSCAR_CONTATO -> imprimirBuscarContato(); // 3 // já criado
                //4 ---- tudo ótimo -- talvez inserir uma confirmação: Esta ação não poderá ser desfeita. Deseja excluir o contato da agenda?
                case Constantes.REMOVER_CONTATO -> removerContato(); // 4
                //5 ---- tudo ótimo -- talvez inserir uma confirmação: Esta ação não poderá ser desfeita. Deseja excluir TODOS os contatos da agenda?
                case Constantes.REMOVER_TODOS_CONTATOS -> removerTodosContatos(); // 5
                //6 ---- acho que podemos usar sempre números em toda a aplicacao.
                //6 ---- talvez listar os contatos para o usuário selecionar pelo índice para qual contato quer inserir
                //6 ---- no final, quando pergunta se quer adicionar mais um telefone, não aceita "S" e "N", só "s" e "n" -- ou tratar, ou pedir números
                case Constantes.ADICIONAR_TELEFONE_PARA_CONTATO -> adicionarTelefoneParaContato(); // 6
                //7 ----
                case Constantes.ADICIONAR_ENDERECO_PARA_CONTATO -> adicionarEnderecoParaContato(); // 7
                //8 ---- perguntar se tem certeza
                case Constantes.REMOVER_TELEFONE_PARA_CONTATO -> removerTelefoneParaContato();// 8
                //9 ---- não funciona - pergunta qual endereço, mas dá erro - colocar uma lista dos endereços e pedir para digitar o número.
                case Constantes.REMOVER_ENDERECO_PARA_CONTATO -> removerEnderecoParaContato();// 9
                //10 ---- lista ok, mas precisa arrumar layout
                case Constantes.MOSTRAR_TODAS_INFORMACOES_CONTATO -> mostrarTodasInformacoesParaContato(); // 10
                //11 ---- lista corretamente se eu inserir todos os telefones de 1 vez.
                //11 ---- caso eu adicione os telefones para o contato Ana, liste e depois decida inserir mais telefones
                //11 ---- os primeiros são ignorados e só permanecem os novos
                case Constantes.LISTAR_TODOS_TELEFONES_PARA_UM_CONTATO -> listarTodosTelefonesParaContato(); // 11
                //12 ---- só deixa incluir 1 endereço, se incluo outro, spobrescreve.
                //12 ---- fazer como telefones e verificar se quer incluir outro enereço
                case Constantes.LISTAR_TODOS_ENDERECOS_PARA_UM_CONTATO -> listarTodosEnderecosParaContato(); // 12
                case Constantes.EXIBIR_TODAS_INFORMACOES_TELEFONE_CONTATO_NA_AGENDA ->
                        exibirTodasInformacoesTelefone(); // 13
                case Constantes.EXIBIR_TODAS_INFORMACOES_ENDERECO_CONTATO_NA_AGENDA ->
                        exibirTodasInformacoesEndereco(); // 14

                // XXXXX EXTRAS XXXXX
                case Constantes.EXIBIR_LISTA_CONTATOS_COM_PAGINACAO -> exibirListaContatosComPaginacao(); // 15
                case Constantes.EXIBIR_LISTA_TELEFONES_COM_PAGINACAO -> exibirListaTelefonesComPaginacao(); // 16
                case Constantes.EXIBIR_LISTA_ENDERECOS_COM_PAGINACAO -> exibirListaEnderecosComPaginacao(); // 17
                case Constantes.EXPORTAR_TODOS_CONTACTOS_PARA_TXT -> exportarTodosContatosParaTXT(); // 18
                case Constantes.IMPORTAR_TODOS_CONTACTOS_PARA_TXT -> importarTodosContatosParaTXT(); // 19
                case "21" -> pegarDdd(); // 19
                case Constantes.SAIR_PROGRAMA -> continueMenu = sairPrograma();

            }
        }
    }


    // XXXXXXXXXXXXXXXXXXX CONTATOS XXXXXXXXXXXXXXXXXXX

    public void adicionarContato() { // 1
//        Contato novoContato = new Contato;
        Contato novoContato = view.AdicionarContato();
        boolean contatoExiste = agenda.getContatos()
                .stream()
                .anyMatch(contato -> contato.equals(novoContato));

        if (contatoExiste) {
            // TODO trocar retorno por lancamento de exception
            // throw new ContatoJaRegistradoException(novoContato.getNome());
            mensagens.contatoExiste();

            return;
        }
        agenda.getContatos().add(novoContato);
    }

    public void listarContatos() { // 2
        agenda.getContatos().forEach(System.out::println);
    }

    public List<Contato> buscarContato(String contatoProcurado) { // 3

        List<Contato> contatosEncontrados = agenda
                .getContatos()
                .stream()
                .filter(c -> c.getNome().equalsIgnoreCase(contatoProcurado))
                .collect(Collectors.toList());

        if (contatosEncontrados.size() == 0) {
            System.err.println("Contato não encontrado. ");
        }

        return contatosEncontrados;
    }

//        switch (opcoesContatoNaoEncontradoAlvo) {
//            case "1" -> view.buscarContato("------- BUSCAR CONTATO -------");
//            case "2" -> menu();
//            default -> System.out.println("Opcão inválida");
//        }



    public void mostrarTodasInformacoesParaContato() { // 10
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTodasInformacoesParaContato(contatoSelecionado);
    }


    public void imprimirBuscarContato() {
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        buscarContato(contato).forEach(System.out::println);
    }

    public void removerContato() { // 4
        String contatoARemover = view.buscarContato("------- REMOVER CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contatoARemover);
        Contato contatoEscolhido = view.escolherContato(contatosEncontrados);
        agenda.getContatos().remove(contatoEscolhido);

    }

    public void removerTodosContatos() { // 5
        agenda.getContatos().clear();
    }


    // XXXXXXXXXXXXXXXXXXX TELEFONES XXXXXXXXXXXXXXXXXXX
    public void adicionarTelefoneParaContato() { // 6
        String contato = view.buscarContato("------- ADD TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        if (contatosEncontrados.size() < 1)
            return;
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Telefone> telefones = view.pegarNovoTelefone();
        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado)) {
                cont.setTelefones(telefones);
            }
        });

    }

    public void removerTelefoneParaContato() { // 8
        String contato = view.buscarContato("------- REMOVER TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Telefone telefone = view.escolherTelefoneRemover(contatoSelecionado);
//        System.out.println(telefone);
//        System.out.println(contato);
        long quantidadeApagados = agenda.getContatos().stream()
                .filter(cont -> cont.equals(contatoSelecionado))
                .map(cont -> cont.getTelefones().remove(telefone))
                .count();
        System.out.println("Foi/Foram apagado(s) " + quantidadeApagados + " telefone(s).");

    }

    public void listarTodosTelefonesParaContato() {// 11
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTodasTelefonesParaContato(contatoSelecionado);
    }


    // XXXXXXXXXXXXXXXXXXX ENDERECOS XXXXXXXXXXXXXXXXXXX
    public void adicionarEnderecoParaContato() { // 7

        String contato = view.buscarContato("------- ADD ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Endereco> enderecos = view.pegarEndereco();
        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado)) {
                cont.setEnderecos(enderecos);
            }
        });
    }

    public void removerEnderecoParaContato() { // 9
        String contato = view.buscarContato("------- REMOVER ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Endereco endereco = view.escolherEnderecoRemover(contatoSelecionado);
        long quantidadeApagados = agenda.getContatos().stream()
                .filter(cont -> cont.equals(contatoSelecionado))
                .map(cont -> cont.getEnderecos().remove(endereco))
                .count();
        System.out.println("Foi/Foram apagado(s) " + quantidadeApagados + " endereço(s).");
    }

    public void pegarDdd() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite UF: ");
        String uf = sc.nextLine();

        Estado estadoOpcao = Estado.pegarDdd(uf);

        switch (estadoOpcao) {
            case RO -> System.out.println(Estado.RO.getDdd());
            case AC -> System.out.println(Estado.AC.getDdd());
            case AM -> System.out.println(Estado.AM.getDdd());
            default -> System.out.println("Digite um uf válido");
        }

    }


//    public void listarTodosTelefonesParaContato() {// 11
//        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
//        List<Contato> contatosEncontrados = buscarContato(contato);
//        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
//        view.mostrarTodasTelefonesParaContato(contatoSelecionado);
//    }


    public void listarTodosEnderecosParaContato() { // 12
        String contato = view.buscarContato("------- BUSCAR ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTodosEnderecosParaContato(contatoSelecionado);
    }


    public void exibirTodasInformacoesTelefone() {

    }

    public void exibirTodasInformacoesEndereco() { // 14

    }


    // XXXXXXXXXXXXXXXXXXX EXTRAS XXXXXXXXXXXXXXXXXXX


    public void exibirListaContatosComPaginacao() { // 15

    }

    public void exibirListaTelefonesComPaginacao() { // 16

    }

    public void exibirListaEnderecosComPaginacao() { // 17

    }

    public void exportarTodosContatosParaTXT() { // 18

    }

    public void importarTodosContatosParaTXT() { // 19

    }

    public boolean sairPrograma() { // 20
        return view.sairPrograma();
    }

    public Boolean existeContato() {
        return true;

    }

}
