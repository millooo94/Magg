package it.powerservice.managermag;

import it.powerservice.managermag.keys.ImpostazioniOggettoPKId;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Table(name = "impostazionioggetto")
@IdClass(ImpostazioniOggettoPKId.class)
public class ImpostazioniOggetto {
    @Id
    @Column(name = "codiceimpostazione", length = 100, nullable = false)
    String CodiceImpostazione;
    @Id
    @Column(name = "idoggetto", precision = 11, nullable = false, columnDefinition = "BIGINT DEFAULT '0'")
    @Comment("id della tabella relativa al tipo oggetto Se impostazione generale lasciare il valore di default 0")
    Long idOggetto;
    @Id
    @Column(name = "tipooggetto", length = 10, nullable = false, columnDefinition = "VARCHAR(100) DEFAULT 'GEN'")
    @Comment("tipo di tabella collegata. Se ''GEN'' (dafault) è impostazione generale. Se ''UTE'' è utenti Se ''DOC'' se documenti ...inserire qui le altre...")
    String tipoOggetto;
    @Column(name = "valorestringa", length = 100, columnDefinition = "VARCHAR(100) DEFAULT NULL")
    String valoreStringa;
    @Column(name = "valorenumero", columnDefinition = "DOUBLE default NULL")
    Double valoreNumero;
    @Column(name = "valoreclob", columnDefinition = "MEDIUMTEXT")
    String valoreClob;

    @Override
    public String toString() {
        return "ImpostazioniOggetto{" +
                "CodiceImpostazione='" + CodiceImpostazione + '\'' +
                ", idOggetto=" + idOggetto +
                ", tipoOggetto='" + tipoOggetto + '\'' +
                //", impostazioneCampo=" + impostazioneCampo +
                ", valoreStringa='" + valoreStringa + '\'' +
                ", valoreNumero=" + valoreNumero +
                ", valoreClob='" + valoreClob + '\'' +
                '}';
    }
}
