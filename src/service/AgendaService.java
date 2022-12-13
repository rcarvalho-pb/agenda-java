package service;

import exception.*;
import model.*;
import view.AgendaView;
import view.Mensagens;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import util.Constantes;

public class AgendaService {

    Mensagens mensagens = new Mensagens();

    Scanner scan = new Scanner(System.in);

    int contador = 0;
    AgendaView view = new AgendaView();
    Agenda agenda = new Agenda();

    public void menu() {
        boolean continueMenu = true;
        boolean retornarMenu = true;
        while (continueMenu) {
            String option = (view.opcaoMenu());
            try {
                switch (option) {
                    case Constantes.ADICIONAR_CONTATO -> adicionarContato(); // 1
                    case Constantes.LISTAR_CONTATO -> listarContatos(); // 2
                    case Constantes.BUSCAR_CONTATO -> imprimirBuscarContato(); // 3
                    case Constantes.REMOVER_CONTATO -> removerContato(); // 4
                    case Constantes.REMOVER_TODOS_CONTATOS -> removerTodosContatos(); // 5
                    case Constantes.INFORMACOES_CONTATO -> menuContato(); // 6
                    
                    case Constantes.EXIBIR_LISTA_CONTATOS_COM_PAGINACAO -> exibirListaContatosComPaginacao(); // 7 - AINDA FALTA
                    case Constantes.EXIBIR_LISTA_TELEFONES_COM_PAGINACAO -> exibirListaTelefonesComPaginacao(); // 8 AINDA FALTA
                    case Constantes.EXIBIR_LISTA_ENDERECOS_COM_PAGINACAO -> exibirListaEnderecosComPaginacao(); // 9 AINDA FALTA
                    case Constantes.EXPORTAR_TODOS_CONTACTOS_PARA_TXT -> exportarTodosContatosParaTXT(); // 10 AINDA FALTA
                    case Constantes.IMPORTAR_TODOS_CONTACTOS_PARA_TXT -> importarTodosContatosParaTXT(); // 11 AINDA FALTA
                    case Constantes.SAIR_PROGRAMA -> {
                        retornarMenu = false;
                        continueMenu = sairPrograma();
                    }
                    default -> throw new EntradaInvalidaOuInsuficienteException("Comando invalido!");
                }
            } catch (ContatoNaoEncontradoException | ContatoJaRegistradoException e) {
                System.out.println(e.getMessage());
            } catch (Exception ignored) {
                System.out.println(ignored.getMessage());
            }

            if(retornarMenu){
                aguardarRepeticaoMenu();
            }
        }
    }

    public void menuContato() {

        boolean continueMenu = true;
        while (continueMenu) {
            String option = (view.opcaoMenuContato());
            try {
                switch (option) {
                    case Constantes.ADICIONAR_TELEFONE_PARA_CONTATO -> adicionarTelefoneParaContato(); // 1
                    case Constantes.ADICIONAR_ENDERECO_PARA_CONTATO -> adicionarEnderecoParaContato(); // 2
                    case Constantes.REMOVER_TELEFONE_PARA_CONTATO -> removerTelefoneParaContato();// 3
                    case Constantes.REMOVER_ENDERECO_PARA_CONTATO -> removerEnderecoParaContato();// 4
                    case Constantes.MOSTRAR_TODAS_INFORMACOES_CONTATO -> mostrarTodasInformacoesParaContato(); // 5
                    case Constantes.LISTAR_TODOS_TELEFONES_PARA_UM_CONTATO -> listarTodosTelefonesParaContato(); // 6
                    case Constantes.LISTAR_TODOS_ENDERECOS_PARA_UM_CONTATO -> listarTodosEnderecosParaContato(); // 7
                    case Constantes.EXIBIR_TODAS_INFORMACOES_TELEFONE_CONTATO_NA_AGENDA ->
                            exibirTodasInformacoesTelefone(); // 8
                    case Constantes.EXIBIR_TODAS_INFORMACOES_ENDERECO_CONTATO_NA_AGENDA ->
                            exibirTodasInformacoesEndereco(); // 9
                    case Constantes.RETORNAR_MENU_PRINCIPAL -> {
                        continueMenu = false;
                        menu();
                    }
                    default -> throw new EntradaInvalidaOuInsuficienteException("Comando invalido!");
                }
            } catch (ContatoNaoEncontradoException | ContatoJaRegistradoException e) {
                System.out.println(e.getMessage());
            } catch (Exception ignored) {
                System.out.println(ignored.getMessage());
            }
        }
        

    }

