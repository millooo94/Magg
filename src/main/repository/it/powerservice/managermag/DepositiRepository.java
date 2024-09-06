package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zkoss.bind.annotation.QueryParam;

import java.util.List;

@Repository
public interface DepositiRepository extends JpaRepository<Depositi, Long> {
    @Query("SELECT d FROM Depositi d")
    List<Depositi> getDepositi();
    @Query("""
           SELECT d
           FROM Depositi d
           WHERE d.id NOT IN (
               SELECT dm.idDeposito
               FROM DepositiMarketplace dm
               WHERE dm.idMarketplace = :marketplaceId
           )
           """)
    List<Depositi> findDepositiNonAssociati(@Param("marketplaceId") long marketplaceId);
}
