package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliRepository extends JpaRepository<Articoli, Long> {
    @Query("SELECT a FROM Articoli a")
    List<Articoli> getArticoli();
}
