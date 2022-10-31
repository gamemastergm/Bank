package br.ucsal.bank.tui;

import javax.swing.JOptionPane;

import business.FuncoesConta;
import business.FuncoesGerente;
import business.VerificadorDeAcesso;
import domain.Emprestimo;
import enums.StatusConta;
import enums.TipoPessoa;
import exceptions.ContaBloqueadaException;
import exceptions.ContaInexistenteException;
import exceptions.FilaVaziaException;

public class AcessoConta {
	static int contador = 0;

	// ACESSO DE CONTA GERENTE OU CLIENTE

	public static void acessarConta(TipoPessoa tipo) {
		JOptionPane.showMessageDialog(null, "Bem vindo a sua area de Acesso");

		switch (tipo) {
		case GERENTE:
			String cpf1 = JOptionPane.showInputDialog("Informe o seu  CPF");
			String chaveDeAcesso = JOptionPane.showInputDialog("Informe a sua CHAVE DE ACESSO");

			try {
				VerificadorDeAcesso.verificarAcesso(cpf1, chaveDeAcesso, tipo);
			} catch (ContaBloqueadaException e ) {

				JOptionPane.showMessageDialog(null, e.getMessage());
				Menu.chamarMenu();
			} catch (ContaInexistenteException e) {

				JOptionPane.showMessageDialog(null, "Essa conta nao existe");
				Menu.chamarMenu();
			}

			acessarFuncoesBancoGerente(tipo);
			break;
		case CLIENTE:
			String cpf2 = JOptionPane.showInputDialog("Informe o seu CPF ou CNPJ");
			String senha = JOptionPane.showInputDialog("Informe a sua SENHA: ");
			try {
				VerificadorDeAcesso.verificarAcesso(cpf2, senha, tipo);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				Menu.chamarMenu();
			}
			acessarFuncoesBancoCliente(tipo);
			break;
		}

	}

	// CONTA CLIENTE - FUNCOES BASICAS / CONTA CLIENTE - ACESSO DE GERENTE

	public static void acessarFuncoesBancoCliente(TipoPessoa tipo) {
		String escolha = "";
		switch (tipo) {
		case CLIENTE:
			if (contador == 0) {
				contador++;
				JOptionPane.showMessageDialog(null, "Bem vindo, " + FuncoesConta.informar() + " o que deseja fazer? ");
			}

			escolha = JOptionPane.showInputDialog("\n1 - Deposito" + "\n2 - Saques" + "\n3 - Transferencias"
					+ "\n4 - Saldo da Conta" + "\n5 - Historico da Conta" + "\n6 - Dividas da Conta"
					+ "\n7 - Pagar Dividas" + "\n8 - Solicitar Emprestimo" + "\n9 - Sair da Conta");
			break;
		case GERENTE:
			JOptionPane.showMessageDialog(null, "Bem vindo, " + FuncoesGerente.informar() + " o que deseja fazer? ");
			escolha = JOptionPane.showInputDialog("\n1 - Deposito" + "\n2 - Saques" + "\n3 - Transferencias"
					+ "\n4 - Saldo da Conta" + "\n5 - Historico da Conta" + "\n6 - Voltar Para o Menu Contas");

			break;
		}

		switch (escolha) {
		case "1":
			String depositoAux = JOptionPane.showInputDialog("Digite o valor do seu deposito");
			double deposito = Double.parseDouble(depositoAux);
			FuncoesConta.depositar(deposito);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "2":
			String saqueAux = JOptionPane.showInputDialog("Digite o valor do seu saque");
			double saque = Double.parseDouble(saqueAux);
			FuncoesConta.sacar(saque);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "3":
			String transferenciaAux = JOptionPane.showInputDialog("Digite o valor da transferencia");
			double transferencia = Double.parseDouble(transferenciaAux);
			String cpf = JOptionPane.showInputDialog("Digite o CPF ou CNPJ da conta desejada");
			FuncoesConta.transferir(transferencia, cpf);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "4":
			FuncoesConta.verSaldo();
			acessarFuncoesBancoCliente(tipo);
			break;
		case "5":
			FuncoesConta.verHistorico();
			acessarFuncoesBancoCliente(tipo);
			break;
		case "6":
			if (tipo == TipoPessoa.CLIENTE) {
				FuncoesConta.verDividas();
				acessarFuncoesBancoCliente(tipo);
			} else {
				acessarContas(tipo);
			}
			break;
		case "7":
			String dividaAux = JOptionPane.showInputDialog("Digite o valor do pagamento");
			double divida = Double.parseDouble(dividaAux);
			FuncoesConta.pagarDividas(divida);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "8":
			String emprestimoAux = JOptionPane.showInputDialog("Digite o valor desejado para o emprestimo");
			double emprestimo = Double.parseDouble(emprestimoAux);
			FuncoesConta.solicitarEmprestimo(emprestimo);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "9":
			if (tipo == TipoPessoa.CLIENTE) {
				contador = 0;
				Menu.chamarMenu();
			}
			break;
		}

	}

