package util;

import java.util.List;

public class Pageable<T>{

    private int tamanhoDaPagina;
    private List<T> conteudo;

    public Pageable(int tamanho){
        this.tamanhoDaPagina = tamanho;
    }

    public Pageable(List<T> conteudo, int tamanho){
        this.conteudo = conteudo;
        this.tamanhoDaPagina = tamanho;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }

    public void setTamanhoDaPagina(int tamanhoDaPagina) {
        this.tamanhoDaPagina = tamanhoDaPagina;
    }

    public List<T> get() {
        return this.get(0);
    }

    public List<T> get(int numeroDaPagina){
        int inicio = tamanhoDaPagina*(numeroDaPagina-1);
        int fim = Math.min(inicio + tamanhoDaPagina, conteudo.size());
        return conteudo.subList(inicio, fim);
    }

}
