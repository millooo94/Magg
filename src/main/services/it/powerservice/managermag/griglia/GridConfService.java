package it.powerservice.managermag.griglia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridConfService {
    @Autowired
    GridConfRepository gridConfRepository;

    public GridConf getGridConf() {
        return gridConfRepository.getGridConf();
    }
    public void setGridConf(String newSection) {
        gridConfRepository.updateGridConfName(newSection);
    }
}
