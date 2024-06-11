package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListiniRicarichiScontiController {
    @Autowired
    ListiniRicarichiScontiService listiniRicarichiScontiService;

    @GetMapping("/getListiniRicarichiSconti")
    public void getListiniRicarichiSconti() {
        var res = listiniRicarichiScontiService.getListiniRicarichiSconti();
        for (ListiniRicarichiSconti lrs: res) {
            System.out.println(res);
        }
    }
}
