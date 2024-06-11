package it.powerservice.managermag;

import it.powerservice.managermag.keys.AllegatiWebMarketplacePKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllegatiWebMarketplaceRepository extends JpaRepository<AllegatiWebMarketplace, AllegatiWebMarketplacePKId> {
    @Query("SELECT awm FROM AllegatiWebMarketplace awm")
    List<AllegatiWebMarketplace> getAllegatiWebMarketplace();
}
