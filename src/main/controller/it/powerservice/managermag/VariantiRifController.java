package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VariantiRifController {
    @Autowired
    VariantiRifService variantiRifService;

    @GetMapping("/getVariantiRif")
    public void getVariantiRif() {
        var res = variantiRifService.getVariantiRif();
        for (VariantiRif vr: res) {
            System.out.println(vr);
        }
    }
}
