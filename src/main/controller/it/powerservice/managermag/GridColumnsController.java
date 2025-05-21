package it.powerservice.managermag;

import it.powerservice.managermag.griglia.GridColumn;
import it.powerservice.managermag.griglia.GridColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.zul.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GridColumnsController {
    @Autowired
    GridColumnsService gridColumnsService;

    @GetMapping("/anagraficheGridColumns")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<GridColumn> getAnagrafiche() {
        return gridColumnsService.getAnagraficheColModel();
    }

    @GetMapping("/marcheGridColumns")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<GridColumn> getMarche() {
        return gridColumnsService.getMarcheColModel();
    }

    @PatchMapping("/updateGridColumns")
    public ResponseEntity<Map<String, String>> updateGridColumns(@RequestBody List<GridColumn> gridColumns) {
        gridColumnsService.saveAll(gridColumns);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Dati salvati con successo");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/updateSingleColumn")
    public ResponseEntity<Map<String, String>> updateSingleColumn(@RequestBody Map<String, Object> request) {
        Long id = ((Number) request.get("id")).longValue();
        String type = request.get("type").toString();
        String subtype = request.getOrDefault("subtype", "").toString();
        String width = request.getOrDefault("width", "").toString();
        System.out.println("REQUEST =====> " + width);
        gridColumnsService.saveSingle(id, type, subtype, Integer.valueOf(width));

        Map<String, String> response = new HashMap<>();
        response.put("message", "Dati salvati con successo");
        return ResponseEntity.ok(response);
    }

}
