package it.powerservice.managermag.geography;

public class Regioni {
    private String nome;
    private String codice;

    public Regioni(String nome) {
        this.nome = nome;
    }

    public Regioni(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Regioni{" +
                "nome='" + nome + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
