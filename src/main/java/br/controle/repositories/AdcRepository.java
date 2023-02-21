package br.controle.repositories;

import br.controle.models.AdcModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdcRepository extends JpaRepository<AdcModel, Long> {
}
