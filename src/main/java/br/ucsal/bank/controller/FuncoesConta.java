package business;

import java.util.Date;

import javax.swing.JOptionPane;

import domain.Conta;
import domain.ContaCorrente;
import domain.ContaPoupanca;
import domain.Emprestimo;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import enums.Acao;
import enums.Status;
import enums.StatusConta;

public class FuncoesConta {
	static Conta auxiliar;
	static Date hoje = new Date();

	// DEPOSITO

	public static void depositar(double valor) {
		Double aux = auxiliar.getSaldo();
		aux += valor;
		auxiliar.setSaldo(aux);
		adicionarHistorico(Acao.DEPOSITO, valor);
		JOptionPane.showMessageDialog(null, "Deposito concluido");

	}

	// SAQUE

	public static void sacar(double valor) {
		Double aux = auxiliar.getSaldo();

		if (auxiliar instanceof ContaPoupanca) {
			ContaPoupanca auxiliar2 = (ContaPoupanca) auxiliar;

			if (valor <= aux) {

				int contador = auxiliar2.getLimitadorSaque() + 1;

				if (contador <= 3) {
					auxiliar2.setLimitadorSaque(contador);
					aux -= valor;
					auxiliar.setSaldo(aux);
					adicionarHistorico(Acao.SAQUE, valor);
					JOptionPane.showMessageDialog(null, "Saque concluido");
				} else {
					JOptionPane.showMessageDialog(null, "Voce alcancou o limite de 3 saques diarios");
				}

			} else {
				JOptionPane.showMessageDialog(null, "O valor a ser sacado � maior que o saldo atual");
			}

		} else if (auxiliar instanceof ContaCorrente) {

			if (valor <= aux) {
				aux -= valor;
				auxiliar.setSaldo(aux);
				adicionarHistorico(Acao.SAQUE, valor);
				JOptionPane.showMessageDialog(null, "Saque concluido");
			} else if (aux >= 0) {
				Double novaDivida = (valor - aux);
				aux -= valor;
				novaDivida += auxiliar.getDividas();
				auxiliar.setDividas(novaDivida);
				auxiliar.setSaldo(aux);
				adicionarHistorico(Acao.SAQUE, valor);
				JOptionPane.showMessageDialog(null, "Saque concluido");
			} else {
				JOptionPane.showMessageDialog(null, "Seu saldo esta negativo, nao � possivel sacar");
			}

		}

	}

	// TRANFERENCIA

	public static void transferir(double valor, String cpf) {
		Double aux = auxiliar.getSaldo();
		String cpfPrincipal = "";

		if (auxiliar.getUsuarioConta() instanceof PessoaJuridica) {
			cpfPrincipal = (((PessoaJuridica) auxiliar.getUsuarioConta()).getCnpj());
		} else if (auxiliar.getUsuarioConta() instanceof PessoaFisica) {
			cpfPrincipal = (((PessoaFisica) auxiliar.getUsuarioConta()).getCpf());
		}

		if (valor <= aux) {

			Status auxi = VerificadorDeAcesso.buscarContaCPF(cpf);
			switch (auxi) {
			case VALIDO:
				aux = auxiliar.getSaldo();
				aux += valor;
				auxiliar.setSaldo(aux);
				adicionarHistorico(Acao.TRANSFERENCIA2, valor);
				VerificadorDeAcesso.buscarContaCPF(cpfPrincipal);
				aux = auxiliar.getSaldo();
				aux -= valor;
				auxiliar.setSaldo(aux);
				adicionarHistorico(Acao.TRANSFERENCIA, valor);
				JOptionPane.showMessageDialog(null, "Transferencia concluida");
				break;
			case INVALIDO:
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null, "O valor a ser sacado e maior que o saldo atual");
		}

	}

	// SOLICITACAO DE EMPRESTIMO

	public static void solicitarEmprestimo(double valor) {
		if (auxiliar instanceof ContaCorrente) {
			adicionarHistorico(Acao.EMPRESTIMO, valor);
			SolicitacaoEmprestimo.adicionarNaFilaDeEmprestimos(valor, auxiliar);
		} else if (auxiliar instanceof ContaPoupanca) {
			JOptionPane.showMessageDialog(null, "Nao e possivel efetuar esse comando");
		}
	}

	// RECEBIMENTO DA RESPOSTA EM RELACAO AO EMPRESTIMO SOLICITADO

	public static void receberEmprestimo(Emprestimo auxiliar2) {
		if (auxiliar2.getConta().getUsuarioConta() instanceof PessoaJuridica) {
			VerificadorDeAcesso.buscarContaCPF(((PessoaJuridica) auxiliar2.getConta().getUsuarioConta()).getCnpj());
		} else if (auxiliar2.getConta().getUsuarioConta() instanceof PessoaFisica) {
			VerificadorDeAcesso.buscarContaCPF(((PessoaFisica) auxiliar2.getConta().getUsuarioConta()).getCpf());
		}
		switch (auxiliar2.isEmprestimo()) {
		case INVALIDO:
			adicionarHistorico(Acao.EMPRESTIMO3, auxiliar2.getValor());
			break;
		case VALIDO:
			adicionarHistorico(Acao.EMPRESTIMO2, auxiliar2.getValor());
			Double aux = auxiliar.getSaldo();
			aux += auxiliar2.getValor();
			auxiliar.setSaldo(aux);
			Double aux2 = auxiliar.getDividas();
			aux2 += auxiliar2.getValor();
			auxiliar.setDividas(aux2);
			break;

		}

	}

