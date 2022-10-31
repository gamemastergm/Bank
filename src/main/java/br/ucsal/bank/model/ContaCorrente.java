package br.ucsal.bank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ucsal.bank.helpers.StatusConta;

@Entity
@Table(name="contaCorrente")
public class ContaCorrente extends Conta {

	public ContaCorrente(Long id, String nome, String cpf, String senhaCliente, int idade, String sexo,
			String estadoCivil, String endereco, String telefone, String senhaConta, double saldo,
			List<String> historico, double dividas, StatusConta statusConta, double limitadorSaque) {
		super(id, nome, cpf, senhaCliente, idade, sexo, estadoCivil, endereco, telefone, senhaConta, saldo, historico,
				dividas, statusConta);
	}
	
}