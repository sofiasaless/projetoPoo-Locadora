package classes_menu;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import classes.Funcionario;
import enums.Generos;

public class MenuFuncionario extends Menu {
    private int escolhasVerPerfil;
    private int escolhaExcluirCliVoltar;
    private int escolhaGeneroCad;
    private int escolhaGeneroRm;
    private int escolhaRemoverSair;
    private int classficacao;
    private int genero;

    //menu de cadastro ou entrar
    public void menuFuncio1(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        print(cyan() +"   NOME: ");
    }
    public void menuFuncio2(){
        print("   ID: ");
    }
    public void menuFuncio3(){
        print("   SENHA: ");
    }
    public void menuFuncio4(){
        print("   CHAVE DE ACESSO: " + white());
    }

    //menu da locadora
    public void menuLocadora(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        println(cyan());
        println("   [1] Ver perfil");
        println("   [2] Listar clientes");
        println("   [3] Cadastrar novo filme");
        println("   [4] Remover filme do catálogo");
        println("   [5] Bater ponto");
        print(red());
        println("   [6] Encerrar");
        println(white());
        println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛");
        // print("$Digite... ");
        print(yellow() + "$Digite... " + white());
    }

    //menu para ver o perfil do funcionario
    public void menuVerPerfil(Funcionario f){
        String nome = f.getNome();
        String id = f.getId();
        int diasTrabalhados = f.getDiasTrabalhados();
        linhaIni();
        print(cyan());
        println("   -> Nome do funcionario: " + nome);
        println("   -> Id do funcionario: " + id);
        println("   -> Dias trabalhados: " + diasTrabalhados);
        println("   -> Rendimento atual: " + f.getSaldo());
        print(white());
        linhaIni();
        println("   -> FILMES QUE " + f.getNome() + " CADASTROU NO SISTEMA <-    ");
        linhaMeio();
        print(magent());
        println(f.showFilmesIn());
        print(white());
        linhaMeio();
        println("   -> FILMES QUE " + f.getNome() + " RETIROU DO CATÁLOGO <-    ");
        print(magent());
        println(f.showFilmesOut());
        print(white());
        linhaFim();
        print(cyan());
        println("   [1] Transferir redimento");
        print(red());
        println("   [2] Voltar");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }

    //menu para bater ponto
    public void menuBaterPonto(){
        LocalTime horaAtual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaDoPonto = horaAtual.format(formatter);
        println(green() + "Ponto batido às " + horaDoPonto + " com sucesso!" + white());
    }

