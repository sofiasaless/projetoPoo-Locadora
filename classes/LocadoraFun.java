package classes;

import java.util.List;

import classes_locadora.ListarClientes;
import classes_locadora.ManipularFilmes;
import enums.Generos;

//CLASSE PARA O FUNCIONARIO MANIPULAR FILMES E CLIENTES, PODENDO CADASTRAR E REMOVER FILMES, OU EXCLUIR CLIENTES
public class LocadoraFun extends Locadora {    
    //classes de ação na locadora para o funcionario
    ListarClientes listCli = new ListarClientes();
    ManipularFilmes cadFilmes = new ManipularFilmes();
    ManipularFilmes rmFilmes = new ManipularFilmes();

    //construtor
    public LocadoraFun(){
        this.perfilLogado = new Funcionario(null, null, null);
    }

    @Override
    public void setPerfilLogado(Usuario usu) { this.perfilLogado = (Funcionario) usu; }
    public Funcionario getFuncionarioLogado(){ return (Funcionario) this.perfilLogado; }

    /* FILMES MANIPULADOS PELO FUNCIONARIO NA LOCADORA */
    //vai adicionar no historico de filmes que o funcionario ja cadastrou no catalogo da locadora;
    @Override
    public void setFilmeIn(Filme f) { getFuncionarioLogado().adcFilmeIn(f); }
    @Override
    public List<Filme> getFilmesIn() { return getFuncionarioLogado().getFilmesIn(); }
    @Override
    public String showFilmeIn(){ return getFuncionarioLogado().showFilmesIn(); }

    //vai adicionar no historico de filmes que o funcionario já retirou do catalogo da locadora;
    @Override
    public void setFilmeOut(Filme f) { getFuncionarioLogado().adcFilmeOut(f); }
    @Override
    public List<Filme> getFilmesOut() { return getFuncionarioLogado().getFilmesOut(); }
    @Override
    public String showFilmeOut(){ return getFuncionarioLogado().showFilmesOut(); }


    /* AÇÕES DO FUNCIONARIO NA LOCADORA */
    
    //para o funcionario, o depositar funciona como um metodo que chama o incrementar saldo, que faz o incremento de seu salário
    @Override
    public void depositar(double v){ 
        getFuncionarioLogado().incrementarSaldo(v); 
    }

    public void listarClientes(){
        System.out.println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕\u001b[36m");
        System.out.println("     CLIENTES CADASTRADOS     ");
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.print("\u001b[35m");
        System.out.println(listCli.toString());
        System.out.print("\u001b[37m");
    }

    public void excluirCliente(String idCli) throws Exception{
        //se o id for 0 significa que o funcionario nao quer mais excluir clientes
        if(idCli.equals("0")){
            throw new Exception("null");
        }

        if(listCli.rmCliente(idCli)){
            listCli.serializar();
            throw new Exception("\u001b[32mCliente removido com sucesso!");              
        } else {
            throw new Exception("\u001b[31mCliente inválido ou já removido! Tente novamente!");
        }

    }

    //iniciando os atributos necessários para a classe funcionar
    //passar o genero dos filmes que vao ser manipulados
    public void manipularCadastro(int genero){
        cadFilmes.setGenero(genero);
        cadFilmes.desserializar();
    }

    public Generos pegarGenero(){ return cadFilmes.gerarGenero(); }

    public void cadastrarFilme(Filme f){
        cadFilmes.addFilme(f);
        setFilmeIn(f);
        cadFilmes.serializar();
    }

    public void manipularRemocao(int genero){
        rmFilmes.setGenero(genero);
        rmFilmes.desserializar();
    }

    public void filmesListaRemocao(){
        rmFilmes.imprimirFilmes();
    }

    public void removerFilme(int index){
        rmFilmes.rmFilme(index);
        setFilmeOut(rmFilmes.getRmFilme());
        rmFilmes.serializar();
    }

    //mostrando o perfil do funcionario
    @Override
    public void verPerfil() {
        String nome = getFuncionarioLogado().getNome();
        String id = getFuncionarioLogado().getId();
        int diasTrabalhados = getFuncionarioLogado().getDiasTrabalhados();
        int dias = getFuncionarioLogado().getDias();
        System.out.println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕\u001b[36m");
        System.out.println("   -> Nome do funcionario: " + nome);
        System.out.println("   -> Id do funcionario: " + id);
        System.out.println("   -> Dias totais trabalhados: " + diasTrabalhados);
        System.out.println("   -> Dias trabalhados: " + dias);
        System.out.println("   -> Rendimento atual: " + getFuncionarioLogado().getSaldo());
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("   -> FILMES QUE " + getFuncionarioLogado().getNome() + " CADASTROU NO SISTEMA <-    ");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("\u001b[35m");
        System.out.println(showFilmeIn());
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.println("   -> FILMES QUE " + getFuncionarioLogado().getNome() + " RETIROU DO CATÁLOGO <-    ");
        System.out.println("\u001b[35m");
        System.out.println(showFilmeOut());
        System.out.print("\u001b[37m");
        System.out.println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛\u001b[36m");
        System.out.println("   [1] Transferir redimento\u001b[31m");
        System.out.println("   [2] Voltar");
        System.out.print("\u001b[37m");
        System.out.println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═");
        System.out.print("\u001b[33m$Digite... \u001b[37m");
    }
 
    //vai funcionar como uma forma do funcionario transferir seu rendimento da conta (salario) para sua conta do banco !!(APENAS UMA SIMULAÇÃO)!!
    public void transferirRendimento() throws Exception{
        if(getFuncionarioLogado().getDias() < 5){
            throw new Exception("\u001b[31mÉ necessário ter pelo menos CINCO dias de trabalho para sacar seu rendimento!\u001b[37m");
        }
        getFuncionarioLogado().setSaldo(0);
        getFuncionarioLogado().zerarDias();
        throw new Exception("\u001b[32mRendimento transferido com sucesso!\u001b[37m");
    }
}