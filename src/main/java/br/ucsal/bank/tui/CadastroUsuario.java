package br.ucsal.bank.tui;

import javax.swing.JOptionPane;

import business.VerificadorDeCadastro;
import business.VerificadoresDeExceptions;
import domain.Cliente;
import domain.ContaCorrente;
import domain.ContaPoupanca;
import domain.Gerente;
import domain.Pessoa;
import domain.PessoaJuridica;
import enums.Status;
import enums.StatusConta;
import enums.TipoPessoa;
import exceptions.ContaCadastradaJaExisteException;
import persistence.DadosContas;
import persistence.DadosUsuario;

public class CadastroUsuario {

	public static void cadastrar() {
		cadastrarCliente();
	}

	// ESCOLHA ENTRE PESSOA FISICA E JURIDICA

	public static void cadastrarCliente() {
		String escolha = JOptionPane.showInputDialog(
				"Escolha a opcao desejada:" + "\n1 - Cadastrar Pessoa Fisica" + "\n2 - Cadastrar Pessoa Juridica");

		switch (escolha) {
		case "1":
			try {
				pessoaF();
			} catch (ContaCadastradaJaExisteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				Menu.chamarMenu();
			}
			
			break;

		case "2":
			try {
				pessoaJ();
			} catch (ContaCadastradaJaExisteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				Menu.chamarMenu();
			}
			break;
		}

	}

	// CADASTRO DE PESSOA FISICA

	private static void pessoaF() throws ContaCadastradaJaExisteException {
		String nome = JOptionPane.showInputDialog("Insira o seu nome: ");

		boolean i;
		int idade = 0;
		do {
			String idade2 = JOptionPane.showInputDialog("Insira sua idade: ");
			try {
				VerificadorDeCadastro.verificarIdade(idade2);
				JOptionPane.showMessageDialog(null, "Idade valida");
				i = Status.VALIDO.verificarStatusBoolean();
				idade = Integer.parseInt(idade2);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				i = Status.INVALIDO.verificarStatusBoolean();
			}

		} while (!i);
		boolean t;
		String telefone;
		do {

			telefone = JOptionPane.showInputDialog("Insira o seu telefone com DDD (Ex: 7101234567)");
			try {
				VerificadorDeCadastro.verificarTelefone(telefone);
				JOptionPane.showMessageDialog(null, "Telefone valido");
				t = Status.VALIDO.verificarStatusBoolean();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				t = Status.INVALIDO.verificarStatusBoolean();
			}

		} while (!t);

		String endereco = JOptionPane.showInputDialog("Insira o seu endereco");
		String sexo = JOptionPane.showInputDialog("Insira o seu sexo");
		String estadoCivil = JOptionPane.showInputDialog("Insira o seu estado civil");

		boolean c;
		String cpf;
		do {

			cpf = JOptionPane.showInputDialog("Insira o seu CPF");
			try {
				VerificadorDeCadastro.receberDadosCpf(cpf);
				JOptionPane.showMessageDialog(null, "CPF valido");
				c = Status.VALIDO.verificarStatusBoolean();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				c = Status.INVALIDO.verificarStatusBoolean();
			}

		} while (!c);
		String senha = JOptionPane.showInputDialog("Insira a sua senha de acesso");
		Cliente dCliente = new Cliente(nome, idade, cpf, TipoPessoa.CLIENTE, telefone, endereco, sexo, estadoCivil,
				senha);
		
		VerificadoresDeExceptions.verificarExistenciaDeUsuarioParaCadastro(cpf, senha);
		
		DadosUsuario.adicionar(dCliente);
		cadastrarCorrenteEPoupanca(dCliente);
	}

	// CADASTRO DE PESSOA JURIDICA

