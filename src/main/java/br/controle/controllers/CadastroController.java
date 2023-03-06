package br.controle.controllers;

import br.controle.enums.CategoriaCadastro;
import br.controle.enums.LocalEntrada;
import br.controle.models.Cadastro;
import br.controle.services.CadastroService;
import br.controle.services.EntradaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CadastroController {

   CadastroService cadastroService;
   EntradaService entradaService;

    public CadastroController(CadastroService cadastroService, EntradaService entradaService) {
        this.cadastroService = cadastroService;
        this.entradaService = entradaService;
    }

    @GetMapping("/teste")
    public ModelAndView teste(){
        ModelAndView mv = new ModelAndView("teste");
        mv.addObject("listaLocais", LocalEntrada.values());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("/cadastro");
        mv.addObject("listaCategorias", CategoriaCadastro.values());
        mv.addObject("objCadastro", new Cadastro());
        return mv;
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Cadastro cadastro){
        ModelAndView mv = new ModelAndView("redirect:/entradas");
        cadastroService.save(cadastro);
        return mv;
    }


    @GetMapping("/buscar")
    public ModelAndView buscar(){
        ModelAndView mv = new ModelAndView("/buscar");
        return mv;
    }

    @PostMapping("buscarRg")
    public ModelAndView buscarRg(@RequestParam("rg") String rg){
        ModelAndView mv = new ModelAndView("/buscar");
        List<Cadastro> rgLista = cadastroService.findByRg(rg);
        mv.addObject("lista", rgLista);
        System.out.println(rg);
        return mv;
    }

    @PostMapping("buscarCpf")
    public ModelAndView buscarCpf(@RequestParam("cpf") String cpf){
        ModelAndView mv = new ModelAndView("/buscar");
        List<Cadastro> cpfList = cadastroService.findByCpf(cpf);
        mv.addObject("lista", cpfList);
        System.out.println(cpf);
        return mv;
    }

    @PostMapping("buscarNome")
    public ModelAndView buscarNome(@RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("/buscar");
        List<Cadastro> nomeList = cadastroService.findByNome(nome);
        mv.addObject("lista", nomeList);
        System.out.println(nome);
        return mv;
    }
    @GetMapping("/excluirCadastro/{idCadastro}")
    public ModelAndView excluirCadastro(@PathVariable("idCadastro") Long id){
        Cadastro cadastro = cadastroService.findById(id);
        cadastroService.excluirCadastro(id);
        List<Cadastro> cadastros = cadastroService.findByRg(cadastro.getRg());
        ModelAndView mv = new ModelAndView("/buscar");
        mv.addObject("lista", cadastros);
        System.out.println(id);
        return mv;
    }

    @GetMapping("/editarCadastro/{idCadastro}")
    public ModelAndView editarCadasro(@PathVariable("idCadastro") Long id) {
        ModelAndView mv = new ModelAndView("/editar");
        mv.addObject("objCadastro", cadastroService.findById(id));
        mv.addObject("listaCategorias", CategoriaCadastro.values());
        System.out.println(id);
        return mv;
    }

    @PostMapping("/editarCadastro/{idCadastro}")
    public ModelAndView editarCadastro(@PathVariable("idCadastro") Long id, Cadastro cadastro){
        cadastroService.editar(id, cadastro);
        ModelAndView mv = new ModelAndView("redirect:/buscar");
        return mv;
    }


}
