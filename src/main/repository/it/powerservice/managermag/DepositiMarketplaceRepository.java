package it.powerservice.managermag;

import it.powerservice.managermag.keys.DepositiMarketplacePKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositiMarketplaceRepository extends JpaRepository<DepositiMarketplace, DepositiMarketplacePKId> {
    @Query("SELECT dm FROM DepositiMarketplace dm")
    List<DepositiMarketplace> getDepositiMarketplace();
}
