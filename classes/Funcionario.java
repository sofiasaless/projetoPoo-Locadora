package classes;

public class Funcionario extends Usuario {
    //atributos necessários a mais para o funcionário
    private String chaveDeAcesso = "05m2023";
    private int dias = 0;
    private int diasTrabalhados = 0;

    public Funcionario(String nome, String id, String senha) {
        super(nome, id, senha);
        this.dias = 0;
        this.diasTrabalhados = 0;
    }

    //vai validar se o funcionario inseriu a chave correta para entrar no sistema
    public boolean validarChave(String chave) throws Exception{
        if(!(this.chaveDeAcesso.equals(chave))){
            throw new Exception("\u001b[31mCHAVE DE ACESSO INVALIDA!\u001b[37m");
        }
        return true;
    }

    //metodos adicionais da classe funcionario
    public int getDias() { return dias; }
    public int getDiasTrabalhados() { return diasTrabalhados; }
    public void zerarDias(){ this.dias = 0; }
    public void baterPonto() { 
        this.dias++;
        this.diasTrabalhados++;
    }

    //o metodo abaixo vai ter como função incrementar o saldo do funcionario que toma forma como o salario dele
    @Override
    public void incrementarSaldo(double v) {
        //o atributo do parametro vai funcionar como os dias que o funcionario acumulou trabalhando, ele ganha 15 reais por dia;
        setSaldo(v*15);
    }

    //para o funcionario esse metodo vai funcionar como uma maneira de listar os filmes que ele já cadastrou no sistema da locadora;
    @Override
    public void adcFilmeIn(Filme f){  super.adcFilmeIn(f); }

    //para o funcionario esse metodo vai listar todos os filmes que ele já retirou do catálogo da locadora;
    @Override
    public void adcFilmeOut(Filme f){ super.adcFilmeOut(f); }

    @Override
    public String toString() {
        return "Funcionario [nome=" + nome + ", id=" + id + ", senha=" + senha + ", saldo=" + saldo + ", diasTrabalhados=" + diasTrabalhados + ", Filme=" + filmes_in.toString() + "]";
    }

    

}