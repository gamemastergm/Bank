package br.ucsal.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ucsal.bank.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	
	//List<Cliente> findByNome(String nome);
	Conta findByNome(String nome);
	Conta findByCpf(String cpf);
	Conta findBySenhaCliente(String senha);
}
