package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliListiniConfigurazioniPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliListiniConfigurazioniRepository extends JpaRepository<ArticoliListiniConfigurazioni, ArticoliListiniConfigurazioniPKId> {
    @Query("SELECT alc FROM ArticoliListiniConfigurazioni alc")
    List<ArticoliListiniConfigurazioni> getAllArticoliListiniConfigurazioni();
}
