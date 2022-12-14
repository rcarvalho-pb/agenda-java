package model;

import java.util.Objects;

public class Endereco {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

    private TipoEndereco tipoEndereco;

    public Endereco(){}

    public Endereco(String logradouro, String cep, String numero, String cidade, String estado, TipoEndereco tipoEndereco) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" Tipo de Endereço: " +tipoEndereco.getNome());
        sb.append("\n Logradouro: " + logradouro + ", N: " + numero + ", CEP: " + cep);
        sb.append("\n Cidade: " + cidade + ", Estado: " + estado + "\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(cep, endereco.cep) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado) && tipoEndereco == endereco.tipoEndereco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, cep, numero, cidade, estado, tipoEndereco);
    }
}
