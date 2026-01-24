package it.powerservice.managermag.griglia;

import it.powerservice.managermag.griglia.AnagraficheGridColumn;
import it.powerservice.managermag.griglia.GridColumn;
import it.powerservice.managermag.griglia.MarcheGridColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zkoss.bind.annotation.QueryParam;

import java.util.List;
import java.util.Optional;

public interface GridColumnRepository extends JpaRepository<GridColumn, Long> {
    @Query("SELECT gc FROM GridColumn gc WHERE gc.grid = 'anagrafiche' AND gc.dataIndx != 'id'")
    List<GridColumn> getAnagraficheGridColumns();

    @Query("SELECT gc FROM GridColumn gc WHERE gc.grid = 'marche'")
    List<GridColumn> getMarcheGridColumns();

    Optional<GridColumn> findByTitle(String title);

    @Query("SELECT MAX(g.groupIndx) FROM GridColumn g")
    Optional<Integer> findMaxGroupIndex();
    @Query("SELECT MAX(g.sortIndx) FROM GridColumn g")
    Optional<Integer> findMaxSortIndex();


}
