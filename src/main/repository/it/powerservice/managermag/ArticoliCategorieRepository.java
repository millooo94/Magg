package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliCategoriePKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoliCategorieRepository extends JpaRepository<ArticoliCategorie, ArticoliCategoriePKId> {
    @Query("SELECT ac FROM ArticoliCategorie ac")
    List<ArticoliCategorie> getArticoliCategorie();
}
