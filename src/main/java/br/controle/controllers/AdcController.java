package br.controle.controllers;

import br.controle.models.AdcModel;
import br.controle.repositories.AdcRepository;
import br.controle.services.AdcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/adc")
public class AdcController {

    AdcService adcService;
    AdcRepository adcRepository;

    public AdcController(AdcService adcService, AdcRepository adcRepository) {
        this.adcService = adcService;
        this.adcRepository = adcRepository;
    }

    @GetMapping("/teste")
    public String index(){
        return "testando";
    }

    @GetMapping("/entradas")
    public ModelAndView entradas(){
//            AdcModel a1 = new AdcModel();
//            a1.setRg("5370184");
//            a1.setNome("Paulo");
//            a1.setCpf("700.001.781-36");
//            a1.setTipo("Associado");
//            a1.setData(new Date());
//
//            AdcModel a2 = new AdcModel();
//            a1.setRg("65651651");
//            a1.setNome("Maria");
//            a1.setCpf("273.757.651-20");
//            a1.setTipo("Convidada");
//            a1.setData(new Date());
//
//            List<AdcModel> entradas = new ArrayList<>();
//            entradas.add(a1);
//            entradas.add(a2);

        List<AdcModel> entradas = adcService.findAll();
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradas);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("/cadastro");
        return mv;
    }

    @PostMapping()
    public ModelAndView salvar(AdcModel adcModel){
        ModelAndView mv = new ModelAndView("redirect:adc/entradas");
        adcService.save(adcModel);
        return mv;
    }



}
