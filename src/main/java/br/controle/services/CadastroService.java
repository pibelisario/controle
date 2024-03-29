package br.controle.services;

import br.controle.models.Cadastro;
import br.controle.repositories.CadastroRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroService {

    CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    public Cadastro findById(Long id){
        return cadastroRepository.findById(id).get();
    }
    public List<Cadastro> findAll(){
        List<Cadastro> cadastros = cadastroRepository.findAll();
        Collections.reverse(cadastros);
        return cadastros;
    }

    public void save(Cadastro cadastro){
        cadastro.setNome(cadastro.getNome().toUpperCase());
        cadastroRepository.save(cadastro);
    }

    public void editar(Cadastro cadastro){
        cadastroRepository.save(cadastro);
    }

    public List<Cadastro> findByRg(String rg){
        //Limitando tamanho maximo da lista pra 10 cadastros.
        List<Cadastro> listCadastros = cadastroRepository.findByRg(rg);
        List<Cadastro> cadastros = listCadastros.stream().limit(10).collect(Collectors.toList());
        return cadastros;
    }

    public List<Cadastro> findByCpf(String cpf){
        return cadastroRepository.findByCpf(cpf);
    }

    public List<Cadastro> findByNome(String nome){
        //Limitando tamanho maximo da lista pra 10 cadastros.
        List<Cadastro> listCadastros = cadastroRepository.findByNome(nome);
        List<Cadastro> cadastros = listCadastros.stream().limit(10).collect(Collectors.toList());
        return cadastros;
    }

    public void excluirCadastro(Long id){
        cadastroRepository.deleteById(id);
    }
}
