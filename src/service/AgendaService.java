package service;

import model.Agenda;
import model.Contato;
import model.Endereco;
import model.Telefone;
import view.AgendaView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.Constantes;
import exception.ContatoJaRegistradoException;

public class AgendaService {

    int contador = 0;
    AgendaView view = new AgendaView();
    Agenda agenda = new Agenda();

    public void menu() {
        boolean continueMenu = true;
        while (continueMenu) {
            String option = (view.opcaoMenu());

            switch (option) {
                case Constantes.ADICIONAR_CONTATO -> adicionarContato(); // Tudo OK - falta testar
                case Constantes.LISTAR_CONTATO -> listarContatos(); // Tudo OK - falta testar
                case Constantes.BUSCAR_CONTATO -> imprimirBuscarContato(); // 3 // já criado
                case Constantes.REMOVER_CONTATO -> removerContato(); // 4
                case Constantes.REMOVER_TODOS_CONTATOS -> removerTodosContatos(); // 5
                case Constantes.ADICIONAR_TELEFONE_PARA_CONTATO -> adicionarTelefoneParaContato(); // 6
                case Constantes.ADICIONAR_ENDERECO_PARA_CONTATO -> adicionarEnderecoParaContato(); // 7
                case Constantes.REMOVER_TELEFONE_PARA_CONTATO -> removerTelefoneParaContato();// 8
                case Constantes.REMOVER_ENDERECO_PARA_CONTATO -> removerEnderecoParaContato();// 9
                case Constantes.MOSTRAR_TODAS_INFORMACOES_CONTATO -> mostrarTodasInformacoesParaContato(); // 10
                case Constantes.LISTAR_TODOS_TELEFONES_PARA_UM_CONTATO -> listarTodosTelefonesParaContato(); // 11
                case Constantes.LISTAR_TODOS_ENDERECOS_PARA_UM_CONTATO -> listarTodosEnderecosParaContato(); // 12
                case Constantes.EXIBIR_TODAS_INFORMACOES_TELEFONE_CONTATO_NA_AGENDA -> exibirTodasInformacoesTelefone(); // 13
                case Constantes.EXIBIR_TODAS_INFORMACOES_ENDERECO_CONTATO_NA_AGENDA -> exibirTodasInformacoesEndereco(); // 14
                case Constantes.EXIBIR_LISTA_CONTATOS_COM_PAGINACAO -> exibirListaContatosComPaginacao(); // 15
                case Constantes.EXIBIR_LISTA_TELEFONES_COM_PAGINACAO -> exibirListaTelefonesComPaginacao(); // 16
                case Constantes.EXIBIR_LISTA_ENDERECOS_COM_PAGINACAO -> exibirListaEnderecosComPaginacao(); // 17
                case Constantes.EXPORTAR_TODOS_CONTACTOS_PARA_TXT -> exportarTodosContatosParaTXT(); // 18
                case Constantes.IMPORTAR_TODOS_CONTACTOS_PARA_TXT -> importarTodosContatosParaTXT(); // 19
                case Constantes.SAIR_PROGRAMA -> continueMenu = sairPrograma();

            }
        }
    }

    public void adicionarContato() { // 1
        Contato novoContato = view.AdicionarContato();
        boolean contatoExiste = agenda.getContatos().stream().anyMatch(contato -> contato.equals(novoContato));

        if (contatoExiste) {
            // TODO trocar retorno por lancamento de exception
            throw new ContatoJaRegistradoException(novoContato.getNome());
        } 
        
        agenda.getContatos().add(novoContato);
    }

    public void listarContatos() { // 2
        agenda.getContatos().forEach(System.out::println);
    }

    public List<Contato> buscarContato(String contato) { // 3

        List<Contato> contatosEncontrados = agenda
                .getContatos()
                .stream()
                .filter(c -> c.getNome().toLowerCase().contains(contato.toLowerCase()) || c.getSobrenome().toLowerCase().contains(contato.toLowerCase()))
                .collect(Collectors.toList());

        if (contatosEncontrados.size() == 0) {
            System.err.println("Contato não encontrado. ");
        }

        // List<Contato> contatos = listaContatos.stream().filter(cont -> cont.getPessoa().getNomeCompleto().toLowerCase().contains(contato.toLowerCase())).collect(Collectors.toList());
        // if(contatos.size() == 0) System.err.println("Contato não encontrado. ");

        return contatosEncontrados;
    }

    

