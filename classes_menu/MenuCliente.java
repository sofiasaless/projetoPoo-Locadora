package classes_menu;

import java.text.DecimalFormat;
import java.util.List;

import classes.Filme;

public class MenuCliente extends Menu {
    private int escolhaGeneroFilme;
    private int escolhaAlugarVoltar;
    private int escolhaDevolverVoltar;

    //menu de cadastro
    public void menuCliente1(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        print(cyan() +"   NOME: ");
    }
    public void menuCliente2(){
        print("   ID: ");
    }
    public void menuCliente3(){
        print("   SENHA: " + white());
    }

    //menu da locadora
    public void menuLocadora(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        println(cyan());
        println("   [1] Ver perfil");
        println("   [2] Inserir saldo");
        println("   [3] Alugar filmes");
        println("   [4] Devolver filmes");
        print(red());
        println("   [5] Encerrar");
        println(white());
        println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛");
        // print("$Digite... ");
        print(yellow() + "$Digite... " + white());        
    }

    /* MENU DOS ITENS DA LOCADORA */
    //menu inserir saldo
    public void menuInserirSaldo(double saldoAtual){
        DecimalFormat d = new DecimalFormat("0.00");        
        linhaIni();
        println(yellow() + "   #SALDO ATUAL NA CARTEIRA: " + d.format(saldoAtual));
        print(white());
        linhaMeio();
        print("   -> Valor a ser depositado: R$");
    }

    //menu alugar filmes
    public void menuGenerosFilmes(){
        linhaIni();
        println(cyan() + "   ESCOLHA O GENERO DO FILME QUE DESEJA ALUGAR!" + white());
        linhaMeio();
        print(cyan());
        println("   [1] Terror");
        println("   [2] Romance");
        println("   [3] Comedia");
        println("   [4] Acao");
        println("   [5] Crime suspense");
        println("   [6] Lançamentos");
        print(red());
        println("   [7] VOLTAR AO MENU");
        print(white());
        linhaFim();
        print(yellow() + "$Digite... " + white());
    }
    public void menuAlugarVoltar(){
        linhaIni();
        print(cyan());
        println("   [1] Começar a alugar filmes");
        println("   [2] Ver outros generos");
        print(white());
        linhaFim();
        print(yellow() + "$Digite... " + white());
    }
    public void menuAlugar(){
        linhaIni();
        println(red() + "   $PARA ALUGAR DIGITE O ID DO FILME ");
        println("   $DIGITE # PARA VOLTAR AO MENU " + white());
        linhaFim();
    }
    public void menuAlugar1(){
        print(yellow() + "$Digite o ID do flme que quer alugar... " + white());            
    }

    //menu para devolver filmes
    public void menuDevolver(List<Filme> alugados){
        linhaIni();
        print(cyan());
        println("   -> SEUS FILMES ALUGADOS NO MOMENTO <-    ");
        println(yellow());
        for(int i = 0; i < alugados.size(); i++){
            println("[ID: " + i + "] " + alugados.get(i).toString());
        }
        println(white());
        linhaMeio();
    }
    public void menuDevolver1(){
        print(cyan());
        println("   [1] Começar a devolver filmes");
        print(red());
        println("   [2] VOLTAR");
        print(white());
        linhaFim();
        print(yellow() + "$Digite... " + white());
    }
    public void menuDevolver2(){
        linhaIni();
        println(red() + "   $PARA DEVOLVER DIGITE O ID DO FILME ");
        println("   $DIGITE # PARA VOLTAR AO MENU " + white());
        linhaFim();
        print(yellow() + "$Digite... " + white());
    }


    /* GETTERS E SETTERS */
    //escolhendo genero de filme
    public int getEscolhaGeneroFilme() { return escolhaGeneroFilme; }
    public void setEscolhaGeneroFilme(int escolhaGeneroFilme) { this.escolhaGeneroFilme = escolhaGeneroFilme; }
    //começar a alugar ou voltar
    public int getEscolhaAlugarVoltar() { return escolhaAlugarVoltar; }
    public void setEscolhaAlugarVoltar(int escolhaAlugarVoltar) { this.escolhaAlugarVoltar = escolhaAlugarVoltar; }
    //começar a devolver ou voltar
    public int getEscolhaDevolverVoltar() { return escolhaDevolverVoltar; }
    public void setEscolhaDevolverVoltar(int escolhaDevolverVoltar) { this.escolhaDevolverVoltar = escolhaDevolverVoltar; }
   
}