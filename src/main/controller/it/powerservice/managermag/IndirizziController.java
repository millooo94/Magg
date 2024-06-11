package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndirizziController {
    @Autowired
    IndirizziService indirizziService;;

    @GetMapping("/getIndirizzi")
    public void getIndirizzi() {
        var res = indirizziService.getIndirizzi();
        for (Indirizzi i: res) {
            System.out.println(i);
        }
    }

}