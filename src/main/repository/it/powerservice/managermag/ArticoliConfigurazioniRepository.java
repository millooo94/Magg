package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliConfigurazioniRepository extends JpaRepository<ArticoliConfigurazioni, Long> {
    @Query("SELECT ac FROM ArticoliConfigurazioni ac")
    List<ArticoliConfigurazioni> getArticoliConfigurazioni();

}
