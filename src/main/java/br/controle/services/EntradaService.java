package br.controle.services;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.repositories.EntradaRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Page<Entrada> findAll(Pageable pageable){
        return entradaRepository.findAll(pageable);
    }


    public void salvar(Entrada entrada){
        entrada.setData(new Date());
        entradaRepository.save(entrada);
    }

//    public List<Entrada> buscarPorDatas(String dataInicial, String dataFinal) throws ParseException {
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//        Date dataInicialF = formato.parse(dataInicial);
//        Date dataFinalF = formato.parse(dataFinal);
//        dataFinalF.setHours(23);
//        List<Entrada> entradas = entradaRepository.findEntradasByDataBetween(dataInicialF, dataFinalF);
//        Collections.reverse(entradas);
//        return entradas;
//    }

    public Page<Entrada> buscarPorDatas(String dataInicial, String dataFinal, Pageable pageable) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicialF = formato.parse(dataInicial);
        Date dataFinalF = formato.parse(dataFinal);
        dataFinalF.setHours(23);
        return entradaRepository.findEntradasByDataBetween(dataInicialF, dataFinalF, pageable);
    }

}
