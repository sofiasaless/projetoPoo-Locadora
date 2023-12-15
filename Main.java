import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Cliente;
import classes.Funcionario;
import classes.EntrarCadastrar;
import classes.Filme;
import classes.LocadoraCli;
import classes.LocadoraFun;
import classes_menu.MenuCliente;
import classes_menu.MenuFuncionario;
import enums.Generos;

public class Main {
    public static void main(String[] args) throws Exception, IOException {
        //scanner
        Scanner scan = new Scanner(System.in);

        //atributos para auxiliar o decorrer do sistema
        Cliente cli = new Cliente(null, null, null);
        Funcionario fun = new Funcionario(null, null, null);
        EntrarCadastrar ec = new EntrarCadastrar();

        //inicializando as classes para o cliente
        MenuCliente menuCli = new MenuCliente();
        LocadoraCli locaCli = new LocadoraCli();

        //inicializando as classes para o funcionario
        MenuFuncionario menuFun = new MenuFuncionario();
        LocadoraFun locaFun = new LocadoraFun();

        //incremento para quando o login for feito e poder prosseguir para o menu principal
        boolean testLogin = false;
        
        //variaveis de escolha do usuario
        int escolhaUsuario = 0;

        //laço para pegar os erros que o usuario pode digitar
        while (true) {
            try{    
                clearScreen();
                abertura();
                System.out.println("\u001b[0m");
                menuCli.linhaIni();
                inicio();
                escolhaUsuario = scan.nextInt();
                if(escolhaUsuario > 3 || escolhaUsuario < 1){
                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                    scan.nextLine();
                    Thread.sleep(1500);
                } else {
                    break;
                }
            } catch(InputMismatchException e){
                println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                scan.nextLine();
                Thread.sleep(1500);
            }
        }

        //pegando a escolha de cliente ou funcionario
        if(escolhaUsuario == 1){
            clearScreen();
            //laço para entrar ou cadastrar cliente
            while(testLogin == false){
                //pegando as exceções
                while(true){
                    clearScreen();
                    try{
                        menuCli.menuEntrarCadastrar();
                        menuCli.setEscolhaLogCad(scan.nextInt());
                        if(menuCli.getEscolhaLogCad() > 2 || menuCli.getEscolhaLogCad() < 1){
                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                            scan.nextLine();
                            Thread.sleep(1500);
                            continue;
                        } else {
                            break;
                        }
                    } catch(InputMismatchException e){
                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                        scan.nextLine();
                        Thread.sleep(1500);
                    }
                }
                
                if(menuCli.getEscolhaLogCad() == 2){
                    //fazendo cadastro do cliente
                    while(true){
                        try{
                            menuCli.menuCliente1();
                            String nome = scan.next();
                            menuCli.menuCliente2();
                            String id = scan.next();
                            menuCli.menuCliente3();
                            String senha = scan.next();
                            menuCli.linhaFim();

                            cli = new Cliente(nome, id, senha);
                            ec.cadastrar(cli);
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            if(ex.getMessage().equals("!")){
                                ec.serializar();
                                Thread.sleep(3000);
                                break;
                            } else {
                                menuCli.menuRefazerCad();
                                int escolha = scan.nextInt();
                                if(escolha == 1){ clearScreen(); break;  }
                                else if(escolha == 2){ clearScreen(); continue; }
                            }
                        }
                    }
            
                } else if(menuCli.getEscolhaLogCad() == 1){
                    //entrando com o cliente
                    while(true){
                        try{
                            menuCli.menuCliente1();
                            String nome = scan.next();
                            menuCli.menuCliente2();
                            String id = scan.next();
                            menuCli.menuCliente3();
                            String senha = scan.next();
                            menuCli.linhaFim();

                            cli = new Cliente(nome, id, senha);
                            ec.logar(cli);
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            if(ex.getMessage().equals("!")){
                                testLogin = true;
                                locaCli.setPerfilLogado(ec.getClienteLogado());
                                Thread.sleep(1000);
                                break;
                            } else {
                                menuCli.menuRefazerLog();
                                int escolha = scan.nextInt();
                                if(escolha == 1){ clearScreen(); break;  }
                                else if(escolha == 2){ clearScreen(); continue; }
                            }
                        }
                    }
                }
            }
            
            clearScreen();
            
            //daqui pra baixo é mostrando os menus de classes da locadora, o perfil logado é um cliente
            while(true){

                //pegando as exceções do MENU PRINCIPAL
                while (true) {
                    try{    
                        clearScreen();
                        menuCli.menuLocadora();
                        menuCli.setEscolhaLocadora(scan.nextInt());
                        if(menuCli.getEscolhaLocadora() < 0 || menuCli.getEscolhaLocadora() > 5){
                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                            scan.nextLine();
                            Thread.sleep(1500);
                            continue;
                        } else {
                            break;
                        }
                    } catch(InputMismatchException e){
                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                        scan.nextLine();
                        Thread.sleep(1500);
                    }
                }

                int item = menuCli.getEscolhaLocadora();
                
                //VER PERFIL
                if(item == 1){
                    clearScreen();
                    locaCli.verPerfil();
                    print("$Pressione qualquer tecla e ENTER para prosseguir... ");
                    scan.next();
                }
                
                //INSERINDO SALDO
                if(item == 2){
                    //verificando as exceções
                    while (true) {
                        try{    
                            clearScreen();
                            menuCli.menuInserirSaldo(locaCli.getCliente().getSaldo());
                            locaCli.depositar(scan.nextDouble());
                            menuCli.linhaFim();
                            break;
                        } catch(InputMismatchException e){
                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                            scan.nextLine();
                            Thread.sleep(1500);
                        }
                    }

                    println("\u001b[32mDEPOSITADO COM SUCESSO!\u001b[37m");
                    Thread.sleep(1500);
                }
                
                //ALUGANDO FILMES
                if(item == 3){
                    while(true){
                        //verificando as exceções
                        //ESCOLHENDO GENEROS OU VOLTAR AO MENU
                        while (true) {
                            try{    
                                clearScreen();
                                menuCli.menuGenerosFilmes();
                                menuCli.setEscolhaGeneroFilme(scan.nextInt());
                                if(menuCli.getEscolhaGeneroFilme() > 7 || menuCli.getEscolhaGeneroFilme() < 1){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                    continue;
                                } else {
                                    break;
                                }
                            } catch(InputMismatchException e){
                                println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                scan.nextLine();
                                Thread.sleep(1500);
                            }
                        }
                        
                        //se a opção selecionada nao for "SAIR"
                        if(menuCli.getEscolhaGeneroFilme() != 7){
                            while (true) {
                                try{    
                                    clearScreen();
                                    menuCli.linhaMeio();
                                    //filmes disponiveis do genero
                                    locaCli.filmesDisponiveis(menuCli.getEscolhaGeneroFilme());

                                    menuCli.linhaMeio();
                                    menuCli.menuAlugarVoltar();
                                    menuCli.setEscolhaAlugarVoltar(scan.nextInt());
                                    if(menuCli.getEscolhaAlugarVoltar() > 2 || menuCli.getEscolhaAlugarVoltar() < 1){
                                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);
                                        continue;
                                    } else {
                                        break;
                                    }
                                } catch(InputMismatchException e){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                }
                            }

                            //se ele escolher alugar......
                            if(menuCli.getEscolhaAlugarVoltar() == 1){
                                menuCli.menuAlugar();
                                int escolha = 0;
                                //verificando exceção
                                while (true) {
                                    try{
                                        // clearScreen();
                                        menuCli.menuAlugar1();
                                        escolha = scan.nextInt();
                                        try {
                                            locaCli.alugarFilme(escolha);
                                        } catch(IndexOutOfBoundsException e){
                                            println(menuCli.red() + "FILME INVÁLIDO OU INEXISTENTE!" + menuCli.white());
                                            Thread.sleep(1500);
                                        } catch(Exception e) {
                                            println(e.getMessage());
                                            Thread.sleep(1500);
                                        }
                                    } catch(InputMismatchException e){
                                        println(menuCli.red() + "Saindo..." + menuCli.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);
                                        break;
                                    }
                                }
                            } else if(menuCli.getEscolhaAlugarVoltar() == 2){
                                //se ele escolher voltar a ver mais generos
                                continue;
                            } else if(menuCli.getEscolhaAlugarVoltar() == 1 && locaCli.getFilmesIn().size() == 5){
                                println(menuCli.red()+"Limite de filmes alugados atingido! Tente devolver algum para alugar outro" + menuCli.white());
                            }
                        } else {
                            //vai voltar para o menu principal
                            break;
                        }
                    }
                } 
                
                //DEVOLVENDO FILMES
                if(item == 4){
                    while(true){
                        clearScreen();
                        menuCli.menuDevolver(locaCli.getFilmesIn());
                        menuCli.menuDevolver1();
                        menuCli.setEscolhaDevolverVoltar(scan.nextInt());

                        //se o cliente quiser devolver filmes...
                        if(menuCli.getEscolhaDevolverVoltar() == 1){
                            //começar a devolver os filmes
                            while(true){
                                clearScreen();
                                try{
                                    menuCli.menuDevolver(locaCli.getFilmesIn());
                                    menuCli.menuDevolver2();
                                    int idFilme = scan.nextInt();                                    
                                    try {
                                        locaCli.devolverFilme(idFilme);
                                    } catch(Exception e){
                                        println(e.getMessage());
                                        Thread.sleep(1500);
                                    }
                                } catch(InputMismatchException e){
                                    println(menuCli.red() + "Saindo..." + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                    break;
                                }
                            }
                        } 
                        //se ele escolher voltar ao menu principal
                        if(menuCli.getEscolhaDevolverVoltar() == 2){
                            break;
                        }
                    }
                }

                //ENCERRANDO A LOCADORA PARA O CLIENTE
                if(item == 5){
                    //serializando todas alterações do cliente que estava logado
                    EntrarCadastrar ec2 = new EntrarCadastrar();
                    for(int i = 0; i < ec2.getClientes().size(); i++){
                        if(ec2.getClientes().get(i).getId().equals(locaCli.getCliente().getId())){
                            ec2.getClientes().set(i, locaCli.getCliente());
                        }
                    }
                    ec2.serializar();
                    break;
                }
            }
        } 
        
        if(escolhaUsuario == 2){
            //laço para entrar ou cadastrar funcionario
            clearScreen();

            while(testLogin == false){
                menuFun.menuEntrarCadastrar();
                menuFun.setEscolhaLogCad(scan.nextInt());
                if(menuFun.getEscolhaLogCad() == 2){
                    //fazendo cadastro do funcionario
                    while(true){
                        try{
                            clearScreen();
                            menuFun.menuFuncio1();
                            String nome = scan.next();
                            menuFun.menuFuncio2();
                            String id = scan.next();
                            menuFun.menuFuncio3();
                            String senha = scan.next();
                            menuFun.menuFuncio4();
                            String chave = scan.next();
                            menuFun.linhaFim();

                            fun = new Funcionario(nome, id, senha);
                            if(fun.validarChave(chave)){
                                ec.cadastrar(fun);
                                continue;
                            } else {
                                throw new Exception(menuFun.red() + "CHAVE DE ACESSO INVALIDA!" + menuFun.white());
                            }
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            Thread.sleep(1000);
                            if(ex.getMessage().equals("!")){
                                ec.serializar();
                                break;
                            } else {
                                menuFun.menuRefazerCad();
                                int escolha = scan.nextInt();
                                if(escolha == 1){ clearScreen(); break;  }
                                else if(escolha == 2){ clearScreen(); continue; }
                            }
                        }
                    }
            
                } else if(menuFun.getEscolhaLogCad() == 1){
                    //entrando com o funcionario
                    while(true){
                        try{
                            clearScreen();
                            menuFun.menuFuncio1();
                            String nome = scan.next();
                            menuFun.menuFuncio2();
                            String id = scan.next();
                            menuFun.menuFuncio3();
                            String senha = scan.next();
                            menuFun.menuFuncio4();
                            String chave = scan.next();
                            menuFun.linhaFim();

                            fun = new Funcionario(nome, id, senha);
                            if(fun.validarChave(chave)){
                                ec.logar(fun);
                            }
                        } catch (Exception ex){
                            System.out.println(ex.getMessage());
                            Thread.sleep(1000);
                            if(ex.getMessage().equals("!")){
                                println(menuFun.green() + "Entrando..." + menuFun.white());
                                Thread.sleep(1000);
                                testLogin = true;
                                locaFun.setPerfilLogado(ec.getFuncionarioLogado());
                                break;
                            } else {
                                menuFun.menuRefazerLog();
                                int escolha = scan.nextInt();
                                if(escolha == 1){ clearScreen(); break;  }
                                else if(escolha == 2){ clearScreen(); continue; }
                            }
                        }
                    }
                }
            }

            //FUNCIONARIO LOGADO
            //depois do funcionario estar logado no sistema...
            while(true){
                //pegando a opção que ele escolher no menu
                while (true) {
                    try{    
                        clearScreen();
                        menuFun.menuLocadora();
                        menuFun.setEscolhaLocadora(scan.nextInt());

                        if(menuFun.getEscolhaLocadora() > 6 || menuFun.getEscolhaLocadora() < 1){
                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                            scan.nextLine();
                            Thread.sleep(1500);
                            continue;
                        } else {
                            break;
                        }
                    } catch(InputMismatchException e){
                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                        scan.nextLine();
                        Thread.sleep(1500);
                    }
                }

                clearScreen();
                int item = menuFun.getEscolhaLocadora();

                //ENCERRANDO A LOCADORA PARA O FUNCIONARIO
                if(item == 6){
                    EntrarCadastrar ec2 = new EntrarCadastrar();
                    for(int i = 0; i < ec2.getFuncionarios().size(); i++){
                        if(ec2.getFuncionarios().get(i).getId().equals(locaFun.getFuncionarioLogado().getId())){
                            ec2.getFuncionarios().set(i, locaFun.getFuncionarioLogado());                        
                        }
                    }
                    ec2.serializar();
                    break;
                }

                //VER PERFIL
                if(item == 1){
                    while(true){
                        while(true){
                            //verificando as exceções
                            try{
                                clearScreen();
                                // menuFun.menuVerPerfil(locaFun.getFuncionarioLogado());
                                locaFun.verPerfil();
                                menuFun.setEscolhasVerPerfil(scan.nextInt());

                                //verificando se o numero nao ta fora das opções
                                if(menuFun.getEscolhasVerPerfil() > 2 || menuFun.getEscolhasVerPerfil() < 1){
                                    println(menuFun.red() + "Insira uma opção válida!" + menuFun.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                    continue;
                                } else {
                                    break;
                                }
                            } catch(InputMismatchException e){
                                println(menuFun.red() + "Insira uma opção válida!" + menuFun.white());
                                scan.nextLine();
                                Thread.sleep(1500);
                            }
                        }

                        //se a escolha for 2 vai voltar ao menu principal
                        if(menuFun.getEscolhasVerPerfil() == 2){
                            println(menuFun.red() + "Saindo..." + menuFun.white());
                            Thread.sleep(1000);
                            break;
                        }
                        //senao o usuario vai sacar o dinheiro de acordo com as condições
                        if(menuFun.getEscolhasVerPerfil() == 1){
                            try{
                                locaFun.transferirRendimento();
                            } catch(Exception e){
                                println(e.getMessage());
                                Thread.sleep(2000);
                            }
                            continue;
                        }
                    }
                }

                //VER LISTA DE CLIENTES
                /* IDEIA: o funcionario tem possibilidade de remover algum usuario */
                if(item == 2){
                    while(true){
                        while(true){
                            try{    
                                clearScreen();
                                locaFun.listarClientes();
                                menuFun.menuClientes();
                                menuFun.setEscolhaExcluirCliVoltar(scan.nextInt());
                                if(menuFun.getEscolhaExcluirCliVoltar() > 2 || menuFun.getEscolhaExcluirCliVoltar() < 1){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                    continue;
                                } else {
                                    break;
                                }
                            } catch(InputMismatchException e){
                                println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                scan.nextLine();
                                Thread.sleep(1500);
                            }
                        }
                        
                        //excluir algum cliente
                        if(menuFun.getEscolhaExcluirCliVoltar() == 1){
                            while(true){
                                try{
                                    clearScreen();
                                    locaFun.listarClientes();
                                    menuFun.menuClientes2();
                                    try{
                                        locaFun.excluirCliente(scan.next());
                                    } catch(Exception e){
                                        if(e.getMessage().equals("null")){
                                            break;
                                        }
                                        println(e.getMessage());
                                        print(menuFun.white());
                                        Thread.sleep(1500);
                                    }
                                } catch(InputMismatchException e){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                }
                            }
                        }
                        //voltando ao menu
                        if(menuFun.getEscolhaExcluirCliVoltar() == 2){
                            println(menuFun.red() + "Saindo..." + menuFun.white());
                            Thread.sleep(1000);
                            break;
                        }
                    }
                }

                //CADASTRAR NOVOS FILMES
                if(item == 3){
                    //escolhendo o genero em que vai cadastrar
                    while(true){
                        //pegando as exceções
                        while(true){
                            try{    
                                clearScreen();
                                menuFun.menuCadFilme();
                                menuFun.setEscolhaGeneroCad(scan.nextInt());   
                                if(menuFun.getEscolhaGeneroCad() > 7 || menuFun.getEscolhaGeneroCad() < 1){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                    continue;
                                } else {
                                    break;
                                }
                            } catch(InputMismatchException e){
                                println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                scan.nextLine();
                                Thread.sleep(1500);
                            }
                        }

                        int escolhaGenero = menuFun.getEscolhaGeneroCad();

                        if(escolhaGenero == 7){
                            println(menuFun.red() + "Saindo..." + menuFun.white());
                            Thread.sleep(1000);
                            break;
                        } else {
                            locaFun.manipularCadastro(escolhaGenero);

                            //exibindo o menu para cadastrar filmes
                            while(true){
                                //caso o funcionario queira cadastrar num lançamento
                                if(escolhaGenero == 6){
                                    while(true){
                                        try{    
                                            clearScreen();
                                            scan.nextLine();
                                            menuFun.menuCadFilme2();
                                            String titulo = scan.nextLine();
                                            //genero
                                            menuFun.menuCadFilme1();
                                            menuFun.setGenero(scan.nextInt());
                                            Generos g = menuFun.getGenero();
                                            //classificação
                                            menuFun.menuCadFilme3();
                                            menuFun.setClassficacao(scan.nextInt());
                                            String classi = menuFun.getClassficacao();
                                            scan.nextLine();
                                            menuFun.menuCadFilme4();
                                            String avaliac = scan.next();
                                            scan.nextLine();
                                            menuFun.menuCadFilme5();
                                            double preco = scan.nextDouble();
                                            scan.nextLine();
                                            Filme f = new Filme(titulo, g, classi, avaliac, preco);
                                            locaFun.cadastrarFilme(f);
                                            print(menuFun.yellow());
                                            println(f.toString());
                                            println(menuFun.green() + "FILME CADASTRADO COM SUCESSO!" + menuFun.white());
                                            break;
                                        } catch(InputMismatchException e){
                                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                            scan.nextLine();
                                            Thread.sleep(1500);
                                        }
                                    }
                                } else {
                                    while(true){
                                        try{    
                                            clearScreen();
                                            scan.nextLine();
                                            menuFun.menuCadFilme2();
                                            String titulo = scan.nextLine();
                                            //classificação
                                            menuFun.menuCadFilme3();
                                            menuFun.setClassficacao(scan.nextInt());
                                            String classi = menuFun.getClassficacao();
                                            scan.nextLine();
                                            menuFun.menuCadFilme4();
                                            String avaliac = scan.next();
                                            scan.nextLine();
                                            menuFun.menuCadFilme5();
                                            double preco = scan.nextDouble();
                                            scan.nextLine();

                                            Generos g = locaFun.pegarGenero();
                                            Filme f = new Filme(titulo, g, classi, avaliac, preco);
                                            locaFun.cadastrarFilme(f);

                                            print(menuFun.yellow());
                                            println(f.toString());
                                            println(menuFun.green() + "FILME CADASTRADO COM SUCESSO!" + menuFun.white());
                                            break;
                                        } catch(InputMismatchException e){
                                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                            scan.nextLine();
                                            Thread.sleep(1500);
                                        }
                                    }
                                }

                                int escolha;
                                while(true){
                                    try{    
                                        menuFun.menuCadFilme6();
                                        escolha = scan.nextInt();                                
                                        if(escolha > 2 || escolha < 1){
                                            println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                            scan.nextLine();
                                            Thread.sleep(1500);
                                            continue;
                                        } else {
                                            break;
                                        }
                                    } catch(InputMismatchException e){
                                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);
                                    }
                                }
                                if(escolha == 1){ continue; }
                                else if(escolha == 2){ break; }
                            }
                        }
                    }
                }

                //REMOVENDO FILMES DO CATÁLOGO
                if(item == 4){
                    //condifção para rodar enquanto a opção dos generos nao for VOLTAR (7)
                    while(true){
                        while(true){
                            try{
                                clearScreen();
                                menuFun.menuRmFilmes();
                                menuFun.setEscolhaGeneroRm(scan.nextInt());
                                if(menuFun.getEscolhaGeneroRm() > 7 || menuFun.getEscolhaGeneroRm() < 1){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                } else {
                                    break;
                                }
                            } catch(InputMismatchException e){
                                println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                scan.nextLine();
                                Thread.sleep(1500);
                            }
                        }

                        int rmGenero = menuFun.getEscolhaGeneroRm();
                        
                        //se for escolher sair...
                        if(rmGenero == 7){
                            scan.nextLine();
                            break;
                        }

                        //pegando o genero que ele quer remover
                        if(rmGenero != 7){
                            locaFun.manipularRemocao(rmGenero);

                            while(true){
                                try{
                                    clearScreen();
                                    locaFun.filmesListaRemocao();

                                    menuFun.menuRmFilme1();
                                    menuFun.setEscolhaRemoverSair(scan.nextInt());
                                    if(menuFun.getEscolhaRemoverSair() > 2 || menuFun.getEscolhaRemoverSair() < 1){
                                        println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);
                                    } else {
                                        break;
                                    }
                                } catch(InputMismatchException e){
                                    println(menuCli.red() + "Insira uma opção válida!" + menuCli.white());
                                    scan.nextLine();
                                    Thread.sleep(1500);
                                }
                            }

                            int removerSair = menuFun.getEscolhaRemoverSair();
                            if(removerSair == 1){
                                while (true) {
                                    try {
                                        clearScreen();
                                        locaFun.filmesListaRemocao();
                                        menuFun.menuRmFilme2();
                                        menuFun.menuRmFilme3();
                                        locaFun.removerFilme(scan.nextInt());                                        
                                        Thread.sleep(2300);
                                    } catch(InputMismatchException e) {
                                        //vai pegar a exceção que vai ser interpretada como um cancelar as remoções de filmes
                                        println(menuCli.red() + "Saindo..." + menuCli.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);                                        
                                        break;
                                    } catch(IndexOutOfBoundsException e){
                                        println(menuFun.red() + "FILME INVÁLIDO OU INEXISTENTE!" + menuFun.white());
                                        scan.nextLine();
                                        Thread.sleep(1500);
                                    }
                                }
                            }
                        }
                    }
                }

                //BATER PONTO
                if(item == 5){
                    clearScreen();
                    menuFun.menuBaterPonto();
                    locaFun.getFuncionarioLogado().baterPonto();
                    locaFun.depositar(locaFun.getFuncionarioLogado().getDias());
                    // locaFun.getFuncionarioLogado().incrementarSaldo(locaFun.getFuncionarioLogado().getDias());
                    Thread.sleep(2500);
                }
            }
        } 

