package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllegatiService {
    @Autowired
    AllegatiRepository allegatiRepository;
    public List<Allegati> getAllegati() {
        return  allegatiRepository.getAllegati();
    }
}
