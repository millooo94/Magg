package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AllegatiWebMarketplaceController {
    @Autowired
    AllegatiWebMarketplaceService allegatiWebMarketplaceService;

    @GetMapping("/getAllegatiWebMarketplace")
    public void getAllegatiWebMarketplace() {
        var res = allegatiWebMarketplaceService.getAllegatiWebMarketplace();
        for (AllegatiWebMarketplace awm: res) {
            System.out.println(awm);
        }
    }
}
