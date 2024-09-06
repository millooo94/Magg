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


    @Query("SELECT a FROM Anagrafiche a WHERE a.tipo = :tipo OR (" +
            "(:tipoCheck = 'Clienti' AND a.ckCliente = true) OR " +
            "(:tipoCheck = 'Fornitori' AND a.ckFornitore = true) OR " +
            "(:tipoCheck = 'Trasportatori' AND a.ckTrasportatore = true) OR " +
            "(:tipoCheck = 'Agenti' AND a.ckAgente = true) OR " +
            "(:tipoCheck = 'Personale' AND a.ckPersonale = true)" +
            ")")
    List<Anagrafiche> getAnagraficheFromTipo(@Param("tipo") String tipo, @Param("tipoCheck") String tipoCheck);



}
