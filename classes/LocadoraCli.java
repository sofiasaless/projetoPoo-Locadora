package classes;

import java.text.DecimalFormat;
import java.util.List;

import classes_locadora.Alugar;
import classes_locadora.Devolver;

//CLASSE PARA O CLIENTE MANIPULAR FILMES, PODENDO ALUGAR E DEVOLVER
public class LocadoraCli extends Locadora {
    Alugar alugar = new Alugar();
    Devolver devolver = new Devolver();

    //construtor
    public LocadoraCli(){
        this.perfilLogado = new Cliente(null, null, null);
    }

    /* FILMES MANIPULADOS PELO CLIENTE NA LOCADORA */
    //perfil cliente que vai estar logado na locadora
    @Override
    public void setPerfilLogado(Usuario usu) { this.perfilLogado = (Cliente) usu; }
    public Cliente getCliente() { return(Cliente) perfilLogado; }

    //set, get e show de filmes que o cliente for alugando na locadora
    @Override
    public void setFilmeIn(Filme f) { getCliente().adcFilmeIn(f); } //automaticamente vai adicionar esse filme no historico de filmes já alugados
    @Override
    public List<Filme> getFilmesIn(){ return getCliente().getFilmesIn(); }
    @Override
    public String showFilmeIn(){ return getCliente().showFilmesIn(); }

    //set, get e show de filmes que o cliente devolveu para a locadora
    @Override
    public void setFilmeOut(Filme f) { getCliente().adcFilmeOut(f); }
    @Override
    public List<Filme> getFilmesOut(){ return getCliente().getFilmesOut(); }
    @Override
    public String showFilmeOut(){ return getCliente().showFilmesOut(); }

    //get e show do historico de todos os filmes que o cliente ja alugou, (nao ha necessidade de set);
    public List<Filme> getFilmesHistorico(){ return getCliente().getFilmesHistorico(); }
    public String showFilmesHitorico(){ return getCliente().showFilmesHistorico(); }


    /* AÇÕES DO CLIENTE NA LOCADORA */

    //incrementando saldo na carteira do cliente
    @Override
    public void depositar(double v){
        getCliente().incrementarSaldo(v);
    }

    //mostrando os filmes disponiveis de acordo com o genero que o cliente escolheu
    public void filmesDisponiveis(int genero) throws Exception {
        alugar.setGenero(genero);
        alugar.mostrarFilmes();
    }
    
    public void alugarFilme(int idFilme) throws Exception {        
        //se ja tiver no limite nao vai começar a alugar
        if(getFilmesIn().size() == 5){
            throw new Exception("\u001b[31mLimite de filmes alugados atingido! Tente devolver algum para alugar outro :) \u001b[37m");
        }

        //passar os atributos ao começar a alugar na locadora (saldo, limite)
        alugar.setSaldo(getCliente().getSaldo());
        alugar.setLimite(getCliente().getFilmesIn().size());

        //agora alugando o filme
        if(alugar.alugar(idFilme)){
            //checando se o cliente ja alugou esse filme
            for(Filme f:getCliente().getFilmesIn()){
                if(alugar.getAlugados().equals(f)){
                    throw new Exception("\u001b[31mVOCÊ JÁ ESTÁ COM ESSE FILME! TENTE ALUGAR OUTRO! \u001b[37m");
                }
            }
            //se passar la em cima é porque o filme nao esta nos alugados do cliente, podendo atualizar os atributos do cliente
            getCliente().setSaldo(alugar.getSaldo());
            setFilmeIn(alugar.getAlugados());
            throw new Exception("\u001b[32mALUGADO COM SUCESSO\u001b[37m!");
        } else {
            throw new Exception("\u001b[31mSALDO INSUFICIENTE PARA ALUGAR O FILME :( \u001b[37m");
        }
    }

    public void devolverFilme(int idFilme) throws Exception{
        //setando o id do filme que vai ser devolvido
        if(devolver.devolver(idFilme, getFilmesIn())) {
            setFilmeOut(devolver.getFilmeDevolvido());
            System.out.print("\u001b[32mFilme devolvido com sucesso\u001b[37m");
            throw new Exception("!");
        } else {
            throw new Exception("\u001b[31mFilme inválido ou já devolvido!\u001b[37m");
        }
    }

    @Override
    public void verPerfil(){
        String nome = getCliente().getNome();
        String id = getCliente().getId();
        double saldo = getCliente().getSaldo();
        DecimalFormat d = new DecimalFormat("0.00");
        System.out.println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕\u001b[36m");
        System.out.println("   -> Nome do cliente: " + nome);
        System.out.println("   -> Id do cliente: " + id);
        System.out.println("   -> Saldo na carteira: " + d.format(saldo));
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("   -> SEUS FILMES ALUGADOS NO MOMENTO <-    ");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("\u001b[35m");
        System.out.println(showFilmeIn());
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("   -> SEUS FILMES DEVOLVIDOS <-    ");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("\u001b[35m");
        System.out.println(showFilmeOut());
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("   -> HISTORICO DE TODOS FILMES ALUGADOS <-    ");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("\u001b[35m");
        System.out.println(showFilmesHitorico());
        System.out.print("\u001b[37m");
        System.out.println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛");
    }

}