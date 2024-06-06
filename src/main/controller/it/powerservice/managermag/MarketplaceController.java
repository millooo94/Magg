package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MarketplaceController {
    @Autowired
    MarketplaceService marketplaceService;

    @GetMapping("/getMarketplace")
    public void getMarketplace() {
        var res = marketplaceService.getMarketplace();
        for (Marketplace m: res) {
            System.out.println(m);
        }
    }
}
