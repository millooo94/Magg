package it.powerservice.managermag;

import it.powerservice.managermag.keys.VariantiRifPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "variantirif")
@IdClass(VariantiRifPKId.class)
public class VariantiRif {
    @Id
    @Column(name = "idvariantepartenza", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idVariantePartenza;
    @Id
    @Column(name = "idvariantearrivo", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idVarianteArrivo;

    public VariantiRif() {
    }

    public VariantiRif(Long idVariantePartenza, Long idVarianteArrivo) {
        this.idVariantePartenza = idVariantePartenza;
        this.idVarianteArrivo = idVarianteArrivo;
    }
}
