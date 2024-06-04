package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancheRepository extends JpaRepository<Banche, Long> {
    @Query("SELECT b FROM Banche b")
    List<Banche> getBanche();
}
