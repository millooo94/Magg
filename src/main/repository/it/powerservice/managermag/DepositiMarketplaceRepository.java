package it.powerservice.managermag;

import it.powerservice.managermag.keys.DepositiMarketplacePKId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositiMarketplaceRepository extends JpaRepository<DepositiMarketplace, DepositiMarketplacePKId> {
    @Query("SELECT dm FROM DepositiMarketplace dm")
    List<DepositiMarketplace> getDepositiMarketplace();

    @Query("SELECT dm FROM DepositiMarketplace dm WHERE dm.idDeposito = :idDeposito AND dm.idMarketplace = :idMarketplace")
    DepositiMarketplace findDepositoMarketplaceByIdDepositoAndIdMarketplace(Long idDeposito, Long idMarketplace);

    @Modifying
    @Transactional
    @Query("DELETE FROM DepositiMarketplace dm WHERE dm.idDeposito = :idDeposito AND dm.idMarketplace = :idMarketplace")
    void deleteByIdDeposito(Long idDeposito, Long idMarketplace);

}
