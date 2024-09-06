package it.powerservice.managermag;

import it.powerservice.managermag.keys.DizionariPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "dizionari")
public class Dizionari {
    @Id
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String Codice;
    @Column(name = "categoria", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    String Categoria;
    @Column(name = "descrizione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String Descrizione;
    @Column(name = "modificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean modificabile;

    public Dizionari() {
    }

    public Dizionari(String codice, String categoria, String descrizione, Boolean modificabile) {
        Codice = codice;
        Categoria = categoria;
        Descrizione = descrizione;
        this.modificabile = modificabile;
    }

    public String getCodice() {
        return Codice;
    }

    public void setCodice(String codice) {
        Codice = codice;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public Boolean getModificabile() {
        return modificabile;
    }

    public void setModificabile(Boolean modificabile) {
        this.modificabile = modificabile;
    }






    @Override
    public String toString() {
        return "Dizionari{" +
                "Codice='" + Codice + '\'' +
                ", Categoria='" + Categoria + '\'' +
                ", Descrizione='" + Descrizione + '\'' +
                ", modificabile=" + modificabile +
                '}';
    }
}
