package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ucsal.bank.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
