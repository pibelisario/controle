package br.controle.repositories;

import br.controle.models.Cadastro;
import br.controle.models.Entrada;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ElementTraversal;

import java.util.Date;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

//
        List<Entrada> findEntradasByDataBetween(Date dataInicial, Date dataFinal);

}