	// CONTA GERENTE - FUNCOES BASICAS

	public static void acessarFuncoesBancoGerente(TipoPessoa tipo) {
		if (contador == 0) {
			contador++;
			JOptionPane.showMessageDialog(null, "Bem vindo, " + FuncoesGerente.informar() + " o que deseja fazer? ");
		}
		String escolha = JOptionPane.showInputDialog("\n1 - Contas" + "\n2 - Emprestimos" + "\n3 - Sair da Conta");

		switch (escolha) {
		case "1":
			acessarContas(tipo);
			break;
		case "2":
			acessarEmprestimos(tipo);
			break;
		case "3":
			Menu.chamarMenu();
			break;
		}

	}

	// CONTA GERENTE - FUNCOES CONTAS

	public static void acessarContas(TipoPessoa tipo) {
		String escolha = JOptionPane.showInputDialog("\n1 - Listar Contas Clientes" + "\n2 - Listar Gerentes"
				+ "\n3 - Buscar Conta" + "\n4 - Remover Conta" + "\n5 - Bloquear Conta" + "\n6 - Desbloquear Conta"
				+ "\n7 - Acessar Conta" + "\n8 - Menu anterior");

		switch (escolha) {
		case "1":
			FuncoesGerente.listarContasClientes();
			acessarContas(tipo);
			break;
		case "2":
			FuncoesGerente.listarContasGerente();
			acessarContas(tipo);
			break;
		case "3":
			String cpf = JOptionPane.showInputDialog("Digite o cpf ou cnpj  da conta");
			JOptionPane.showMessageDialog(null, FuncoesGerente.buscarConta(cpf));
			acessarContas(tipo);
			break;
		case "4":
			String cpf2 = JOptionPane.showInputDialog("Digite o cpf ou cnpj  da conta");
			FuncoesGerente.removerConta(cpf2);
			acessarContas(tipo);
			break;
		case "5":
			String cpf3 = JOptionPane.showInputDialog("Digite o cpf ou cnpj  da conta");
			VerificadorDeAcesso.buscarContaCPF(cpf3);
			FuncoesConta.atualizarStatusConta(StatusConta.BLOQUEADA);
			acessarContas(tipo);
			break;
		case "6":
			String cpf4 = JOptionPane.showInputDialog("Digite o cpf ou cnpj  da conta");
			VerificadorDeAcesso.buscarContaCPF(cpf4);
			FuncoesConta.atualizarStatusConta(StatusConta.DESBLOQUEADA);
			acessarContas(tipo);
			break;
		case "7":
			String cpf5 = JOptionPane.showInputDialog("Digite o cpf ou cnpj  da conta");
			VerificadorDeAcesso.buscarContaCPF(cpf5);
			acessarFuncoesBancoCliente(tipo);
			break;
		case "8":
			AcessoConta.acessarFuncoesBancoGerente(tipo);
			break;
		}

	}

	// CONTA GERENTE - FUNCOES EMPRESTIMOS

	public static void acessarEmprestimos(TipoPessoa tipo) {
		String escolha = JOptionPane.showInputDialog(
				"\n1 - Listar Emprestimos Atualizados" + "\n2 - Buscar Emprestimos Atualizados da Conta"
						+ "\n3 - Gerenciar Emprestimos" + "\n4 - Menu anterior");
		switch (escolha) {
		case "1":
			FuncoesGerente.listarEmprestimos();
			acessarEmprestimos(tipo);
			break;

		case "2":
			String numeroSenhaAux = JOptionPane.showInputDialog("Informe o Numero Senha do Emprestimo desejado");
			int numeroSenha = Integer.parseInt(numeroSenhaAux);
			JOptionPane.showMessageDialog(null, FuncoesGerente.imprimirSolicitacaoEmpreste(numeroSenha));
			acessarEmprestimos(tipo);
			break;

		case "3":
			Emprestimo aux = null;
			try {
				aux = FuncoesGerente.iniciarGerenciaDeEmprestimo();
			} catch (FilaVaziaException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				acessarEmprestimos(tipo);
				break;
			}

			int escolha2 = JOptionPane.showConfirmDialog(null, aux + "\nPermitir Emprestimo?",
					"Confirma��o de Emprestimo", 0);
			switch (escolha2) {
			case 0:
				FuncoesGerente.aceitarEmprestimo();
				break;
			case 1:
				FuncoesGerente.negarEmprestimo();
				break;

			}
			acessarEmprestimos(tipo);
			break;

		case "4":
			AcessoConta.acessarFuncoesBancoGerente(tipo);
			break;
		}

	}
}
