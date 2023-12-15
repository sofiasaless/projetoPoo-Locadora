package classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Filme> filmes_historico;

    public Cliente(String nome, String id, String senha) {
        super(nome, id, senha);
        this.filmes_historico = new ArrayList<Filme>();
    }

    //o metodo abaixo toma forma como uma função de depositar saldo na conta do cliente para que ele possa alugar filmes
    @Override
    public void incrementarSaldo(double v) {
        setSaldo(getSaldo() + v);
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return "Cliente [Nome=" + nome + ", Id=" + id + ", Senha=" + senha + ", Saldo=" + d.format(saldo) + "]";
    }

    //o metodo abaixo vai adicionar na lista filmes que estão alugados por ele no momento, portanto deve haver um limite
    @Override
    public void adcFilmeIn(Filme f){
        super.adcFilmeIn(f);  
        this.filmes_historico.add(f);
    }

    //para o cliente, o metodo abaixo vai funcionar como uma lista de filmes que ele já devolveu para locadora;
    @Override
    public void adcFilmeOut(Filme f){
        String titulo = f.getTitulo();
        for(int i = 0; i < this.filmes_in.size(); i++){
            if(this.filmes_in.get(i).getTitulo().equals(titulo)){
                this.filmes_in.remove(i);
                super.adcFilmeOut(f);
                return;
            }
        }
        //!se passar pelo looping de cima é porque o filme removido nao está nos alugados no cliente 
    }

    //todos os filmes que o cliente ja alugou serão armazenados no lista de filmes_historico;
    public List<Filme> getFilmesHistorico(){ return this.filmes_historico; }
    public String showFilmesHistorico(){
        String s = "";
        for(Filme f:this.filmes_historico){
            s += f + "\n";
        }
        return s;
    }

}