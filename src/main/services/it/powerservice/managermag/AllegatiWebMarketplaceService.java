package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllegatiWebMarketplaceService {
    @Autowired AllegatiWebMarketplaceRepository allegatiWebMarketplaceRepository;

    public List<AllegatiWebMarketplace> getAllegatiWebMarketplace() {
        return allegatiWebMarketplaceRepository.getAllegatiWebMarketplace();
    }
}
