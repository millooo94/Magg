package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzi, Long> {
    @Query("SELECT i FROM Indirizzi i")
    List<Indirizzi> getIndirizzi();

    @Query("SELECT i FROM Indirizzi i WHERE i.id = :id")
    Indirizzi getIndirizziFromId(@Param("id") long id);

    @Query("SELECT i FROM Indirizzi i WHERE i.idAnagrafica = :idAnagrafica")
    List<Indirizzi> getIndirizziFromIdAnagrafica(@Param("idAnagrafica") long idAnagrafica);

    @Query("SELECT i FROM Indirizzi i WHERE i.idAnagrafica = :idAnagrafica AND i.tipoIndirizzo = 'S'")
    Indirizzi getIndirizzoSedePrincipaleFromIdAnagrafica(@Param("idAnagrafica") long idAnagrafica);
}