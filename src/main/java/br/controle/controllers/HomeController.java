package br.controle.controllers;

import br.controle.models.Entrada;
import br.controle.services.EntradaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    EntradaService entradaService;

    public HomeController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

//    @GetMapping("/")
//    public ModelAndView entradas(){
//        ModelAndView mv = new ModelAndView("/entradas");
//        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0,5, Sort.by("id").descending())));
//        return mv;
//    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
