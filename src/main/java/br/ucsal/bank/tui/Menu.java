package br.ucsal.bank.tui;

import java.util.Date;

import java.util.Scanner;

import enums.TipoPessoa;
import javax.swing.JOptionPane;

public class Menu {
	public static void chamarMenu() {
		Date hoje = new Date();
		CadastroUsuario.cadastrarGerente();
		CadastroUsuario.cadastrarTeste();

		String opcao = JOptionPane.showInputDialog(hoje + "\nBem vindo ao banco" + "\n1 - Cadastrar novo cliente"
				+ "\n2 - Acessar sua conta" + "\n3 - Acesso especial" + "\n4 - Finalizar Sessao").toLowerCase();
		switch (opcao) {
		case "1":
		case "cadastrar novo cliente":
			CadastroUsuario.cadastrar();
			chamarMenu();
			break;
		case "2":
		case "acessar sua conta":
			AcessoConta.acessarConta(TipoPessoa.CLIENTE);
			break;
		case "3":
		case "acesso Especial":
			AcessoConta.acessarConta(TipoPessoa.GERENTE);
		case "4":
		case "finalizar Sessao":
			System.exit(0); 
			return;
		default:
			JOptionPane.showMessageDialog(null, "Opcao invalida,tente novamente");
			chamarMenu();
			break;

		}

	}
}
