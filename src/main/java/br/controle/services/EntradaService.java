package br.controle.services;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import br.controle.repositories.EntradaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntradaService {

    EntradaRepository entradaRepository;
    CadastroService cadastroService;

    public EntradaService(EntradaRepository entradaRepository, CadastroService cadastroService) {
        this.entradaRepository = entradaRepository;
        this.cadastroService = cadastroService;
    }

    public List<Entrada> findAll(){
        return entradaRepository.findAll();
    }

    public void salvar(Long id){
        Cadastro cadastro = cadastroService.findById(id);
        if (cadastro != null){
            Entrada entrada = new Entrada();
            entrada.setCadastro(cadastroService.findById(id));
            entrada.setData(new Date());
            entrada.setLocal("ODONTOLOGIA");
            entradaRepository.save(entrada);
        }
    }
}
