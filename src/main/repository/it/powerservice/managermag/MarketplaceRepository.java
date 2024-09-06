package it.powerservice.managermag;

import com.powerservice.managermag.depositiMarketplace.utilities.CustomDepositiMarketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    @Query("SELECT m FROM Marketplace m")
    List<Marketplace> getMarketplace();
    @Query("SELECT l FROM Listini l JOIN Marketplace m ON l.id = m.idListino WHERE m.id = :marketplaceId")
    Listini getListinoByIdListino(@Param("marketplaceId") Long marketplaceId);
    @Query("SELECT l FROM Listini l JOIN Marketplace m ON l.id = m.idListinoInglese WHERE m.id = :marketplaceId")
    Listini getListinoIngleseByIdListinoInglese(@Param("marketplaceId") Long marketplaceId);
}
