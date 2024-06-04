package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DizionariIvaController {
    @Autowired
    DizionariIvaService dizionariIvaService;

    @GetMapping("/getDizionariIva")
    public void getDizionariIva() {
        var res = dizionariIvaService.getDizionariIva();
        for (DizionariIva di: res) {
            System.out.println(di);
        }
    }
}
