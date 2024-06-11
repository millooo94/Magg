package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllegatiRepository extends JpaRepository<Allegati, Long> {
    @Query("SELECT a FROM Allegati a")
    List<Allegati> getAllegati();
}
