package classes_locadora;

import java.util.List;

import classes.Filme;

public class Devolver {
    private Filme filme_devolvido; //guarda o filme devolvido e retorna para que possa ser retirado da lista de alugados do cliente
    
    //devolvendo o filme de acordo com o id
    public boolean devolver(int id, List<Filme> filmes) {
        if(filmes.get(id) == null){
            return false;
        } else {
            this.filme_devolvido = filmes.get(id);
            System.out.println(filmes.get(id).toString());
            return true;
        }
    }

    //retornando os devolvidos
    public Filme getFilmeDevolvido() { return filme_devolvido; }
    
}
