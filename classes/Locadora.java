package classes;

import java.util.List;

public abstract class Locadora {
    protected Usuario perfilLogado;

    //metodo que vai ser aplicado polimorfismo nas classes filhas
    public void setPerfilLogado(Usuario usu){ this.perfilLogado = usu; }
    
    //metodos abstratos, todos implementados com polimorfismo
    public abstract void setFilmeIn(Filme f);
    public abstract void setFilmeOut(Filme f);
    public abstract List<Filme> getFilmesIn();
    public abstract List<Filme> getFilmesOut();
    
    //vendo o perfil do usuario
    public void verPerfil(){
        System.out.println(this.perfilLogado);
    }
    
    public abstract String showFilmeIn();
    public abstract String showFilmeOut();

    //incrementando o saldo do usuario de acordo com o parametro passado
    public void depositar(double v){
        this.perfilLogado.setSaldo(v);
    }

}