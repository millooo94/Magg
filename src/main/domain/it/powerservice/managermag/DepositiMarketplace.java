package it.powerservice.managermag;

import it.powerservice.managermag.keys.DepositiMarketplacePKId;
import jakarta.persistence.*;

@Entity
@Table(name = "depositimarketplace")
@IdClass(DepositiMarketplacePKId.class)
public class DepositiMarketplace {
    @Id
    @Column(name = "iddeposito", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idDeposito;
    @Id
    @Column(name = "idmarketplace", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idMarketplace;
}
