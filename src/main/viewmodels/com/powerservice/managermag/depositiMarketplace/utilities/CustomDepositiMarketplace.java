package com.powerservice.managermag.depositiMarketplace.utilities;

public class CustomDepositiMarketplace {
    private Long id;
    private String codice;
    private String nome;
    private Boolean nonInviare;

    public CustomDepositiMarketplace() {
    }

    public CustomDepositiMarketplace(Long id, String codice, String nome, Boolean nonInviare) {
        this.id = id;
        this.codice = codice;
        this.nome = nome;
        this.nonInviare = nonInviare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getNonInviare() {
        return nonInviare;
    }

    public void setNonInviare(Boolean nonInviare) {
        this.nonInviare = nonInviare;
    }

    @Override
    public String toString() {
        return "CustomDepositiMarketplace{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", nonInviare=" + nonInviare +
                '}';
    }
}
