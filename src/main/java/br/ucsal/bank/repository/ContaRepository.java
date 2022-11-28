package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bank.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	
	//List<Cliente> findByNome(String nome);
	Conta findByNome(String nome);
	Conta findByCpf(String cpf);
	Conta findBySenhaCliente(String senha);
}