	// BLOQUEIO OU DESBLOQUEIO DE CONTA

	public static void atualizarStatusConta(StatusConta statusConta) {
		switch (statusConta) {
		case BLOQUEADA:
			auxiliar.setStatusConta(StatusConta.BLOQUEADA);
			JOptionPane.showMessageDialog(null, "Esta conta esta bloqueada");
			break;
		case DESBLOQUEADA:
			auxiliar.setStatusConta(StatusConta.DESBLOQUEADA);
			JOptionPane.showMessageDialog(null, "Esta conta esta desbloqueada");
			break;

		}

	}

	// PAGAMENTO DE DIVIDAS

	public static void pagarDividas(double valor) {
		if (auxiliar instanceof ContaCorrente) {
			Double aux1 = auxiliar.getSaldo();
			if (valor <= aux1) {
				Double aux2 = auxiliar.getDividas();
				aux1 -= valor;
				aux2 -= valor;
				auxiliar.setSaldo(aux1);
				auxiliar.setDividas(aux2);
				adicionarHistorico(Acao.DIVIDA, valor);
				JOptionPane.showMessageDialog(null, "Pagamento Concluido");
			} else {
				JOptionPane.showMessageDialog(null, "nao foi possivel efetuar o pagamento, saldo insuficiente");
			}

		} else if (auxiliar instanceof ContaPoupanca) {
			JOptionPane.showMessageDialog(null, "Nao e possivel efetuar esse comando!");
		}
	}

	// IMPRIMIR SALDO

	public static void verSaldo() {
		if (auxiliar instanceof ContaCorrente) {
			JOptionPane.showMessageDialog(null, "Seu saldo atual e R$" + auxiliar.getSaldo());
		} else if (auxiliar instanceof ContaPoupanca) {
			Double saldo = auxiliar.getSaldo();
			JOptionPane.showMessageDialog(null, "Seu saldo atual e R$" + auxiliar.getSaldo());
			double rendimentoMensal = (0.11 * saldo) / 100;
			double rendimentoAnual = (1.4 * saldo) / 100;
			JOptionPane.showMessageDialog(null, "\nSeu rendimento mensal e de R$ :" + rendimentoMensal
					+ "\nSeu rendimento anual e de R$: " + rendimentoAnual);
		}

	}

	// IMPRIMIR DIVIDAS

	public static void verDividas() {
		if (auxiliar instanceof ContaCorrente) {
			JOptionPane.showMessageDialog(null, "Seu total de dividas e de R$" + auxiliar.getDividas());
		} else if (auxiliar instanceof ContaPoupanca) {
			JOptionPane.showMessageDialog(null, "Nao e possivel efetuar esse comando!");
		}

	}

	// IMPRIMIR HISTORICO DE TRANSACOES

	public static void verHistorico() {
		JOptionPane.showMessageDialog(null, "Seu historico de conta:\n" + auxiliar.getHistorico());
	}

	// ADICIONAR TRANSACAO AO HISTORICO DE TRANSACOES

	public static void adicionarHistorico(Acao acao, double valor) {
		hoje = new Date();
		String aux = auxiliar.getHistorico();
		switch (acao) {
		case DEPOSITO:
			aux += "Foram depositados R$" + valor + " em sua conta -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case SAQUE:
			aux += "Foram sacados R$" + valor + " de sua conta ----->  " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case TRANSFERENCIA:
			aux += "Foram transferidos R$" + valor + " de sua conta -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case TRANSFERENCIA2:
			aux += "Foram transferidos R$" + valor + " para sua conta -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case EMPRESTIMO:
			aux += "Foi solicitado um emprestimo de R$" + valor + " para o banco -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case EMPRESTIMO2:
			aux += "Foi aceito um emprestimo de R$" + valor + " pelo banco -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case EMPRESTIMO3:
			aux += "Foi negado um emprestimo de R$" + valor + " pelo banco -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;
		case DIVIDA:
			aux += "Foi pago um valor de R$" + valor + " de suas dividas -----> " + hoje + "\n";
			auxiliar.setHistorico(aux);
			break;

		}

	}

	// INICIALIZA A CONTA ABERTA

	public static void pegarConta(Conta conta) {
		auxiliar = conta;

	}

	// PEGA O NOME DO USUARIO DONO DA CONTA

	public static String informar() {

		return auxiliar.getUsuarioConta().getNome().toUpperCase();

	}

}