    public void adicionarContato() { // 1
//        Contato novoContato = new Contato;
        Contato novoContato = view.AdicionarContato();
        boolean contatoExiste = agenda.getContatos().stream().anyMatch(contato -> contato.equals(novoContato));

        if (contatoExiste) {
            mensagens.contatoExiste();
            throw new ContatoJaRegistradoException(novoContato.getNome());
        }
        agenda.getContatos().add(novoContato);
        mensagens.mensagemContatoCriado();
    }

    public void listarContatos() { // 2

        if (agenda.getContatos().isEmpty()){
            throw new AgendaVaziaException();
        }
        agenda.getContatos().forEach(System.out::println);
    }

    public List<Contato>buscarContato(String contato) { // 3

        List<Contato> contatosEncontrados = agenda
                .getContatos()
                .stream()
                .filter(c -> c.getNome().toLowerCase().contains(contato.toLowerCase()) || c.getSobrenome().toLowerCase().contains(contato.toLowerCase()))
                .toList();

        if (contatosEncontrados.size() == 0) {
            throw new ContatoNaoEncontradoException();
        }

        if (contato.equals("")){
            throw new ContatoNaoEncontradoException();
        }

        return contatosEncontrados ;







    }

    public void imprimirBuscarContato() {

        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        contatosEncontrados.forEach(System.out::println);

    }

