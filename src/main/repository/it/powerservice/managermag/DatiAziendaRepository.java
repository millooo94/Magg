package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatiAziendaRepository extends JpaRepository<DatiAzienda, Long> {
    @Query("SELECT da FROM DatiAzienda da")
    List<DatiAzienda> getDatiAzienda();
}
