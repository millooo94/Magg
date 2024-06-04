package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancheService {
    @Autowired
    BancheRepository bancheRepository;

    public List<Banche> getBanche() {
        return bancheRepository.getBanche();
    }
}