    //menu de opções ao ver lista de clientes
    public void menuClientes(){
        linhaIni();
        print(cyan());
        println("   [1] Excluir cliente");
        print(red());
        println("   [2] Voltar");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    public void menuClientes1(){
        linhaIni();
        print(cyan());
        println("     CLIENTES CADASTRADOS     ");
        print(white());
        linhaMeio();
    }
    public void menuClientes2(){
        linhaMeio();
        println(red() + "   $PARA NÃO EXCLUIR MAIS CLIENTES DIGITE 0 " + white());
        linhaMeio();
        print(yellow() +    "$Digite o ID do cliente que deseja EXCLUIR... " + white());
    }

    //menu de opção para cadastrar
    public void menuCadFilme(){
        linhaIni();
        println(cyan() + "     CADASTRANDO FILMES     " + white());
        linhaMeio();
        println(red() + "$Escolha em que categoria deseja cadastrar um novo filme!" + white());
        linhaMeio();
        print(cyan());
        println("   [1] Terror");
        println("   [2] Romance");
        println("   [3] Comedia");
        println("   [4] Acao");
        println("   [5] Crime suspense");
        println("   [6] Lançamentos");
        print(red());
        println("   [7] VOLTAR");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    //cadastrando.......
    public void menuCadFilme1(){
        println("     < GENÊRO >  ");
        print(blue());
        println("   [1] Terror   [2] Romance");
        println("   [3] Comedia  [4] Acao");
        println("   [5] Crime Suspense");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    public void menuCadFilme2(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        print(cyan() + "     > TÍTULO: ");
    }
    public void menuCadFilme3(){
        println(cyan() + "     < CLASSIFICAÇÃO >  ");
        print(blue());
        println("   [1] Livre   [2] '10+'");
        println("   [3] '12+'   [4] '14+");
        println("   [5] '16+'   [6] '18+");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    public void menuCadFilme4(){
        print(cyan());
        print("     > AVALIAÇÃO: ");
    }
    public void menuCadFilme5(){
        print(cyan());
        print("     > PREÇO: " );
    }
    //opção de voltar ou cadastrar mais
    public void menuCadFilme6(){
        print(white());
        linhaMeio();
        print(cyan());
        println("   [1] Cadastrar novo filme");
        print(red());
        println("   [2] Voltar");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }

    //menu para remover filmes do catalogo
    public void menuRmFilmes(){
        linhaIni();
        println(cyan() + "     TIRANDO FILMES DO CATÁLOGO     " + white());
        linhaMeio();
        println(red() + "$Escolha em que categoria deseja retirar um filme!" + white());
        linhaMeio();
        print(cyan());
        println("   [1] Terror");
        println("   [2] Romance");
        println("   [3] Comedia");
        println("   [4] Acao");
        println("   [5] Crime suspense");
        println("   [6] Lançamentos");
        print(red());
        println("   [7] VOLTAR");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    public void menuRmFilme1(){
        linhaMeio();
        print(cyan());
        println("   [1] Remover filmes");
        println("   [2] Ver outros gêneros");
        print(white());
        linhaMeio();
        print(yellow() + "$Digite... " + white());
    }
    public void menuRmFilme2(){
        linhaIni();
        println(red() + "   $PARA REMOVER DO CATÁLOGO DIGITE O ID DO FILME ");
        println("   $DIGITE # PARA TERMINAR DE REMOVER " + white());
        linhaMeio();
    }
    public void menuRmFilme3(){
        print(yellow() + "$Digite o ID do filme que quer devolver... " + white());
    }
    



    //vai setar a opção de sacar ou sair da opção ver perfil da locadora
    public int getEscolhasVerPerfil() { return escolhasVerPerfil; }
    public void setEscolhasVerPerfil(int escolhasVerPerfil) { this.escolhasVerPerfil = escolhasVerPerfil; }
    //vai setar a opção de excluir cliente ou voltar para o menu
    public int getEscolhaExcluirCliVoltar() { return escolhaExcluirCliVoltar; }
    public void setEscolhaExcluirCliVoltar(int escolhaExcluirCliVoltar) { this.escolhaExcluirCliVoltar = escolhaExcluirCliVoltar; }
    //vai setar a opção de escolher genero para cadastrar filmes ou voltar
    public int getEscolhaGeneroCad() { return escolhaGeneroCad; }
    public void setEscolhaGeneroCad(int escolhaGeneroCad) { this.escolhaGeneroCad = escolhaGeneroCad; }
    //vai setar o genero de qual genero vai ser removido o filme
    public int getEscolhaGeneroRm() { return escolhaGeneroRm; }
    public void setEscolhaGeneroRm(int escolhaGeneroRm) { this.escolhaGeneroRm = escolhaGeneroRm; }
    //entre esolher remover ou sair
    public int getEscolhaRemoverSair() { return escolhaRemoverSair; }
    public void setEscolhaRemoverSair(int escolhaRemoverSair) { this.escolhaRemoverSair = escolhaRemoverSair; }
    //classificação do filme no ato de cadastrar
    public void setClassficacao(int classficacao) { this.classficacao = classficacao; }
    public String getClassficacao() { 
        switch (classficacao) {            
            case 1:
                return "Livre";
            case 2:
                return "10+";
            case 3:
                return "12+";
            case 4:
                return "14+";
            case 5:
                return "16+";
            case 6:
                return "18+";
            default:
                return null;
        }
    }    
    //genero do filme no ato de cadastrar um lançamento
    public void setGenero(int genero) { this.genero = genero; }
    public Generos getGenero() { 
        switch(genero){    
            case 1:
                return Generos.TERROR;
            case 2:
                return Generos.ROMANCE;
            case 3:
                return Generos.COMEDIA;
            case 4:
                return Generos.ACAO;
            case 5:
                return Generos.CRIME_SUSPENSE;
            default:
                return null;
        }
    }
    

}