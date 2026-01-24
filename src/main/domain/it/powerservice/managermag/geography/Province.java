package it.powerservice.managermag.geography;

public class Province {
    private String nome;
    private String nomeCompleto;
    private String codice;

    public Province(String nomeCompleto) {
        this.nome = "";
        this.nomeCompleto = nomeCompleto;
        this.codice = "";
    }

    public Province(String nome, String nomeCompleto, String codice) {
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Province{" +
                "nome='" + nome + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
