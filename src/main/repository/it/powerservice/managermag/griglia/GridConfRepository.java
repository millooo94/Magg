package it.powerservice.managermag.griglia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GridConfRepository extends JpaRepository<GridConf, Long> {
    @Query("SELECT gc FROM GridConf gc")
    GridConf getGridConf();
    @Transactional
    @Modifying
    @Query("UPDATE GridConf gc SET gc.section = :newSection WHERE gc.id = 1")
    void updateGridConfName(String newSection);

}
