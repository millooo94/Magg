package it.powerservice.managermag;

import it.powerservice.managermag.keys.CategorieRifPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRifRepository extends JpaRepository<CategorieRif, CategorieRifPKId> {
    @Query("SELECT cr FROM CategorieRif cr")
    List<CategorieRif> getCategorieRif();
}
