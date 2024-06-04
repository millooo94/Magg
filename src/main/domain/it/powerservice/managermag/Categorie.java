package it.powerservice.managermag;

import jakarta.persistence.*;

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
    @Column(name = "descrizioneeng", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrizioneEng;
    @Column(name = "descrextraeng", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrizioneExtraEng;
    @Column(name = "visibileweb", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean visibileWeb;
    @Column(name = "abilitascpermodpag", nullable = false, columnDefinition = "DEFAULT NOT NULL DEFAULT FALSE")
    Boolean abilitaScPerModPag;
    @Column(name = "percorsocompleto", columnDefinition = "CHARACTER SET 'latin1' NULL DEFAULT NULL")
    String percorsoCompleto;

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
                '}';
    }
}
