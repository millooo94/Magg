package it.powerservice.managermag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DizionariIvaRepository extends JpaRepository<DizionariIva, String> {
    @Query("SELECT di FROM DizionariIva di")
    List<DizionariIva> getDizionariIva();
}
