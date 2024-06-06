package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantiRepository extends JpaRepository<Varianti, Long> {
    @Query("SELECT v FROM Varianti v")
    List<Varianti> getVarianti();

}
