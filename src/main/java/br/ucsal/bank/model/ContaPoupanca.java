package br.ucsal.bank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ucsal.bank.helpers.StatusConta;

@Entity
@Table(name="contaPoupanca")
public class ContaPoupanca extends ContaCorrente {
	
	private String limitadorSaque;

	public ContaPoupanca() {
		super();
	}
	
	public ContaPoupanca(Long id, String nome, String cpf, String senhaCliente, int idade, String sexo,
			String estadoCivil, String endereco, String telefone, String senhaConta, String saldo,
			List<String> historico, String dividas, StatusConta statusConta, String status, String limitadorSaque) {
		super(id, nome, cpf, senhaCliente, idade, sexo, estadoCivil, endereco, telefone, senhaConta, saldo, historico,
				dividas, statusConta, status);
		this.limitadorSaque = limitadorSaque;
	}

	public String getLimitadorSaque() {
		return limitadorSaque;
	}

	public void setLimitadorSaque(String limitadorSaque) {
		this.limitadorSaque = limitadorSaque;
	}

	@Override
	public String toString() {
		return "ContaPoupanca [limitadorSaque=" + limitadorSaque + "]";
	}

}
