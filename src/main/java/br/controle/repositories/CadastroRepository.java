package br.controle.repositories;

import br.controle.models.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    List<Cadastro> findByRg(String rg);
    List<Cadastro> findByCpf(String cpf);
    @Query("select cadastro from Cadastro cadastro where cadastro.nome like %?1%")
    List<Cadastro> findByNome(String nome);

}
