package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bank.model.ContaPoupanca;

@Repository
public interface ContaPoupancaRepository  extends JpaRepository<ContaPoupanca, Long> {

	
}
