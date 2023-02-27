package br.controle.services;

import br.controle.models.Entrada;
import br.controle.repositories.EntradaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    EntradaRepository entradaRepository;

    public EntradaService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public List<Entrada> findAll(){
        return entradaRepository.findAll();
    }
}
