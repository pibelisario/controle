package br.controle.services;

import br.controle.models.AdcModel;
import br.controle.repositories.AdcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdcService {

    AdcRepository adcRepository;

    public AdcService(AdcRepository adcRepository) {
        this.adcRepository = adcRepository;
    }

    public List<AdcModel> findAll(){
        return adcRepository.findAll();
    }
}
