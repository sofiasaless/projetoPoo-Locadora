package classes;

import java.io.Serializable;
import java.text.DecimalFormat;

import enums.Generos;

public class Filme implements Serializable {
    private String titulo;
    private String classificacao;
    private Generos genero;
    private double preco;
    private String avaliacao;

    public Filme(String titulo, Generos genero, String classificacao, String avaliacao, double preco) {
        this.genero = genero;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.preco = preco;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getClassificacao() {
        return classificacao;
    }
    public Generos getGenero() {
        return genero;
    }
    public double getPreco() {
        return preco;
    }
    public String getPrecoFormat(){
        DecimalFormat d = new DecimalFormat("0.00");
        return d.format(this.preco);
    }
    public String getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {
        return "Filme{" + titulo + ", Classificação:" + classificacao + ", Genero:" + genero + ", Preço:" + getPrecoFormat() + '}';
    }
    
}
