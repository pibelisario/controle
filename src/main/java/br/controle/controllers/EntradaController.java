package br.controle.controllers;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.services.CadastroService;
import br.controle.services.EntradaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EntradaController {

    EntradaService entradaService;
    CadastroService cadastroService;

    public EntradaController(EntradaService entradaService, CadastroService cadastroService) {
        this.entradaService = entradaService;
        this.cadastroService = cadastroService;
    }

    @GetMapping("/entradas")
    public ModelAndView entradas(){
        List<Entrada> entradas = entradaService.findAll();
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradas);
        return mv;
    }

    @PostMapping("buscarRgEntrada")
    public ModelAndView buscarRgEntrada(@RequestParam("rg") String rg){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByRg(rg);
        mv.addObject("cadastros", cadastros);
        List<Entrada> entradas = entradaService.findAll();
        mv.addObject("entradas", entradas);
        return mv;
    }

    @GetMapping("/salvarEntrada/{idCadastro}")
    public ModelAndView salvarEntrada(@PathVariable("idCadastro") Long id, Cadastro cadastro){
        entradaService.salvar(id);
        ModelAndView mv = new ModelAndView("redirect:/entradas");
        return mv;
    }
}
