package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzi, Long> {
    @Query("SELECT i FROM Indirizzi i")
    List<Indirizzi> getIndirizzi();
}