package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliWebMarketConfigurazioniRepository extends JpaRepository<ArticoliWebMarketConfigurazioni, Long> {
    @Query("SELECT awmc FROM ArticoliWebMarketConfigurazioni awmc")
    List<ArticoliWebMarketConfigurazioni> getArticoliWebMarketConfigurazioni();
}
