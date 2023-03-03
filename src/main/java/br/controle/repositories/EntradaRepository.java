package br.controle.repositories;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    @Query("select entrada from Entrada entrada where entrada.data like %?1% and entrada.data like %?2%")
    List<Entrada> findByDatas(String dataInicial, String dataFinal);

}
