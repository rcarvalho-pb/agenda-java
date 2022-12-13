package model;

public class Endereco {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;

    public Endereco(){}

    public Endereco(String logradouro, String cep, String numero, String cidade, String estado) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
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
        sb.append(" Logradouro: " + logradouro + ", N: " + numero + ", CEP: " + cep);
        sb.append("\n Cidade: " + cidade + ", Estado: " + estado + "\n");
        return sb.toString();
    }
}
