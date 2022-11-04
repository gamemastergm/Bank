package br.ucsal.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ucsal.bank.helpers.Status;

@Entity
@Table(name="emprestimo")
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int numeroSenha;
	private double valor;
	private Status emprestimo;
	//private Conta conta;
	
	public Emprestimo(int numeroSenha, Double valor, Status emprestimo, Conta conta) {
		super();
		this.numeroSenha = numeroSenha;
		this.valor = valor;
		this.emprestimo = emprestimo;
		//this.conta = conta;
	}
	
	public int getNumeroSenha() {
		return numeroSenha;
	}
	public void setNumeroSenha(int numeroSenha) {
		this.numeroSenha = numeroSenha;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Status getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(Status emprestimo) {
		this.emprestimo = emprestimo;
	}
	/*public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}*/
	
}
