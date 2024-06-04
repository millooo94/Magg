package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpostazioniCampiRepository extends JpaRepository<ImpostazioniCampi, String> {

    @Query("SELECT ic FROM ImpostazioniCampi ic")
    List<ImpostazioniCampi> getImpostazioniCampi();


}
