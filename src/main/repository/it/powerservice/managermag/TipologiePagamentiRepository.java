package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipologiePagamentiRepository extends JpaRepository<TipologiePagamenti, Long> {
    @Query("SELECT tp FROM TipologiePagamenti tp")
    List<TipologiePagamenti> getTipologiePagamenti();
}
