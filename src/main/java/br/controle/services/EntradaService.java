package br.controle.services;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.repositories.EntradaRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class EntradaService {

    EntradaRepository entradaRepository;
    CadastroService cadastroService;

    public EntradaService(EntradaRepository entradaRepository, CadastroService cadastroService) {
        this.entradaRepository = entradaRepository;
        this.cadastroService = cadastroService;
    }

//    public List<Entrada> findAll(){
//        List<Entrada> entradas = entradaRepository.findAll();
//        Collections.reverse(entradas);
//        return entradas;
//    }

    public Page<Entrada> findAlll(){
         Page<Entrada> entradas = entradaRepository.findAll(PageRequest.of(0,5, Sort.Direction.DESC, "data"));
        return entradas;
    }

    public void salvar(Entrada entrada){
        entrada.setData(new Date());
        entradaRepository.save(entrada);
    }




}
