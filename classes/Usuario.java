package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Serializable {    
    //variaveis de definição do usuario
    protected String nome;
    protected String id;
    protected String senha;
    //atributo que vai ser incrementado com polimorfismo
    protected double saldo;
    //historico de filmes do usuario
    protected List<Filme> filmes_in;
    protected List<Filme> filmes_out;

    //construtor
    public Usuario(String nome, String id, String senha) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
        this.filmes_in = new ArrayList<Filme>();
        this.filmes_out = new ArrayList<Filme>();
    }

    //gets (nao há necessidade de sets, devido ao construtor)
    public String getNome() { return nome; }
    public String getId() { return id; }
    public String getSenha() { return senha; }
    //saldo
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    //metodos abstratos
    /* esse metodo vai ser incrementado e admitido de formas diferentes nas classes filhas cliente e funcionario */
    public abstract void incrementarSaldo(double v);

    //metodos que vao ser implementados com polimorfismo pelas classes filhas
    public void adcFilmeIn(Filme f){ this.filmes_in.add(f); }
    public void adcFilmeOut(Filme f){ this.filmes_out.add(f); }
    //retornando os filmes salvos
    public List<Filme> getFilmesIn(){ return this.filmes_in; }
    public List<Filme> getFilmesOut(){ return this.filmes_in; }

    //mostrando as duas listas de filmes
    public String showFilmesIn(){
        String s = "";
        for(Filme f:this.filmes_in){
            s += f + "\n";
        }
        return s;
    }
    public String showFilmesOut(){
        String s = "";
        for(Filme f:this.filmes_out){
            s += f + "\n";
        }
        return s;
    }
    
    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", id=" + id + ", senha=" + senha + ", saldo=" + saldo + "]";
    }

}