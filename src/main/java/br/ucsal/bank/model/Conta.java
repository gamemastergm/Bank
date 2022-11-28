package br.ucsal.bank.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ucsal.bank.helpers.StatusConta;

@Entity
@Table(name="conta")
public class Conta extends Cliente {
	private String senhaConta;
	private String saldo = "0";
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> historico;
	private String dividas= "0";
	private StatusConta statusConta;
	
	public Conta() {
		super();
	}

	public Conta(Long id, String nome, String cpf, String senhaCliente, int idade, String sexo, String estadoCivil,
			String endereco, String telefone, String senhaConta, String saldo, List<String> historico, String dividas,
			StatusConta statusConta) {
		super(id, nome, cpf, senhaCliente, idade, sexo, estadoCivil, endereco, telefone);
		this.senhaConta = senhaConta;
		this.saldo = saldo;
		this.historico = historico;
		this.dividas = dividas;
		this.statusConta = statusConta;
	}

	public String getSenhaConta() {
		return senhaConta;
	}

	public void setSenhaConta(String senhaConta) {
		this.senhaConta = senhaConta;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public List<String> getHistorico() {
		return historico;
	}

	public void setHistorico(List<String> historico) {
		this.historico = historico;
	}

	public String getDividas() {
		return dividas;
	}

	public void setDividas(String dividas) {
		this.dividas = dividas;
	}

	public StatusConta getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(StatusConta statusConta) {
		this.statusConta = statusConta;
	}

	@Override
	public String toString() {
		return "Conta [senhaConta=" + senhaConta + ", saldo=" + saldo + ", historico=" + historico + ", dividas="
				+ dividas + ", statusConta=" + statusConta + "]";
	}
	
	
	
}
