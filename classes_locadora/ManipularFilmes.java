package classes_locadora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import classes.Filme;
import enums.Generos;
import interfaces.Serializacao;

public class ManipularFilmes implements Serializacao {
    private String genero;
    private List<Filme> filmes = new ArrayList<Filme>();
    private Filme filmeRm;

    public ManipularFilmes(){
        this.filmes = new ArrayList<Filme>();
    }

    //vai gerar um genero para depois pegar o diretorio de onde estao os filmes
    public void setGenero(int g){
        switch (g) {
            case 1:
                genero = Generos.TERROR.getLabel();
                break;
            case 2:
                genero = Generos.ROMANCE.getLabel();
                break;
            case 3:
                genero = Generos.COMEDIA.getLabel();
                break;
            case 4:
                genero = Generos.ACAO.getLabel();
                break;
            case 5:
                genero = Generos.CRIME_SUSPENSE.getLabel();
                break;   
            case 6:
                genero = Generos.LANCAMENTOS.getLabel();
                break;
            default:
                genero = null;
        }
    }

    //vai pegar o diretorio do aqruivo .ser do filmes do genero selecionado
    private String diretorio(){
        switch(genero){
            case "Terror":
                return "filmesTerror.ser";
            case "Romance":
                return "filmesRomance.ser";
            case "Comedia":
                return "filmesComedia.ser";
            case "Acao":
                return "filmesAcao.ser";
            case "Crime":
                return "filmesCrimeSus.ser";
            case "Lancamento":
                return "filmesLancamento.ser";
            default:
                return null;
        }
    }

    //gerar genero para colocar na lista de filems
    public Generos gerarGenero(){
        String g = genero;
        if(g.equals("Terror")){
            return Generos.TERROR;
        } else if(g.equals("Romance")){
            return Generos.ROMANCE;
        } else if(g.equals("Comedia")){
            return Generos.COMEDIA;
        } else if(g.equals("Acao")){
            return Generos.ACAO;
        } else if(g.equals("Crime")){
            return Generos.CRIME_SUSPENSE;
        } else if(g.equals("Lancamento")){
            return Generos.LANCAMENTOS;
        } else {
            return null;
        }
    }

    //cadastrando um novo filme na locadora
    public void addFilme(Filme f){ 
        this.filmes.add(f);
    }

    //retirando filmes do catalogo da locadora
    public void rmFilme(int index) {
        if(this.filmes.get(index) == null){
            System.out.println("ID DE FILME INVÁLIDO!");
        }
        System.out.println(this.filmes.get(index).toString());
        this.filmeRm = this.filmes.get(index);
        this.filmes.remove(index);
        System.out.println("\u001b[32mREMOVIDO COM SUCESSO!\u001b[37m");
    }
    public Filme getRmFilme(){ return this.filmeRm; }

    //vai imprimir os filmes do determinado genero que foi escolhido pelo funcionario
    public void imprimirFilmes(){
        //agora imprimindo os filmes do determinado genero que o funcionario escolher
        System.out.print("\u001b[34m");
        System.out.printf("%-60s%-5s%-10s%-8s%-8s%-8s\n", "TÍTULO", "ID", "GÊNERO", "CLASS.", "AVAL.", "PREÇO");
        System.out.print("\u001b[37m");
        System.out.println("================================================================================================================");
        System.out.print("\u001b[32m");
        int index = 0;
        for(Filme f:this.filmes){
            String[] filme = {f.getTitulo(), Integer.toString(index), f.getGenero().getLabel(), f.getClassificacao(), f.getAvaliacao(), 
                f.getPrecoFormat()};
            System.out.printf("%-60s%-5s%-10s%-8s%-8s%-8s\n", filme[0], filme[1], filme[2], filme[3], filme[4], filme[5]);       
            index++;
        }
        System.out.print("\u001b[37m");
        System.out.println("================================================================================================================");
    }

    //serializando a lista de filmes com as alterações de cadastro ou remoção
    @Override
    public void serializar(){
        Path pathCli = Paths.get(diretorio());
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathCli, StandardOpenOption.CREATE))) {
            oos.writeObject(this.filmes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //desserializando os filmes desejados pelo funcionario
    @Override
    public void desserializar(){
        List<Filme> filmes_aux = new ArrayList<Filme>();
        Path pathClientes = Paths.get(diretorio());
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            filmes_aux = (List<Filme>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.filmes = filmes_aux;
    }

}