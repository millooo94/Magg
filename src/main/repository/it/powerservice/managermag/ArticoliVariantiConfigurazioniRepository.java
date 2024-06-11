package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliVariantiConfigurazioniRepository extends JpaRepository<ArticoliVariantiConfigurazioni, Long> {
    @Query("SELECT avc FROM ArticoliVariantiConfigurazioni avc")
    List<ArticoliVariantiConfigurazioni> getArticoliVariantiConfigurazioni();
}
