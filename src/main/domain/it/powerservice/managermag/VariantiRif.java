package it.powerservice.managermag;

import it.powerservice.managermag.keys.VariantiRifPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "variantirif")
@IdClass(VariantiRifPKId.class)
public class VariantiRif {
    @Id
    @Column(name = "idvariantepartenza", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Integer idVariantePartenza;
    @Id
    @Column(name = "idvariantearrivo", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Integer idVarianteArrivo;
}
