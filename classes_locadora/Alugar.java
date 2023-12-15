package classes_locadora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import classes.Filme;

public class Alugar {
    private int limite; //delimitador de quantos filmes o cliente pode alugar, o max é 5 filmes por cliente
    private Filme alugado = new Filme(null, null, null, null, 0); //guarda o filme alugado, um filme por vez;

    //atributos para os metodos funcionarem
    private double saldo;
    private int genero;
    
    //lista dos filmes que usuario deseja ver
    private List<Filme> filmes = new ArrayList<Filme>();

    //construtor
    public Alugar() {
        this.filmes = new ArrayList<Filme>();
    }

    //get e set de limite
    public void setLimite(int limite){ this.limite = limite; }
    public int getLimite(){ return this.limite; }

    //é preciso pegar o saldo que o cliente tem para que ele possa alugar os filmes
    public void setSaldo(double saldo) { this.saldo = saldo; }
    private void pagar(double saldo) { this.saldo -= saldo; }
    public double getSaldo() { return saldo; }

    //convertendo o inteiro da escolha de genero para posteriormente pegar o diretorio dos filmes
    public void setGenero(int g){ this.genero = g; }
    //pegando o diretorio dos filmes de acordo com o genero escolhido
    private String diretorio(){
        switch(genero){
            case 1:
                return "filmesTerror.ser";
            case 2:
                return "filmesRomance.ser";
            case 3:
                return "filmesComedia.ser";
            case 4:
                return "filmesAcao.ser";
            case 5:
                return "filmesCrimeSus.ser";
            case 6:
                return "filmesLancamento.ser";
            default:
                return null;
        }
    }
    
    //mostrando os filmes de acordo com o genero selecionado
    public void mostrarFilmes() throws Exception {
        //limpando a lista de filmes para amarzenar outros
        this.filmes.clear();
        this.filmes = desserializar();

        //agora imprimindo os filmes do determinado genero
        System.out.print("\u001b[34m");
        System.out.printf("%-60s%-5s%-10s%-8s%-8s%-8s\n", "TÍTULO", "ID", "GÊNERO", "CLASS.", "AVAL.", "PREÇO");
        System.out.print("\u001b[37m");
        System.out.println("================================================================================================================");
        System.out.print("\u001b[32m");
        int index = 0;
        for(Filme f:filmes){
            String[] filme = {f.getTitulo(), Integer.toString(index), f.getGenero().getLabel(), f.getClassificacao(), f.getAvaliacao(), 
                              f.getPrecoFormat()};
            System.out.printf("%-60s%-5s%-10s%-8s%-8s%-8s\n", filme[0], filme[1], filme[2], filme[3], filme[4], filme[5]);       
            index++;
        }
        System.out.print("\u001b[37m");
    }

    //alugando pelo ID do filme desejado
    public boolean alugar(int idFilme) {
        double custo = this.filmes.get(idFilme).getPreco();

        if(getSaldo() < custo){
            return false;
        } else {
            this.alugado = null;
            this.alugado = this.filmes.get(idFilme);
            pagar(custo);
            // setLimite(getLimite() + 1);
            System.out.println(this.alugado.toString());
            return true;
        }
    }

    //desserializando o arquivo de filmes de acordo com o genero escolhido pelo cliente
    public List<Filme> desserializar(){
        List<Filme> filmes = new ArrayList<Filme>();
        Path pathClientes = Paths.get(diretorio());
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            filmes = (List<Filme>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    //retornando os filmes que foram alugados
    public Filme getAlugados(){ return this.alugado; }

}