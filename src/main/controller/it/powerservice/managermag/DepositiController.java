package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DepositiController {
    @Autowired
    DepositiService depositiService;
    @GetMapping("/getDepositi")
    public void getMapping() {
        var res = depositiService.getDepositi();
        for (Depositi d: res) {
            System.out.println(d);
        }
    }
}
