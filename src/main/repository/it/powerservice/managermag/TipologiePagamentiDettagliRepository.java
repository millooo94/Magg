package it.powerservice.managermag;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipologiePagamentiDettagliRepository extends JpaRepository<TipologiePagamentiDettagli, Long> {
    @Query("SELECT tpd FROM TipologiePagamentiDettagli tpd")
    List<TipologiePagamentiDettagli> getTipologiePagamentiDettagli();
    @Query("SELECT tpd FROM TipologiePagamentiDettagli tpd WHERE tpd.idPagamento = :idTipologiePagamenti")
    List<TipologiePagamentiDettagli> findTipologiePagamentiDettagliFromIdTipologiePagamenti(@Param("idTipologiePagamenti") Long idTipologiePagamenti);






}
