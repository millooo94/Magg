package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnagraficheInfoTrasportatoriRepository extends JpaRepository<AnagraficheInfoTrasportatori, Long> {
    @Query("SELECT ait FROM AnagraficheInfoTrasportatori ait")
    List<AnagraficheInfoTrasportatori> getAanagraficheInfoTrasportatori();
}