    public void imprimirBuscarContato() {
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        buscarContato(contato).forEach(cont -> {
            System.out.println((contador + 1) + ": " + cont);
            contador++;
        });
        contador = 0;
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

    public void adicionarTelefoneParaContato() { // 6
        String contato = view.buscarContato("------- ADD TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        if (contatosEncontrados.size() < 1)
            return;
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Telefone> telefones = view.pegarNovoTelefone();
        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado)) {
                cont.getTelefones().addAll(telefones);
            }
        });

    }

    public void adicionarEnderecoParaContato() { // 7

        String contato = view.buscarContato("------- ADD ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Endereco> enderecos = view.pegarEnderecos();
        agenda.getContatos().forEach(cont -> {
            if (cont.equals(contatoSelecionado)) {
                cont.setEnderecos(enderecos);
            }
        });
    }

    public void removerTelefoneParaContato() { // 8
        String contato = view.buscarContato("------- REMOVER TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Telefone telefone = view.escolherTelefoneRemover(contatoSelecionado);
        agenda.getContatos().stream()
                .filter(cont -> cont.equals(contatoSelecionado))
                .map(cont -> cont.getTelefones().remove(telefone))
                .close();

    }

    public void removerEnderecoParaContato() { // 9
        String contato = view.buscarContato("------- REMOVER ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Endereco endereco = view.escolherEndereco(contatoSelecionado);
        agenda.getContatos().stream()
                .filter(cont -> cont.equals(contatoSelecionado))
                .map(cont -> cont.getEnderecos().remove(endereco))
                .close();

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

    public Contato buscarContatoPorTelefone(String numeroTelefone) { // 13

        Contato contato;
        List<Contato> contatosEncontrados = agenda.getContatos().stream()
        .filter(cont -> cont.getTelefones().stream().anyMatch(tel -> tel.getNumeroTelefone().contains(numeroTelefone))).collect(Collectors.toList());
            
        if (contatosEncontrados.size() == 0) {
            System.err.println("Contato não encontrado. ");
            return null;
        }
        else{
            
            contato = view.escolherContato(contatosEncontrados);
        }

        return contato;
    }

    public void exibirTodasInformacoesTelefone(){
        String numeroTelefone = view.buscarContatoPorTelefone();
        Contato contato = buscarContatoPorTelefone(numeroTelefone);
        view.mostrarTodasInformacoesParaContato(contato);
    }

    public Contato buscarContatoPorEndereco(String enderecoOpcao) { // 13
        if(enderecoOpcao == null){
            System.err.println("Opção inválida. ");
            return null;
        }
        String[] apoio = enderecoOpcao.split(",");
        Contato contato;
        List<Contato> contatosEncontrados;
        switch (apoio[0]){
            case "1" -> {
                contatosEncontrados = agenda.getContatos().stream()
                  .filter(cont -> cont.getEnderecos().stream().anyMatch(lograd -> lograd.getLogradouro().contains(apoio[1]))).collect(Collectors.toList());
            }
            case "2" -> {
                contatosEncontrados = agenda.getContatos().stream()
                  .filter(cont -> cont.getEnderecos().stream().anyMatch(cep -> cep.getCep().contains(apoio[1]))).collect(Collectors.toList());
            }
            default -> {
                contatosEncontrados = new ArrayList<>();
                System.err.println("Opção inválida. ");
            }
        }
        
            
        if (contatosEncontrados.size() == 0) {
            System.err.println("Contato não encontrado. ");
            return null;
        }
        else{
            
            contato = view.escolherContato(contatosEncontrados);
        }

        return contato;
    }

    public void exibirTodasInformacoesEndereco() { // 14
        String endereco = view.buscarContatoPorEndereco();
        if(endereco == null) return;
        Contato contato = buscarContatoPorEndereco(endereco);
        view.mostrarTodasInformacoesParaContato(contato);
    }

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
