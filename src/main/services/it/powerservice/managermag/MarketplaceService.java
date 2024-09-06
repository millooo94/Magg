package it.powerservice.managermag;

import com.powerservice.managermag.depositiMarketplace.utilities.CustomDepositiMarketplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MarketplaceService {
    @Autowired
    MarketplaceRepository marketplaceRepository;
    public List<Marketplace> getMarketplace() {
        return marketplaceRepository.getMarketplace();
    }

    public Listini getListinoByIdListino(Long marketplaceId) {
        return  marketplaceRepository.getListinoByIdListino(marketplaceId);
    }
    public Listini getListinoIngleseByIdListinoInglese(Long marketplaceId) {
        return  marketplaceRepository.getListinoIngleseByIdListinoInglese(marketplaceId);
    }
    public void saveMarketplace(Marketplace marketplace) {
        marketplaceRepository.save(marketplace);
    }



}
