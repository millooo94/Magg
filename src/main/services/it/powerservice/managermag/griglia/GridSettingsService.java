package it.powerservice.managermag.griglia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridSettingsService {
    @Autowired
    GridSettingsRepository gridSettingsRepository;

    public GridSettings getGridSettings(String section) {
        return gridSettingsRepository.getGridSettings(section);
    }

    public void save(GridSettings gridSettings) {
        gridSettingsRepository.save(gridSettings);
    }

}
