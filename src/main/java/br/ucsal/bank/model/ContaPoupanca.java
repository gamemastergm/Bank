package br.ucsal.bank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.ucsal.bank.helpers.StatusConta;

@Entity
@Table(name="contaPoupanca")
public class ContaPoupanca extends ContaCorrente {
	
	private String ativa;
	private String limitadorSaque;
	private String reserva = "0";

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(Long id, String nome, String cpf, String senhaCliente, int idade, String sexo,
			String estadoCivil, String endereco, String telefone, String senhaConta, String saldo,
			List<String> historico, String dividas, StatusConta statusConta, String status, String limitadorSaque, String reserva, String ativa) {
		super(id, nome, cpf, senhaCliente, idade, sexo, estadoCivil, endereco, telefone, senhaConta, saldo, historico, dividas,
				statusConta, status);
		this.limitadorSaque = limitadorSaque;
		this.reserva = reserva;
		this.ativa = ativa;
	}
	
	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}

	public String getLimitadorSaque() {
		return limitadorSaque;
	}

	public void setLimitadorSaque(String limitadorSaque) {
		this.limitadorSaque = limitadorSaque;
	}

	public String getReserva() {
		return reserva;
	}

	public void setReserva(String reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "ContaPoupanca [ativa=" + ativa + ", limitadorSaque=" + limitadorSaque + ", reserva=" + reserva + "]";
	}

}
