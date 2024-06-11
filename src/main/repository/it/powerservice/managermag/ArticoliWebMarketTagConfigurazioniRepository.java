package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliWebMarketTagConfigurazioniRepository extends JpaRepository<ArticoliWebMarketTagConfigurazioni, Long> {
    @Query("SELECT awmtc FROM ArticoliWebMarketTagConfigurazioni awmtc")
    List<ArticoliWebMarketTagConfigurazioni> getArticoliWebMarketTagConfigurazioni();
}
