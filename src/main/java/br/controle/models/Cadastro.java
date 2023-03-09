package br.controle.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Entity
public class
Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Rg não pode ser branco")
    private String rg;
    @NotBlank(message = "Nome não pode ser branco")
    private String nome;
//    @CPF(message = "CPF invalido")
    private String cpf;
    private String telefone;
    @NotNull
    @NotEmpty
    private String categoria;

    @OneToMany(mappedBy = "cadastro", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Entrada> entradas = new ArrayList<Entrada>();

    public Cadastro() {
    }

    public Cadastro(Long id, String rg, String nome, String cpf, String telefone, String categoria) {
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
}
