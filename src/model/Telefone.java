package model;

import java.util.Objects;

public class Telefone {
  
    private String ddd;
    private String numeroTelefone;

    public Telefone() {
    }

    // public Telefone(String ddd, String numeroTelefone) {
    //     this.ddd = ddd;
    //     this.numeroTelefone = numeroTelefone;
    // }

    // parsing phone numbers
    public Telefone(String telefoneCompleto) {
        String[] linha = telefoneCompleto.split(" ");
        this.ddd = linha[0];
        this.numeroTelefone = linha[1];
    }

    public String getDDD(){
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone){
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone telefone)) return false;
        return ddd.equals(telefone.ddd) && getNumeroTelefone().equals(telefone.getNumeroTelefone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, getNumeroTelefone());
    }

    @Override
    public String toString(){
        return "(" + ddd + ") " + numeroTelefone;
    }

  

}
