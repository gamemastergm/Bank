package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bank.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository  extends JpaRepository<ContaCorrente, Long> {

	
}
