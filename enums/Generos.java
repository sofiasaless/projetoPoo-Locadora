package enums;

public enum Generos {
    TERROR("Terror"),
    ROMANCE("Romance"),
    COMEDIA("Comedia"),
    ACAO("Acao"),
    CRIME_SUSPENSE("Crime"),
    LANCAMENTOS("Lancamento");
    
    private String label;
    
    Generos(String label){
        this.label = label;
    }
    
    public String getLabel(){ return this.label; }
}