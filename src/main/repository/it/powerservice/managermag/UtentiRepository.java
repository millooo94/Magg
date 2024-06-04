package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtentiRepository extends JpaRepository<Utenti, Long> {
    @Query("SELECT u FROM Utenti u")
    List<Utenti> getUtenti();
}
