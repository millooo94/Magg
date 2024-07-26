package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DizionariRepository extends JpaRepository<Dizionari, String> {
    @Query("SELECT d FROM Dizionari d")
    List<Dizionari> getDizionari();

    @Modifying
    @Transactional
    @Query("UPDATE Dizionari d SET d.Descrizione = :newDescrizione WHERE d.Codice = :codice")
    void updateDescrizioneDizionario(@Param("codice") String codice, @Param("newDescrizione") String newDescrizione);


}
