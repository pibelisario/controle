package br.controle.controllers;

import br.controle.models.AdcModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/adc")
public class AdcController {

    @GetMapping("/teste")
    public String index(){
        return "testando";
    }

    @GetMapping("/entradas")
    public ModelAndView entradas(){
        AdcModel a1 = new AdcModel();
        a1.setRg("5370184");
        a1.setNome("Paulo");
        a1.setCpf("700.001.781-36");
        a1.setTipo("Associado");
        a1.setDia(LocalDate.now());
        a1.setHora(LocalDateTime.now());

        List<AdcModel> entradas = new ArrayList<>();
        entradas.add(a1);

        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradas);
        return mv;
    }



}
