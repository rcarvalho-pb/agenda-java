package service;

import model.Agenda;
import model.Contato;
import model.Endereco;
import model.Telefone;
import view.AgendaView;

import java.util.List;
import java.util.stream.Collectors;

import common.Constantes;

public class AgendaService {

    AgendaView view = new AgendaView();
    Agenda agenda = new Agenda();

    public void menu(){
        boolean continueMenu = true;
        while(continueMenu){
            String option = (view.opcaoMenu());

            switch (option) {
                case Constantes.ADICIONAR_CONTATO-> adicionarContato(); //Tudo OK - falta testar
                case Constantes.LISTAR_CONTATO -> listarContatos(); //Tudo OK - falta testar
                case Constantes.BUSCAR_CONTATO -> imprimirBuscarContato(); //3 // já criado
                case Constantes.REMOVER_CONTATO -> removerContato(); //4
                case Constantes.REMOVER_TODOS_CONTATOS -> removerTodosContatos(); //5
                case Constantes.ADICIONAR_TELEFONE_PARA_CONTATO -> adicionarTelefoneParaContato(); //6
                case Constantes.ADICIONAR_ENDERECO_PARA_CONTATO -> adicionarEnderecoParaContato(); //7
                case Constantes.REMOVER_TELEFONE_PARA_CONTATO -> removerTelefoneParaContato();//8
                case Constantes.REMOVER_ENDERECO_PARA_CONTATO -> removerEnderecoParaContato();//9
                case Constantes.MOSTRAR_TODAS_INFORMACOES_CONTATO -> mostrarTodasInformacoesParaContato(); //10
                case Constantes.LISTAR_TODOS_TELEFONES_PARA_UM_CONTATO -> listarTodosTelefonesParaContato(); // 11
                case Constantes.LISTAR_TODOS_ENDERECOS_PARA_UM_CONTATO -> listarTodosEnderecosParaContato(); // 12
                case Constantes.EXIBIR_TODAS_INFORMACOES_TELEFONE_CONTATO_NA_AGENDA -> exibirTodasInformacoesTelefone(); //13
                case Constantes.EXIBIR_TODAS_INFORMACOES_ENDERECO_CONTATO_NA_AGENDA -> exibirTodasInformacoesEndereco(); //14
                case Constantes.EXIBIR_LISTA_CONTATOS_COM_PAGINACAO -> exibirListaContatosComPaginacao(); //15
                case Constantes.EXIBIR_LISTA_TELEFONES_COM_PAGINACAO -> exibirListaTelefonesComPaginacao(); //16
                case Constantes.EXIBIR_LISTA_ENDERECOS_COM_PAGINACAO -> exibirListaEnderecosComPaginacao(); //17
                case Constantes.EXPORTAR_TODOS_CONTACTOS_PARA_TXT -> exportarTodosContatosParaTXT(); //18
                case Constantes.IMPORTAR_TODOS_CONTACTOS_PARA_TXT -> importarTodosContatosParaTXT(); //19
                case Constantes.SAIR_PROGRAMA ->  continueMenu = sairPrograma();   
        
          }
        }  
    }
        

    public void adicionarContato(){ //1
        Contato novoContato = view.AdicionarContato();
        boolean contatoExiste = agenda.getContatos().stream().anyMatch(contato -> contato.equals(novoContato));
        if(!contatoExiste){
            agenda.getContatos().add(novoContato);
        }
        else{
            System.out.println("Contato já existe.");
        }    }

    public void listarContatos(){ //2
        agenda.getContatos().forEach(System.out::println);
    }

    public List<Contato> buscarContato(String contatoProcurado){ //3

        List<Contato> contatosEncontrados = agenda.getContatos()
            .stream()
            .filter(cont -> cont.getNome().toLowerCase().contains(contatoProcurado.toLowerCase()))
            .collect(Collectors.toList());

        if(contatosEncontrados.size() == 0) {
         System.err.println("Contato não encontrado. ");        
        }

        return contatosEncontrados;
    }

    public void imprimirBuscarContato(){
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        buscarContato(contato).forEach(System.out::println);
    }
    
    public void removerContato(){ //4
        String contatoARemover = view.buscarContato("------- REMOVER CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contatoARemover);
        Contato contatoEscolhido = view.escolherContato(contatosEncontrados);
        agenda.getContatos().remove(contatoEscolhido);
        
    }
    
    public void removerTodosContatos(){ //5
        agenda.getContatos().clear();
    }

    public void adicionarTelefoneParaContato(){ //6
        String contato = view.buscarContato("------- ADD TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        if(contatosEncontrados.size() < 1) return;
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        List<Telefone> telefones = view.pegarNovoTelefone();
        agenda.getContatos().forEach(cont -> {
                                if(cont.equals(contatoSelecionado)) {
                                    cont.setTelefones(telefones);
                                }
                            });
        
    }

    public void adicionarEnderecoParaContato(){ //7
        
        String contato = view.buscarContato("------- ADD ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Endereco endereco = view.pegarEndereco();
        agenda.getContatos().forEach(cont -> {
                                if(cont.equals(contatoSelecionado)) {
                                    cont.setEndereco(endereco);
                                }
                            });
    }


    public void removerTelefoneParaContato(){ //8
        String contato = view.buscarContato("------- REMOVER TELEFONE -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        Telefone telefone = view.escolherTelefoneRemover(contatoSelecionado);
        agenda.getContatos().stream()
                            .filter(cont -> cont.equals(contatoSelecionado))
                            .map(cont -> cont.getTelefones().remove(telefone))
                            .close();
        
    }

    public void removerEnderecoParaContato(){ //9
        String contato = view.buscarContato("------- REMOVER ENDEREÇO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        agenda.getContatos().forEach(cont -> {
                                if(cont.equals(contatoSelecionado)) {
                                    cont.setEndereco(null);
                                }
                            });
        
    }
    
    public void mostrarTodasInformacoesParaContato(){ //10
        String contato = view.buscarContato("------- BUSCAR CONTATO -------");
        List<Contato> contatosEncontrados = buscarContato(contato);
        Contato contatoSelecionado = view.escolherContato(contatosEncontrados);
        view.mostrarTodasInformacoesParaContato(contatoSelecionado);
    }
    
    public void listarTodosTelefonesParaContato(){// 11
        
    }
    
    public void listarTodosEnderecosParaContato(){ // 12
        
    }
    

    public void exibirTodasInformacoesTelefone(){ //13
  
    }

   public void exibirTodasInformacoesEndereco(){ //14
  
    }


   public void exibirListaContatosComPaginacao(){ //15
  
    }


   public void exibirListaTelefonesComPaginacao(){ //16
  
    }


    public void exibirListaEnderecosComPaginacao(){ //17
  
    }

    public void exportarTodosContatosParaTXT(){ //18
  
    }


    public void importarTodosContatosParaTXT(){ //19
  
    }

  
    public boolean sairPrograma(){ //20
        return view.sairPrograma();
    }

    public Boolean existeContato(){
        return true;

    }
  

}
    
