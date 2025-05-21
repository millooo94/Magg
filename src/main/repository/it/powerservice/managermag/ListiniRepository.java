package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zkoss.zhtml.Li;

import java.util.List;

@Repository
public interface ListiniRepository extends JpaRepository<Listini, Long> {
    @Query("SELECT i FROM Listini i")
    List<Listini> getListini();

    @Query("SELECT l FROM Listini l WHERE l.nome LIKE CONCAT('%', :searchWord, '%')")
    List<Listini> getListiniFromSearch(@Param("searchWord") String searchWord);
}
