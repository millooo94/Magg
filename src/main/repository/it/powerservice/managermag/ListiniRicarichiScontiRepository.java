package it.powerservice.managermag;

import it.powerservice.managermag.keys.ListiniRicarichiScontiPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListiniRicarichiScontiRepository extends JpaRepository<ListiniRicarichiSconti, ListiniRicarichiScontiPKId> {
    @Query("SELECT lrs FROM ListiniRicarichiSconti lrs")
    List<ListiniRicarichiSconti> getListiniRicarichiSconti();
}
