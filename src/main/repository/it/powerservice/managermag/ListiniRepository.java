package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListiniRepository extends JpaRepository<Listini, Long> {
    @Query("SELECT i FROM Listini i")
    List<Listini> getListini();
}
