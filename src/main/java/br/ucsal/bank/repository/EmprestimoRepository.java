package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ucsal.bank.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {}