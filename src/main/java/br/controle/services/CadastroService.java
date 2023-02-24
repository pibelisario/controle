package br.controle.services;

import br.controle.models.Cadastro;
import br.controle.repositories.CadastroRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CadastroService {

    CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    public List<Cadastro> findAll(){
        return cadastroRepository.findAll();
    }

    public void save(Cadastro cadastro){
        cadastro.setData(new Date());
        cadastroRepository.save(cadastro);
    }

    public List<Cadastro> findByRg(String rg){
        return cadastroRepository.findByRg(rg);
    }

    public List<Cadastro> findByCpf(String cpf){
        return cadastroRepository.findByCpf(cpf);
    }

    public List<Cadastro> findByNome(String nome){
        return cadastroRepository.findByNome(nome);
    }
}
