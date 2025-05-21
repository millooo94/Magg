package it.powerservice.managermag;

import it.powerservice.managermag.griglia.GridColumn;
import it.powerservice.managermag.griglia.GridSettings;
import it.powerservice.managermag.griglia.GridSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.zul.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gridsettings")
public class GridSettingsController {
    @Autowired
    GridSettingsService gridSettingsService;
    @GetMapping("/{section}")
    @CrossOrigin(origins = "http://localhost:5173")
    public GridSettings getGridSettings(@PathVariable String section) {
        return gridSettingsService.getGridSettings(section);
    }

    @PatchMapping("/update")
    public ResponseEntity<Map<String, String>> updateGridColumns(@RequestBody GridSettings gridSettings) {
        System.out.println("PATCH /gridsettings/update called");
        gridSettingsService.save(gridSettings);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Dati salvati con successo");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/setfreeze")
    public ResponseEntity<Map<String, String>> updateSingleColumn(@RequestBody Map<String, Object> request) {
        String section = (String) request.get("section");

        Optional<GridSettings> gridSettings = Optional.ofNullable(gridSettingsService.getGridSettings(section));

        if (gridSettings.isPresent()) {
            gridSettings.get().setFreeze((Integer) request.get("freeze"));
            gridSettingsService.save(gridSettings.get());
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Dati salvati con successo");
        return ResponseEntity.ok(response);
    }

}
