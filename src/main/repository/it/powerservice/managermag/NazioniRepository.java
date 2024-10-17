package it.powerservice.managermag;

import it.powerservice.managermag.geography.Nazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NazioniRepository extends JpaRepository<Nazioni, Long> {

}
