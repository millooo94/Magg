package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceService {
    @Autowired
    MarketplaceRepository marketplaceRepository;
    public List<Marketplace> getMarketplace() {
        return marketplaceRepository.getMarketplace();
    }
}
