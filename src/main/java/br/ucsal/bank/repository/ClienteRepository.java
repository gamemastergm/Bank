package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bank.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	//List<Cliente> findByNome(String nome);
	Cliente findByNome(String nome);
	Cliente findByCpf(String cpf);
	Cliente findBySenhaCliente(String senha);
	
}