    public void removerContato() { // 4
        String contatoARemover = view.buscarContato("------- REMOVER CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contatoARemover);
        Contato contatoEscolhido = view.escolherContato(contatosEncontrados);
        mensagens.confirmacaoExcluirContato();
        var resposta = scan.nextLine();
        switch(resposta){
            case Constantes.RESP_SIM -> {
                agenda.getContatos().remove(contatoEscolhido);
                mensagens.contatoRemovido();
            }
            case Constantes.RESP_NAO -> mensagens.operacaoCancelada();
            default-> throw new EntradaInvalidaOuInsuficienteException("Entrada invalida. ");
        }

    }

    public void removerTodosContatos() { // 5
        mensagens.confirmacaoExcluirContatos();
        var resposta = scan.nextLine();
        switch(resposta){
            case Constantes.RESP_SIM -> {
                agenda.getContatos().clear();
                mensagens.contatosRemovidos();
            }
            case Constantes.RESP_NAO -> mensagens.operacaoCancelada();
            default-> throw new EntradaInvalidaOuInsuficienteException("Entrada invalida. ");
        }

    }

    public List<Telefone> pegarNovoTelefone() {
        List<Telefone> telefones = new ArrayList<>();

        boolean continuarLoop;

       

        do {
            String numeroTelefone = view.pegarTelefone();
            Telefone telefone = new Telefone(numeroTelefone);
            var telefoneExiste = telefones.stream().anyMatch(t -> t.equals(telefone));
            if (telefoneExiste) {
                mensagens.mensagemTelefoneExiste();
                throw new TelefoneJaRegistradoException();
            }
            telefones.add(telefone);
            continuarLoop = view.perguntarAoUsuario("Deseja adicionar outro telefone?");

        } while (continuarLoop);



        return telefones;
    }


    public void adicionarTelefoneParaContato() { // 6
        AtomicLong i = new AtomicLong(Constantes.INDEX_FATOR);

        String contato = view.buscarContato("------- ADD TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);

        if(contatosEncontrados.size() == 1){
            System.out.println(contatosEncontrados.get(0));
        }
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Telefone> telefones = pegarNovoTelefone();

        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado))
                cont.addAllTelefones(telefones);
        });
        Telefone.removeTelefonesDuplicados(telefones);
        mensagens.mensagemTelefoneAdicionadoSucesso();

    }

    public void adicionarEnderecoParaContato() { // 7
        String contato = view.buscarContato("------- ADD ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Endereco> enderecos = view.pegarEnderecos();
        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado)) {
                cont.addAllEnderecos(enderecos);
            }
        });
        mensagens.mensagemEnderecoAdicionadoSucesso();
    }

    public void removerTelefoneParaContato() { // 8
        String contato = view.buscarContato("------- REMOVER TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Telefone telefone = view.escolherTelefoneRemover(contatoSelecionado);

        mensagens.confirmacaoExcluirTelefone();
        String resposta = scan.nextLine();
        switch(resposta){
            case Constantes.RESP_SIM -> {
                 agenda.getContatos()
                        .stream()
                        .filter(cont -> cont.equals(contatoSelecionado))
                        .findFirst()
                        .orElseThrow(ContatoNaoEncontradoException::new)
                        .getTelefones()
                        .remove(telefone);
                mensagens.telefonesApagados();
            }
            case Constantes.RESP_NAO -> mensagens.operacaoCancelada();
            default-> throw new EntradaInvalidaOuInsuficienteException("Entrada invalida. ");
        }


    }

    public void removerEnderecoParaContato() { // 9
        String contato = view.buscarContato("------- REMOVER ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Endereco endereco = view.escolherEnderecoRemover(contatoSelecionado);

        mensagens.confirmacaoExcluirEndereco();
        String resposta = scan.nextLine();
        switch(resposta){
            case Constantes.RESP_SIM -> {
                agenda.getContatos()
                        .stream()
                        .filter(cont -> cont.equals(contatoSelecionado))
                        .findFirst()
                        .orElseThrow(ContatoNaoEncontradoException::new)
                        .getEnderecos()
                        .remove(endereco);

                mensagens.enderecosApagados();
            }
            case Constantes.RESP_NAO -> mensagens.operacaoCancelada();
            default-> throw new EntradaInvalidaOuInsuficienteException("Entrada invalida. ");
        }


    }

    public void mostrarTodasInformacoesParaContato() { // 10
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTodasInformacoesParaContato(contatoSelecionado);
    }

    public void listarTodosTelefonesParaContato() {// 11
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTelefones(contatoSelecionado);

    }

    public void listarTodosEnderecosParaContato() { // 12
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarEnderecos(contatoSelecionado);
    }


    public Contato buscarContatoPorTelefone(String numeroTelefone)
            throws ContatoNaoEncontradoException { // 13

        List<Contato> contatosEncontrados = agenda
                .getContatos()
                .stream()
                .filter(cont -> cont.getTelefones()
                        .stream()
                        .anyMatch(tel -> tel.getNumeroTelefone()
                                .contains(numeroTelefone)))
                .toList();

        if (contatosEncontrados.size() == Constantes.LISTA_VAZIA)
            throw new ContatoNaoEncontradoException(numeroTelefone);

        return view.escolherContato(contatosEncontrados);


    }


    public void exibirTodasInformacoesTelefone() {
        String numeroTelefone = view.buscarContatoPorTelefone();
        Contato contato = buscarContatoPorTelefone(numeroTelefone);
        view.mostrarTodasInformacoesParaContato(contato);
    }

    public Contato buscarContatoPorEndereco(String enderecoOpcao) { // 13

        if (enderecoOpcao == null)
            throw new EntradaInvalidaOuInsuficienteException("Entrada inválida para o endereço!");

        String[] acessoEndereco = enderecoOpcao.split(",");
        List<Contato> contatosEncontrados;

        switch (acessoEndereco[Constantes.OPCAO_ENDERECO]) {
            case Constantes.LOGRADOURO -> {
                contatosEncontrados = agenda.getContatos().stream().filter(cont -> cont.getEnderecos().stream().anyMatch(lograd -> lograd.getLogradouro().contains(acessoEndereco[1]))).toList();
            }
            case Constantes.CEP -> {
                contatosEncontrados = agenda.getContatos().stream().filter(cont -> cont.getEnderecos().stream().anyMatch(cep -> cep.getCep().contains(acessoEndereco[1]))).toList();
            }
            default -> {
                contatosEncontrados = new ArrayList<>();
                System.err.println("Opção inválida. ");
            }
        }

        if (contatosEncontrados.size() == Constantes.LISTA_VAZIA)
            throw new ContatoNaoEncontradoException();

        return view.escolherContato(contatosEncontrados);
    }

    public void exibirTodasInformacoesEndereco() { // 14
        String endereco = view.buscarContatoPorEndereco();
        if (endereco == null) return;
        Contato contato = buscarContatoPorEndereco(endereco);
        view.mostrarTodasInformacoesParaContato(contato);
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

    private void aguardarRepeticaoMenu() {
        int tempoDeCarregamento = 500;
        try {
            Thread.sleep(tempoDeCarregamento);
            System.out.println();
            System.out.print("Retornando ao menu");
            Thread.sleep(tempoDeCarregamento);
            System.out.print(".");
            Thread.sleep(tempoDeCarregamento);
            System.out.print(".");
            Thread.sleep(tempoDeCarregamento);
            System.out.println(".");
            System.out.println();
            Thread.sleep(tempoDeCarregamento);
        } catch (InterruptedException ignored) {
        }
    }

}
