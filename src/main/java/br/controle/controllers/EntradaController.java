package br.controle.controllers;

import br.controle.enums.CategoriaCadastro;
import br.controle.enums.LocalEntrada;
import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.repositories.EntradaRepository;
import br.controle.services.CadastroService;
import br.controle.services.EntradaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.boot.autoconfigure.web.format.DateTimeFormatters.formatter;

@RestController
public class EntradaController {

    EntradaService entradaService;
    CadastroService cadastroService;

    EntradaRepository entradaRepository;

    public EntradaController(EntradaService entradaService, CadastroService cadastroService, EntradaRepository entradaRepository) {
        this.entradaService = entradaService;
        this.cadastroService = cadastroService;
        this.entradaRepository = entradaRepository;
    }

    //    @GetMapping("/entradas")
//    public ModelAndView entradas() {
//        Pageable pageable = PageRequest.of(0, 2);
//        List<Entrada> entradas = entradaService.findAll();
//        ModelAndView mv = new ModelAndView("/entradas");
//        mv.addObject("entradas", entradaService.findAlll());
//        return mv;
//    }

    @GetMapping("/entradas")
    public ModelAndView entradas(){
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0,4, Sort.by("id").descending())));
        return mv;
    }

    @GetMapping("/entradasPag")
    public ModelAndView carregarPessoas(@PageableDefault(size = 4) org.springframework.data.domain.Pageable pageable,
                                        ModelAndView model) {
        model.addObject("entradas", entradaService.findAll(PageRequest.of(pageable.getPageNumber(), 4, Sort.by("id").descending())));
        model.setViewName("/entradas");
        return model;
    }

    @GetMapping("/detalhes/{idCadastro}")
    public ModelAndView detalhes(@PathVariable("idCadastro")Long id){
        ModelAndView mv = new ModelAndView("/detalhes");
        mv.addObject("objCadastro", cadastroService.findById(id));
        mv.addObject("listaCategorias", CategoriaCadastro.values());
        return mv;
    }

    @PostMapping("buscarRgEntrada")
    public ModelAndView buscarRgEntrada(@RequestParam("rg") String rg){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByRg(rg);
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 4, Sort.by("id").descending())));
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }


    @PostMapping("buscarNomeEntrada")
    public ModelAndView buscarNomeEntrada(@RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByNome(nome);
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 4, Sort.by("id").descending())));
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }


    @PostMapping("buscarCpfEntrada")
    public ModelAndView buscarCpfEntrada(@RequestParam("cpf") String cpf){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByCpf(cpf);
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 4, Sort.by("id").descending())));
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }


    @PostMapping("salvarEntrada")
    public  ModelAndView salvarEntrada(Entrada entrada){
        ModelAndView mv = new ModelAndView("redirect:/entradas");
        entradaService.salvar(entrada);
        System.out.println(entrada.getLocal());
        return mv;
    }

    @GetMapping("data")
    public ModelAndView data(){
        ModelAndView mv = new ModelAndView("/data");
        return mv;
    }

    @GetMapping("buscarData")
    public ModelAndView buscarData(@RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal){
        ModelAndView mv = new ModelAndView("/data");
        System.out.println("Data Inicial: " +dataInicial+ " Data Final: " +dataFinal);
        List<Entrada> entradas = entradaRepository.findByDatas(dataInicial, dataFinal);
        return mv;
    }


}
