package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contato {
    
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    private String email;

    public Contato(String nome, String sobrenome, String email) {
        telefones = new ArrayList<>();
        enderecos = new ArrayList<>();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public Contato(String nome, String sobrenome, List<Telefone> telefones, List<Endereco> enderecos, String email) {
        this(nome, sobrenome, email);
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void addAllTelefones(List<Telefone> telefones){
        this.telefones.addAll(telefones);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addAllEnderecos(List<Endereco> enderecos) {
        this.enderecos.addAll(enderecos);
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato contato)) return false;
        return Objects.equals(getNome(), contato.getNome()) && Objects.equals(getSobrenome(), contato.getSobrenome()) && Objects.equals(getEmail(), contato.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getSobrenome(), getEmail());
    }

    @Override
    public String toString(){

        return "Nome: "+ nome + ", Sobrenome: " + sobrenome + ", Email: " + email;
    }


}
