package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpostazioniValoriRepository extends JpaRepository<ImpostazioniValori, String> {

    @Query("SELECT iv FROM ImpostazioniValori iv")
    List<ImpostazioniValori> getImpostazioniValori();

}
