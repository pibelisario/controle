package br.controle.controllers;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.services.EntradaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EntradaController {

    EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping("/entradas")
    public ModelAndView entradas(){
        List<Entrada> entradas = entradaService.findAll();
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradas);
        return mv;
    }

    @PostMapping("buscarRgEntrada")
    public ModelAndView buscarRgEntrada(@PathVariable("rg") Long rg){
        ModelAndView mv = new ModelAndView("entradas");
    }
}
