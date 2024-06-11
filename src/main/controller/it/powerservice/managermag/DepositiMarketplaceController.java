package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DepositiMarketplaceController {
    @Autowired
    DepositiMarketplaceService depositiMarketplaceService;

    @GetMapping("/getDepositiMarketplace")
    public void getDepositiMarketplace() {
        var res = depositiMarketplaceService.getDepositiMarketplace();
        for (DepositiMarketplace dm: res) {
            System.out.println(dm);
        }
    }
}
