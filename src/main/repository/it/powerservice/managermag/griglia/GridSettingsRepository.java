package it.powerservice.managermag.griglia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GridSettingsRepository extends JpaRepository<GridSettings, Long> {

    @Query("SELECT gs FROM GridSettings gs WHERE gs.grid = :section")
    GridSettings getGridSettings(String section);
}
