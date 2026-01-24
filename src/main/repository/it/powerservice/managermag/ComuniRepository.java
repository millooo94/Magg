package it.powerservice.managermag;

import it.powerservice.managermag.geography.Comuni;
import it.powerservice.managermag.geography.Province;
import it.powerservice.managermag.geography.Regioni;
import it.powerservice.managermag.geography.Cap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComuniRepository extends JpaRepository<Comuni, Long> {
    @Query("SELECT c FROM Comuni c")
    List<Comuni> getComuni();
    @Query("SELECT DISTINCT new it.powerservice.managermag.geography.Regioni(c.regione, c.codReg) FROM Comuni c")
    List<Regioni> getRegioni();
    @Query("SELECT DISTINCT new it.powerservice.managermag.geography.Province(c.provincia, c.provinciaCompleta, c.codProv) FROM Comuni c WHERE c.regione = :regione")
    List<Province> getProvinceFromRegione(@Param("regione") String regione);
    @Query("SELECT DISTINCT new it.powerservice.managermag.geography.Comuni(c.citta, c.codCom) FROM Comuni c WHERE c.provinciaCompleta = :provincia")
    List<Comuni> getComuniFromProvincia(@Param("provincia") String provincia);
    @Query("SELECT DISTINCT new it.powerservice.managermag.geography.Cap(c.cap) FROM Comuni c WHERE c.citta = :comune")
    List<Cap> getCapFromComune(@Param("comune") String comune);
}
