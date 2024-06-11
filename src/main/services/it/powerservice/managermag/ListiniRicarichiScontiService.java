package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListiniRicarichiScontiService {
    @Autowired
    ListiniRicarichiScontiRepository listiniRicarichiScontiRepository;

    public List<ListiniRicarichiSconti> getListiniRicarichiSconti() {
        return listiniRicarichiScontiRepository.getListiniRicarichiSconti();
    }
}
