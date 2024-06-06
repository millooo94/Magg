package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipologiePagamentiDettagliRepository extends JpaRepository<TipologiePagamentiDettagli, Long> {
    @Query("SELECT tpd FROM TipologiePagamentiDettagli tpd")
    List<TipologiePagamentiDettagli> getTipologiePagamentiDettagli();
}
