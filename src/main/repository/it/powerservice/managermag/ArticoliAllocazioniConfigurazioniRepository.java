package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliAllocazioniConfigurazioniPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliAllocazioniConfigurazioniRepository extends JpaRepository<ArticoliAllocazioniConfigurazioni, ArticoliAllocazioniConfigurazioniPKId> {
    @Query("SELECT aac FROM ArticoliAllocazioniConfigurazioni aac")
    List<ArticoliAllocazioniConfigurazioni> getArticoliAllocazioniConfigurazioni();
}
