package model.enums;

import exception.EntradaInvalidaOuInsuficienteException;
import util.Constantes;

public enum TipoTelefone {
    RESIDENCIAL("Residencial"),
    CELULAR("Celular"),
    COMERCIAL("Comercial"),
    FIXO("Fixo");

    public String nome;

    TipoTelefone(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static TipoTelefone pegarTipoTelefone(String id) {
        switch (id) {
            case Constantes.TIPO_RESIDENCIAL -> {
                return RESIDENCIAL;

            }
            case Constantes.TIPO_COMERCIAL -> {
                return COMERCIAL;
            }
            case Constantes.TIPO_CELULAR -> {
                return CELULAR;

            }
            case Constantes.TIPO_FIXO -> {
                return FIXO;
            }
            default -> throw new EntradaInvalidaOuInsuficienteException("Entrada inválida!");
        }

    }
}