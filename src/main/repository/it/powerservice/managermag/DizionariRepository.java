package it.powerservice.managermag;

import it.powerservice.managermag.keys.DizionariPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DizionariRepository extends JpaRepository<Dizionari, DizionariPKId> {
    @Query("SELECT d FROM Dizionari d")
    List<Dizionari> getDizionari();
}
