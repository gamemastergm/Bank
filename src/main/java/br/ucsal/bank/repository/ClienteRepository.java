package br.ucsal.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ucsal.bank.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	//List<Cliente> findByNome(String nome);
	Cliente findByNome(String nome);
	Cliente findByCpf(String cpf);
	Cliente findBySenhaCliente(String senha);
	
}
