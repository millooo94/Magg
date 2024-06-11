package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "allegati")
public class Allegati {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idarticoloconfigurazione", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idArticoloConfigurazione;
    @Column(name = "idanagrafica", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idAnagrafica;
    @Column(name = "idcategoria", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idCategoria;
    @Column(name = "idmarca", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idMarca;
    @Column(name = "tipo", length = 1, nullable = false, columnDefinition = "VARCHAR(1) NOT NULL")
    @Comment("F=Foto, U=Url.  vecchio campo isFoto, isUrl o se proviene da tabella allegatiUrlFoto è U")
    String tipo;
    @Column(name = "descrizioneallegato", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrizioneAllegato;
    @Column(name = "url", length = 500, columnDefinition = "VARCHAR(500) NULL DEFAULT NULL")
    String url;
}
