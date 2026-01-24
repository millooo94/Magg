package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnagraficheRepository extends JpaRepository<Anagrafiche, Long> {
    @Query("SELECT a FROM Anagrafiche a")
    List<Anagrafiche> getAnagrafiche();


    @Query("""
    SELECT a FROM Anagrafiche a WHERE 
        (:ckCliente = true AND a.ckCliente = true) OR
        (:ckFornitore = true AND a.ckFornitore = true) OR
        (:ckTrasportatore = true AND a.ckTrasportatore = true) OR
        (:ckAgente = true AND a.ckAgente = true) OR
        (:ckPersonale = true AND a.ckPersonale = true)
""")
    List<Anagrafiche> getAnagraficheFromTipo(
            @Param("ckCliente") boolean ckCliente,
            @Param("ckFornitore") boolean ckFornitore,
            @Param("ckTrasportatore") boolean ckTrasportatore,
            @Param("ckAgente") boolean ckAgente,
            @Param("ckPersonale") boolean ckPersonale
    );


    @Query("SELECT a FROM Anagrafiche a WHERE a.nome LIKE CONCAT('%', :searchWord, '%') OR a.cognome LIKE CONCAT('%', :searchWord, '%') OR a.ragioneSociale LIKE CONCAT('%', :searchWord, '%')")
    List<Anagrafiche> getAnagraficheFromSearch(@Param("searchWord") String searchWord);

}
