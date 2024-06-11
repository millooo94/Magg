package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepositiMarketplaceService {
    @Autowired
    DepositiMarketplaceRepository depositiMarketplaceRepository;
    public List<DepositiMarketplace> getDepositiMarketplace() {
        return depositiMarketplaceRepository.getDepositiMarketplace();
    }
}
