package br.ucsal.bank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ucsal.bank.helpers.StatusConta;

@Entity
@Table(name="contacorrente")
public class ContaCorrente extends Conta {
	private String status;

	public ContaCorrente() {
		super();
	}
	
	public ContaCorrente(Long id, String nome, String cpf, String senhaCliente, int idade, String sexo,
			String estadoCivil, String endereco, String telefone, String senhaConta, String saldo,
			List<String> historico, String dividas, StatusConta statusConta, String status) {
		super(id, nome, cpf, senhaCliente, idade, sexo, estadoCivil, endereco, telefone, senhaConta, saldo, historico,
				dividas, statusConta);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ContaCorrente [status=" + status + "]";
	}
	
	
	
}