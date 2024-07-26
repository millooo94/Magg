package it.powerservice.managermag;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL")
    Long id;
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String codice;
    @Column(name = "descrizione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String descrizione;
    @Column(name = "descrizioneeng", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrizioneEng;
    @Column(name = "descrextraeng", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrizioneExtraEng;
    @Column(name = "visibileweb", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean visibileWeb;
    @Column(name = "abilitascpermodpag", nullable = false, columnDefinition = "DEFAULT NOT NULL DEFAULT FALSE")
    Boolean abilitaScPerModPag;
    @Column(name = "percorsocompleto", columnDefinition = "CHARACTER SET 'latin1' NULL DEFAULT NULL")
    String percorsoCompleto;

    @Transient
    List<Categorie> children = new LinkedList<Categorie>();
    @Transient
    Integer numeroFigli;

    public Categorie() {
    }

    public Categorie(String codice, String descrizione, Boolean visibileWeb, Boolean abilitaScPerModPag) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.abilitaScPerModPag = abilitaScPerModPag;
        this.visibileWeb = visibileWeb;
    }

    public Categorie(Long id, String codice, String descrizione, String descrizioneEng, String descrizioneExtraEng, Boolean visibileWeb, Boolean abilitaScPerModPag, String percorsoCompleto) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.descrizioneEng = descrizioneEng;
        this.descrizioneExtraEng = descrizioneExtraEng;
        this.visibileWeb = visibileWeb;
        this.abilitaScPerModPag = abilitaScPerModPag;
        this.percorsoCompleto = percorsoCompleto;
    }


    public Categorie(Long id, String codice, String descrizione, String descrizioneEng, String descrizioneExtraEng, Boolean visibileWeb, Boolean abilitaScPerModPag, String percorsoCompleto, Integer numeroFigli) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.descrizioneEng = descrizioneEng;
        this.descrizioneExtraEng = descrizioneExtraEng;
        this.visibileWeb = visibileWeb;
        this.abilitaScPerModPag = abilitaScPerModPag;
        this.percorsoCompleto = percorsoCompleto;
        this.numeroFigli = numeroFigli;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneEng() {
        return descrizioneEng;
    }

    public void setDescrizioneEng(String descrizioneEng) {
        this.descrizioneEng = descrizioneEng;
    }

    public String getDescrizioneExtraEng() {
        return descrizioneExtraEng;
    }

    public void setDescrizioneExtraEng(String descrizioneExtraEng) {
        this.descrizioneExtraEng = descrizioneExtraEng;
    }

    public Boolean getVisibileWeb() {
        return visibileWeb;
    }

    public void setVisibileWeb(Boolean visibileWeb) {
        this.visibileWeb = visibileWeb;
    }

    public Boolean getAbilitaScPerModPag() {
        return abilitaScPerModPag;
    }

    public void setAbilitaScPerModPag(Boolean abilitaScPerModPag) {
        this.abilitaScPerModPag = abilitaScPerModPag;
    }

    public String getPercorsoCompleto() {
        return percorsoCompleto;
    }

    public void setPercorsoCompleto(String percorsoCompleto) {
        this.percorsoCompleto = percorsoCompleto;
    }

    public List<Categorie> getChildren() {
        return children;
    }
    public void setChildren(List<Categorie> children) {
        this.children = children;
    }

    public Integer getNumeroFigli() {
        return numeroFigli;
    }

    public void setNumeroFigli(Integer numeroFigli) {
        this.numeroFigli = numeroFigli;
    }

    public void addEmptyChild() {
        children.add(new Categorie());
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", descrizioneEng='" + descrizioneEng + '\'' +
                ", descrizioneExtraEng='" + descrizioneExtraEng + '\'' +
                ", visibileWeb=" + visibileWeb +
                ", abilitaScPerModPag=" + abilitaScPerModPag +
                ", percorsoCompleto='" + percorsoCompleto + '\'' +
                ", numeroFigli='" + numeroFigli + '\'' +
                ", children='" + children + '\'' +
                '}';
    }
}
