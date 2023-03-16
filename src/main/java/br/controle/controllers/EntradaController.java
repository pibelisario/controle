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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;


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



    @GetMapping("/entradas")
    public ModelAndView entradas(){
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0,10, Sort.by("id").descending())));
        return mv;
    }

    @GetMapping("/excluirEntrada/{idEntrada}")
    public ModelAndView excluirEntrada(@PathVariable("idEntrada")Long id){
        ModelAndView mv = new ModelAndView("redirect:/entradas");
        entradaService.excluirEntrada(id);
        return mv;
    }

    @GetMapping("/entradasPag")
    public ModelAndView carregarEntradas(@PageableDefault(size = 5) org.springframework.data.domain.Pageable pageable,
                                        ModelAndView model) {
        model.addObject("entradas", entradaService.findAll(PageRequest.of(pageable.getPageNumber(), 10, Sort.by("id").descending())));
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
        if (cadastros.isEmpty()){
            mv.addObject("mensagem", "Nenhum registo encontrado para o RG: " +rg);
        }
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 10, Sort.by("id").descending())));
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }


    @PostMapping("buscarNomeEntrada")
    public ModelAndView buscarNomeEntrada(@RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByNome(nome);
        if (cadastros.isEmpty()){
            mv.addObject("mensagem", "Nenhum registo encontrado para o NOME: " +nome);
        }
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 10, Sort.by("id").descending())));
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }


    @PostMapping("buscarCpfEntrada")
    public ModelAndView buscarCpfEntrada(@RequestParam("cpf") String cpf){
        ModelAndView mv = new ModelAndView("/entradas");
        List<Cadastro> cadastros = cadastroService.findByCpf(cpf);
        if (cadastros.isEmpty()){
            mv.addObject("mensagem", "Nenhum registo encontrado para o CPF: " +cpf);
        }
        mv.addObject("cadastros", cadastros);
        mv.addObject("entradas", entradaService.findAll(PageRequest.of(0, 10, Sort.by("id").descending())));
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


    @GetMapping("buscarEntrada")
    public ModelAndView buscar(){
        ModelAndView mv = new ModelAndView("/buscarEntrada");
//        mv.addObject("listaEntradas",  null);
        return mv;
    }

    @GetMapping("buscarData")
    public ModelAndView buscarData(@RequestParam("dataInicial")String dataInicial, @RequestParam("dataFinal")String dataFinal) throws ParseException {
        ModelAndView mv = new ModelAndView("/buscarEntrada");
        Page<Entrada> entradas = entradaService.buscarPorDatas(dataInicial, dataFinal, PageRequest.of(0, 15));
        if (entradas.isEmpty()){
            mv.addObject("mensagem", "Nenhum registo encontrado para as datas entre: " +dataInicial+ " e " +dataFinal);
        }
        mv.addObject("listaEntradas", entradas);
        return mv;
    }

    @GetMapping("BuscarDataPag")
    public ModelAndView carregarDatasPaginacao(@PageableDefault(size = 10) org.springframework.data.domain.Pageable pageable,
                                         ModelAndView model) {
        model.addObject("listaEntradas", entradaService.findAll(PageRequest.of(pageable.getPageNumber(), 15)));
        model.setViewName("/buscarEntrada");
        return model;
    }




}
