package it.powerservice.managermag;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marketplace")
public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "idlistino")
    private Long idListino;
    @Column(name = "idlistinoinglese")
    private Long idListinoInglese;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "pathexport")
    private String pathExport;
    @Column(name = "nonesportaregiacenzamaga")
    private Boolean nonEsportareGiacenzaMaga;
    @Column(name = "disponibilitamenoscmin")
    private Boolean disponibilitamenoScMin;
    @Column(name = "nonesportaresedispneg")
    private Boolean nonEsportareSeDispNeg;
    @Column(name = "disponibilitafissa")
    private Boolean disponibilitaFissa;
    @Column(name = "disponibilitanonzero")
    private Boolean disponibilitaNonZero;
    @Column(name = "spesesped")
    private Boolean speseSped;
    @Column(name = "tempispedizione")
    private String tempiSpedizione;
    @Column(name = "prezzoperkg")
    private Boolean prezzoperkg;
    @Column(name = "nonmodificabile")
    private Boolean nonModificabile;
    @Column(name = "attivo")
    private Boolean attivo;

    @Transient
    Listini selectedListino;
    @Transient
    Listini selectedListinoInglese;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdListino() {
        return idListino;
    }

    public void setIdListino(Long idListino) {
        this.idListino = idListino;
    }

    public Long getIdListinoInglese() {
        return idListinoInglese;
    }

    public void setIdListinoInglese(Long idListinoInglese) {
        this.idListinoInglese = idListinoInglese;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPathExport() {
        return pathExport;
    }

    public void setPathExport(String pathExport) {
        this.pathExport = pathExport;
    }

    public Boolean getNonEsportareGiacenzaMaga() {
        return nonEsportareGiacenzaMaga;
    }

    public void setNonEsportareGiacenzaMaga(Boolean nonEsportareGiacenzaMaga) {
        this.nonEsportareGiacenzaMaga = nonEsportareGiacenzaMaga;
    }

    public Boolean getDisponibilitamenoScMin() {
        return disponibilitamenoScMin;
    }

    public void setDisponibilitamenoScMin(Boolean disponibilitamenoScMin) {
        this.disponibilitamenoScMin = disponibilitamenoScMin;
    }

    public Boolean getNonEsportareSeDispNeg() {
        return nonEsportareSeDispNeg;
    }

    public void setNonEsportareSeDispNeg(Boolean nonEsportareSeDispNeg) {
        this.nonEsportareSeDispNeg = nonEsportareSeDispNeg;
    }

    public Boolean getDisponibilitaFissa() {
        return disponibilitaFissa;
    }

    public void setDisponibilitaFissa(Boolean disponibilitaFissa) {
        this.disponibilitaFissa = disponibilitaFissa;
    }

    public Boolean getDisponibilitaNonZero() {
        return disponibilitaNonZero;
    }

    public void setDisponibilitaNonZero(Boolean disponibilitaNonZero) {
        this.disponibilitaNonZero = disponibilitaNonZero;
    }

    public Boolean getSpeseSped() {
        return speseSped;
    }

    public void setSpeseSped(Boolean speseSped) {
        this.speseSped = speseSped;
    }

    public String getTempiSpedizione() {
        return tempiSpedizione;
    }

    public void setTempiSpedizione(String tempiSpedizione) {
        this.tempiSpedizione = tempiSpedizione;
    }

    public Boolean getPrezzoperkg() {
        return prezzoperkg;
    }

    public void setPrezzoperkg(Boolean prezzoperkg) {
        this.prezzoperkg = prezzoperkg;
    }

    public Boolean getNonModificabile() {
        return nonModificabile;
    }

    public void setNonModificabile(Boolean nonModificabile) {
        this.nonModificabile = nonModificabile;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Listini getSelectedListino() {
        return selectedListino;
    }

    public void setSelectedListino(Listini selectedListino) {
        this.selectedListino = selectedListino;
    }

    public Listini getSelectedListinoInglese() {
        return selectedListinoInglese;
    }

    public void setSelectedListinoInglese(Listini selectedListinoInglese) {
        this.selectedListinoInglese = selectedListinoInglese;
    }

    @Override
    public String toString() {
        return "Marketplace{" +
                "id=" + id +
                ", idListino=" + idListino +
                ", idListinoInglese=" + idListinoInglese +
                ", tipo='" + tipo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", pathExport='" + pathExport + '\'' +
                ", nonEsportareGiacenzaMaga=" + nonEsportareGiacenzaMaga +
                ", disponibilitamenoScMin=" + disponibilitamenoScMin +
                ", nonEsportareSeDispNeg=" + nonEsportareSeDispNeg +
                ", disponibilitaFissa=" + disponibilitaFissa +
                ", disponibilitaNonZero=" + disponibilitaNonZero +
                ", speseSped=" + speseSped +
                ", tempiSpedizione='" + tempiSpedizione + '\'' +
                ", prezzoperkg=" + prezzoperkg +
                ", nonModificabile=" + nonModificabile +
                ", attivo=" + attivo +
                ", selectedListino=" + selectedListino +
                ", selectedListinoInglese=" + selectedListinoInglese +
                '}';
    }
}
