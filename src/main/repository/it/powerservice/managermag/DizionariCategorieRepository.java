package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DizionariCategorieRepository extends JpaRepository<DizionariCategorie, String> {
    @Query("SELECT dc FROM DizionariCategorie dc")
    List<DizionariCategorie> getDizionariCategorie();
}
