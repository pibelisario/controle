package br.controle.repositories;

import br.controle.models.Entrada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

//
        Page<Entrada> findEntradasByDataBetween(Date dataInicial, Date dataFinal, Pageable pageable);

}
