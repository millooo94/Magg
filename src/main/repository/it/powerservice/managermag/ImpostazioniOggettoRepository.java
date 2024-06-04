package it.powerservice.managermag;


import it.powerservice.managermag.keys.ImpostazioniOggettoPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import  java.util.*;

@Repository
public interface ImpostazioniOggettoRepository extends JpaRepository<ImpostazioniOggetto, ImpostazioniOggettoPKId> {

    @Query("SELECT io from ImpostazioniOggetto io")
    List<ImpostazioniOggetto> getImpostazioniOggetto();
}
