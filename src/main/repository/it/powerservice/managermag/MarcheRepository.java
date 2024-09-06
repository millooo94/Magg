package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcheRepository extends JpaRepository<Marche, Long> {
    @Query("SELECT m FROM Marche m")
    List<Marche> getMarche();


}
