package it.powerservice.managermag;

import it.powerservice.managermag.keys.DepositiMarketplacePKId;
import jakarta.persistence.*;

@Entity
@Table(name = "depositimarketplace")
@IdClass(DepositiMarketplacePKId.class)
public class DepositiMarketplace {
    @Id
    @Column(name = "iddeposito")
    Long idDeposito;
    @Id
    @Column(name = "idmarketplace")
    Long idMarketplace;
    @Column(name = "noninviare")
    Boolean nonInviare;

    public DepositiMarketplace() {
    }

    public DepositiMarketplace(Long idDeposito, Long idMarketplace, Boolean nonInviare) {
        this.idDeposito = idDeposito;
        this.idMarketplace = idMarketplace;
        this.nonInviare = nonInviare;
    }

    public Long getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Long idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Long getIdMarketplace() {
        return idMarketplace;
    }

    public void setIdMarketplace(Long idMarketplace) {
        this.idMarketplace = idMarketplace;
    }

    public Boolean getNonInviare() {
        return nonInviare;
    }

    public void setNonInviare(Boolean nonInviare) {
        this.nonInviare = nonInviare;
    }
}

