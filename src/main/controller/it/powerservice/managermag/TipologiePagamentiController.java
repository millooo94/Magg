package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipologiePagamentiController {
    @Autowired
    TipologiePagamentiService tipologiePagamentiService;

    @GetMapping("getTipologiePagamenti")
    public void getTipologiePagamenti() {
        var res = tipologiePagamentiService.getTipologiePagamenti();
        for (TipologiePagamenti tp: res) {
            System.out.println(tp);
        }
    }
}
