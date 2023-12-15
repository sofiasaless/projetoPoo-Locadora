package classes_menu;

public abstract class Menu {
    private int escolhaLogCad;
    private int escolhaLocadora;

    public void menuEntrarCadastrar(){
        println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕");
        print(cyan());
        println("   [1] Entrar");
        println("   [2] Cadastrar");
        print(white());
        println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛");
        print(yellow() + "$Digite... " + white());
    }

    //linhas para menu
    public void linhaIni(){ println("╔═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╕"); }
    public void linhaFim(){ println("╚═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═╛"); }
    public void linhaMeio(){ println("═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═─═"); }

    //cores para menu
    public String red(){ return "\u001b[31m"; }
    public String green(){ return "\u001b[32m"; }
    public String cyan(){ return "\u001b[36m"; }
    public String white(){ return "\u001b[37m"; };
    public String yellow(){ return "\u001b[33m"; }
    public String magent(){return "\u001b[35m"; }
    public String blue(){return "\u001b[34m"; }

    public void menuRefazerCad(){
        linhaIni();
        print(cyan());
        println("   [1] Voltar a tela inicial");
        println("   [2] Refazer cadastro");
        print(white());
        linhaFim();
        print("$Digite... ");
    }
    public void menuRefazerLog(){
        linhaIni();
        print(cyan());
        println("   [1] Voltar a tela inicial");
        println("   [2] Refazer login");
        print(white());
        linhaFim();
        print("$Digite... ");
    }

    //metodos para imprimir mais rapido
    public static void println(String v){
        System.out.println(v);
    }
    public static void print(String v){
        System.out.print(v);
    }

    // escolhendo login ou cadastrado
    public int getEscolhaLogCad() { return escolhaLogCad; }
    public void setEscolhaLogCad(int escolhaLogCad) { this.escolhaLogCad = escolhaLogCad; }
    //escolhendo item do menu da locadora
    public int getEscolhaLocadora() { return escolhaLocadora; }
    public void setEscolhaLocadora(int escolhaLocadora) { this.escolhaLocadora = escolhaLocadora; }
}
