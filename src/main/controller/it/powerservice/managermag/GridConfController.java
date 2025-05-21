package it.powerservice.managermag;

import it.powerservice.managermag.griglia.GridConf;
import it.powerservice.managermag.griglia.GridConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GridConfController {

    @Autowired
    GridConfService gridConfService;
    @GetMapping("/section")
    @CrossOrigin(origins = "http://localhost:5173")
    public GridConf getGridConf() {
        var conf = gridConfService.getGridConf();
        System.out.println(conf);
        return conf;
    }
}
