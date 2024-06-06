package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnagraficheRepository extends JpaRepository<Anagrafiche, Long> {
    @Query("SELECT a FROM Anagrafiche a")
    List<Anagrafiche> getAnagrafiche();
}
