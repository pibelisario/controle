package br.controle.controllers;

import br.controle.enums.CategoriaCadastro;
import br.controle.enums.LocalEntrada;
import br.controle.models.Cadastro;
import br.controle.services.CadastroService;
import br.controle.services.EntradaService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    public ModelAndView cadastro(Cadastro cadastro) {
        ModelAndView mv = new ModelAndView("/cadastro");
        mv.addObject("listaCategorias", CategoriaCadastro.values());
        mv.addObject("objCadastro", new Cadastro());
        return mv;
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Cadastro cadastro, BindingResult result
            , RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("/cadastro");
            mv.addObject("objCadastro", cadastro);
            mv.addObject("listaCategorias", CategoriaCadastro.values());
            List<String> msg = new ArrayList<>();
            for (ObjectError objectError : result.getAllErrors()) {
                msg.add(objectError.getDefaultMessage());
            }
            mv.addObject("msg", msg);
            return mv;
        }
        ModelAndView mv = new ModelAndView("redirect:/cadastro");
        cadastroService.save(cadastro);
        attributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso.");
        return mv;
    }


    @GetMapping("/buscarCadastro")
    public ModelAndView buscar(){
        ModelAndView mv = new ModelAndView("/buscarCadastro");
        return mv;
    }

    @PostMapping("buscarRg")
    public ModelAndView buscarRg(@RequestParam("rg") String rg){
        ModelAndView mv = new ModelAndView("/buscarCadastro");
        List<Cadastro> rgLista = cadastroService.findByRg(rg);
        mv.addObject("lista", rgLista);
        System.out.println(rg);
        return mv;
    }

    @PostMapping("buscarCpf")
    public ModelAndView buscarCpf(@RequestParam("cpf") String cpf){
        ModelAndView mv = new ModelAndView("/buscarCadastro");
        List<Cadastro> cpfList = cadastroService.findByCpf(cpf);
        mv.addObject("lista", cpfList);
        System.out.println(cpf);
        return mv;
    }

    @PostMapping("buscarNome")
    public ModelAndView buscarNome(@RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("/buscarCadastro");
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
        ModelAndView mv = new ModelAndView("redirect:/buscarCadastro");
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
    public ModelAndView editarCadastro(@PathVariable("idCadastro") Long id, @Valid Cadastro cadastro, BindingResult result
            , RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()){
            mv.setViewName("redirect:/editarCadastro/" + id);
            mv.addObject("objCadastro", cadastro);
            mv.addObject("listaCategorias", CategoriaCadastro.values());
            List<String> msg = new ArrayList<>();
            for (ObjectError objectError : result.getAllErrors()) {
                msg.add(objectError.getDefaultMessage());
            }
            attributes.addFlashAttribute("msg", msg);
            return mv;
        }
        mv.setViewName("redirect:/editarCadastro/" + id);
        cadastroService.editar(id, cadastro);
        attributes.addFlashAttribute("mensagem", "Aualizado com sucesso.");
        return mv;
    }


}
