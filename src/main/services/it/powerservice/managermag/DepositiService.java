package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositiService {
    @Autowired
    DepositiRepository depositiRepository;

    public List<Depositi> getDepositi() {
        return depositiRepository.getDepositi();
    }
}