	private static void pessoaJ() throws ContaCadastradaJaExisteException {
		String nome = JOptionPane.showInputDialog("Insira o seu nome: ");
		boolean i;
		int idade = 0;
		do {
			String idade2 = JOptionPane.showInputDialog("Insira sua idade: ");
			try {
				VerificadorDeCadastro.verificarIdade(idade2);
				JOptionPane.showMessageDialog(null, "Idade valida");
				i = Status.VALIDO.verificarStatusBoolean();
				idade = Integer.parseInt(idade2);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				i = Status.INVALIDO.verificarStatusBoolean();
			}

		} while (!i);
		boolean t;
		String telefone;
		do {

			telefone = JOptionPane.showInputDialog("Insira o seu telefone com DDD (Ex: 7101234567)");
			try {
				VerificadorDeCadastro.verificarTelefone(telefone);
				JOptionPane.showMessageDialog(null, "Telefone valido");
				t = Status.VALIDO.verificarStatusBoolean();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				t = Status.INVALIDO.verificarStatusBoolean();
			}
		} while (!t);

		String endereco = JOptionPane.showInputDialog("Insira o seu endereco");

		boolean c;
		String cnpj;
		do {
			cnpj = JOptionPane.showInputDialog("Insira o seu CNPJ");
			try {
				VerificadorDeCadastro.receberDadosCnpj(cnpj);
				JOptionPane.showMessageDialog(null, "CNPJ valido");
				c = Status.VALIDO.verificarStatusBoolean();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				c = Status.INVALIDO.verificarStatusBoolean();
			}
		} while (!c);

		String senha = JOptionPane.showInputDialog("Insira a sua senha de acesso");
		PessoaJuridica dCliente = new PessoaJuridica(nome, idade, cnpj, TipoPessoa.CLIENTE, senha, endereco);

		VerificadoresDeExceptions.verificarExistenciaDeUsuarioParaCadastro(cnpj, senha);

		DadosUsuario.adicionar(dCliente);
		cadastrarCorrenteEPoupanca(dCliente);
	}

	static int contador = 0, contador2 = 0;

	// CADASTRO DE GERENTE

	public static void cadastrarGerente() {
		if (contador == 0) {
			Gerente gerente1 = new Gerente("Ednaldo Pereira", 56, "72720991279", TipoPessoa.GERENTE, "eds");
			DadosUsuario.adicionar(gerente1);
			contador++;
		}

	}

	// CADASTRO PARA TESTES

	public static void cadastrarTeste() {
		if (contador2 == 0) {
			Cliente teste1 = new Cliente("teste1", 25, "48270416010", TipoPessoa.CLIENTE, "teste1", "teste1", "teste1",
					"teste1", "teste1");
			Cliente teste2 = new Cliente("teste2", 50, "09089314091", TipoPessoa.CLIENTE, "teste2", "teste2", "teste2",
					"teste2", "teste2");
			PessoaJuridica teste3 = new PessoaJuridica("teste Especial 1", 75, "16288507000122", TipoPessoa.CLIENTE, "teste2",
					"teste Especial 3");
			

			ContaCorrente dadoCorrente1 = new ContaCorrente(teste1, 0d, "", 0d, StatusConta.DESBLOQUEADA);
			ContaPoupanca dadoPoupanca2 = new ContaPoupanca(teste2, 0d, "", 0, StatusConta.DESBLOQUEADA);
			ContaPoupanca dadoPoupanca3 = new ContaPoupanca(teste3, 0d, "", 0, StatusConta.DESBLOQUEADA);
			
			DadosUsuario.adicionar(teste1);
			DadosUsuario.adicionar(teste2);
			DadosUsuario.adicionar(teste3);

			DadosContas.adicionarConta(dadoCorrente1);
			DadosContas.adicionarConta(dadoPoupanca2);
			DadosContas.adicionarConta(dadoPoupanca3);
			contador2++;
		}

	}

	// CADASTRO DE CONTA CORRENTE E/OU POUPANCA

	public static void cadastrarCorrenteEPoupanca(Pessoa dCliente) {
		String escolha = JOptionPane.showInputDialog(
				"Informe o tipo de conta que deseja cadastrar" + "\n1 - Conta Corrente" + "\n2 - Conta Poupanca");

		if (escolha.equals("1")) {
			ContaCorrente dadoCorrente = new ContaCorrente(dCliente, 0d, "", 0d, StatusConta.DESBLOQUEADA);
			DadosContas.adicionarConta(dadoCorrente);

		} else if (escolha.equals("2")) {
			ContaPoupanca dadoPoupanca = new ContaPoupanca(dCliente, 0d, "", 0, StatusConta.DESBLOQUEADA);
			DadosContas.adicionarConta(dadoPoupanca);

		} else {
			JOptionPane.showMessageDialog(null, "Opcao invalida, tente novamente");
			cadastrarCorrenteEPoupanca(dCliente);

		}

	}
}
