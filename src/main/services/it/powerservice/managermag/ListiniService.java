package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListiniService {
    @Autowired
    ListiniRepository listiniRepository;

    public List<Listini> getListini() {
        return listiniRepository.getListini();
    }
}
