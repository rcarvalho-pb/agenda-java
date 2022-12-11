package model;

import java.util.List;

public class Contato {
    
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    private String email;

    public Contato(String nome, String sobrenome, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public Contato(String nome, String sobrenome, List<Telefone> telefones, List<Endereco> enderecos, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.email = email;
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

    public void setTelefones(List<Telefone> telefones){
        this.telefones = telefones;
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

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contato other = (Contato) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString(){
        if (telefones != null && enderecos != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(nome + " " + sobrenome + ", " + email);
            sb.append(enderecos);
            sb.append(telefones);
            return sb.toString();
        }
        if (telefones == null && enderecos != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(nome + " " + sobrenome + ", " + email);
            sb.append(enderecos);
            return sb.toString();
        }
        if (telefones != null && enderecos == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(nome + " " + sobrenome + ", " + email);
            sb.append(telefones);
            return sb.toString();
        }

        return nome + " " + sobrenome + ", " + email;

    }

  
}
