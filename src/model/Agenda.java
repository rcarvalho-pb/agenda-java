package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    
  //private String nome;
  private List<Contato> contatos;

  public Agenda(/*String nome*/) {
    //this.nome = nome;
    contatos = new ArrayList<>();
  }

  public List<Contato> getContatos(){
    return contatos;
  }
}