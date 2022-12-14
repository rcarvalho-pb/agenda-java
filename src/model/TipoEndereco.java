package model;

import exception.EntradaInvalidaOuInsuficienteException;
import util.Constantes;

public enum TipoEndereco {

    RESIDENCIAL("Residencial", "1"),
    COMERCIAL("Comercial", "2"),
    CAIXA_POSTAL("Caixa postal", "3");

    public String nome;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    TipoEndereco(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static TipoEndereco pegarEndereco(String id) {
        switch (id) {
            case Constantes.TIPO_RESIDENCIAL -> {
                return RESIDENCIAL;
            } case Constantes.TIPO_COMERCIAL ->   {
                return COMERCIAL;
            } case Constantes.TIPO_CAIXAPOSTAL -> {
                return CAIXA_POSTAL;
            }
            default -> throw new EntradaInvalidaOuInsuficienteException("Entrada inválida!");
        }
    }
}