        //se o usuario escolher encerrar o programa        
        if(escolhaUsuario == 3){
            println(menuCli.red() + "Saindo..." + menuCli.white());
            scan.nextLine();
            Thread.sleep(1500);                                        
        }
        scan.close();
    }

    /* METODOS PARA ENCURTAR A MAIN */
    public static void inicio(){
        print("\u001b[36m");
        println("   [1] Cliente");
        println("   [2] Funcionário");
        print("\u001b[31m");
        println("   [3] ENCERRAR");
        print("\u001b[37m");
        println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛");
        print("$Digite... ");
    }

    public static void println(String value){ System.out.println(value); }
    public static void print(String value){ System.out.print(value); }

    //limpando a tela
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //fonte de abertura
    public static void abertura(){
        String s = "";
        s += "██╗      ██████╗  ██████╗ █████╗ ██████╗  ██████╗ ██████╗  █████╗ \r\n" + //
            "██║     ██╔═══██╗██╔════╝██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██╔══██╗\r\n" + //
            "██║     ██║   ██║██║     ███████║██║  ██║██║   ██║██████╔╝███████║\r\n" + //
            "██║     ██║   ██║██║     ██╔══██║██║  ██║██║   ██║██╔══██╗██╔══██║\r\n" + //
            "███████╗╚██████╔╝╚██████╗██║  ██║██████╔╝╚██████╔╝██║  ██║██║  ██║\r\n" + //
            "╚══════╝ ╚═════╝  ╚═════╝╚═╝  ╚═╝╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝";                   

        for(int i = 0; i < s.length(); i++){
            System.out.print("\u001b[35;1m" + s.charAt(i));
        }
    }

    /* public static void abertura(){
        String s = "";
        s += " ████                                  █████                             \r\n" + //
                "░░███                                 ░░███                              \r\n" + //
                " ░███   ██████   ██████   ██████    ███████   ██████  ████████   ██████  \r\n" + //
                " ░███  ███░░███ ███░░███ ░░░░░███  ███░░███  ███░░███░░███░░███ ░░░░░███ \r\n" + //
                " ░███ ░███ ░███░███ ░░░   ███████ ░███ ░███ ░███ ░███ ░███ ░░░   ███████ \r\n" + //
                " ░███ ░███ ░███░███  ███ ███░░███ ░███ ░███ ░███ ░███ ░███      ███░░███ \r\n" + //
                " █████░░██████ ░░██████ ░░████████░░████████░░██████  █████    ░░████████\r\n" + //
                "░░░░░  ░░░░░░   ░░░░░░   ░░░░░░░░  ░░░░░░░░  ░░░░░░  ░░░░░      ░░░░░░░░ \r\n"; //

        for(int i = 0; i < s.length(); i++){
            System.out.print("\u001b[35;1m" + s.charAt(i));
        }
    } */
}