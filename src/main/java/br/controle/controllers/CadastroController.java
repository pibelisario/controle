package br.controle.controllers;

import br.controle.enums.CategoriaCadastro;
import br.controle.models.Cadastro;
import br.controle.repositories.CadastroRepository;
import br.controle.services.CadastroService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/adc")
public class CadastroController {

    CadastroService cadastroService;
    CadastroRepository cadastroRepository;

    public CadastroController(CadastroService cadastroService, CadastroRepository cadastroRepository) {
        this.cadastroService = cadastroService;
        this.cadastroRepository = cadastroRepository;
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

        List<Cadastro> entradas = cadastroService.findAll();
        ModelAndView mv = new ModelAndView("/entradas");
        mv.addObject("entradas", entradas);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("/cadastro");
        mv.addObject("listaCategorias", CategoriaCadastro.values());
        mv.addObject("objCadastro", new Cadastro());
        return mv;
    }

    @PostMapping()
    public ModelAndView salvar(Cadastro cadastro){
        ModelAndView mv = new ModelAndView("redirect:adc/entradas");
        cadastroService.save(cadastro);
        return mv;
    }


    @GetMapping("/buscar")
    public ModelAndView buscar(){
        ModelAndView mv = new ModelAndView("/buscar");
        return mv;
    }

    @PostMapping("/buscarRg")
    public ModelAndView buscarRg(@RequestParam("rg") String rg){
        ModelAndView mv = new ModelAndView("/buscar");
        List<Cadastro> rgLista = cadastroService.findByRg(rg);
        mv.addObject("lista", rgLista);
        System.out.println(rg);
        return mv;
    }

    @PostMapping("/buscarCpf")
    public ModelAndView buscarCpf(@RequestParam("cpf") String cpf){
        ModelAndView mv = new ModelAndView("/buscar");
        List<Cadastro> cpfList = cadastroService.findByCpf(cpf);
        mv.addObject("lista", cpfList);
        System.out.println(cpf);
        return mv;
    }

    @PostMapping("/buscarNome")
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


}