package it.powerservice.managermag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "impostazionicampi")
public class ImpostazioniCampi {
    @Id
    @Column(name = "Codice", nullable = false, length = 100)
    String Codice;

    @Column(name = "Categoria", nullable = false, length = 70)
    String Categoria;
    @Column(name = "etichettacampo", nullable = false, length = 100)
    String EtichettaCampo;
    @Column(name = "tipocampo", nullable = false, length = 1)
    @Comment("N=Numerico S=Stringa D=Data C=Clob - In automatico ci sarà anche pulsante per aperura form standard per testo")
    String TipoCampo;
    @Column(name = "aspettocampo", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    @Comment("0= Campo box normale 1= Multivalore a lista 2= Multivalore a lista con multiscelta 3=Check box 4= Multivalore a lista ma con Select custom presa dal campo  selectcustom")
    Integer AspettoCampo;
    @Column(name = "formatonumero", length = 20, columnDefinition = "VARCHAR(20) DEFAULT NULL")
    String formatoNumero;
    @Column(name = "formatodata", length = 20, columnDefinition = "VARCHAR(20) DEFAULT NULL")
    String formatoData;
    @Column(name = "Notes", columnDefinition = "MEDIUMTEXT")
    String Notes;
    @Column(name = "invisibile", precision = 1, columnDefinition = "TINYINT(1) DEFAULT '0'")
    Short invisibile;
    @Column(name = "selectcustom", columnDefinition = "MEDIUMTEXT")
    String selectCustom;


    @Override
    public String toString() {
        return "ImpostazioniCampi{" +
                "Codice='" + Codice + '\'' +
                ", Categoria='" + Categoria + '\'' +
                ", EtichettaCampo='" + EtichettaCampo + '\'' +
                ", TipoCampo='" + TipoCampo + '\'' +
                ", AspettoCampo=" + AspettoCampo +
                ", formatoNumero='" + formatoNumero + '\'' +
                ", formatoData='" + formatoData + '\'' +
                ", Notes='" + Notes + '\'' +
                ", invisibile=" + invisibile +
                ", selectCustom='" + selectCustom + '\'' +
                '}';
    }
}
