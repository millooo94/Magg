package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VariantiController {
    @Autowired
    VariantiService variantiService;

    @GetMapping("/getVarianti")
    public void getVarianti() {
        var res = variantiService.getVarianti();
        for (Varianti v: res) {
            System.out.println(v);
        }
    }
}
