package business;

import java.util.List;

import domain.Cliente;
import domain.Conta;
import domain.Gerente;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import enums.Status;
import enums.StatusConta;
import enums.TipoPessoa;
import exceptions.ContaBloqueadaException;
import exceptions.ContaInexistenteException;
import persistence.DadosContas;
import persistence.DadosUsuario;
import tui.Menu;

public class VerificadorDeAcesso {

	public static void verificarAcesso(String cpf, String chave, TipoPessoa tipo)
			throws ContaBloqueadaException, ContaInexistenteException {
		List<Pessoa> teste = DadosUsuario.listar();
		Pessoa aux = null;

		VerificadoresDeExceptions.verificarExistenciaDeUsuarioParaBusca(cpf, chave , teste);

		for (Pessoa pessoa : teste) {

			if (pessoa instanceof PessoaJuridica) {
				PessoaJuridica pj = (PessoaJuridica) pessoa;

				if (pj.getCnpj().equals(cpf) && pj.getSenha().equals(chave)) {
					aux = pessoa;
					
					Status.VALIDO.imprimirAcesso();
					buscarConta(pj);
					return;
				}

			} else if (pessoa instanceof PessoaFisica) {
				PessoaFisica pf = (PessoaFisica) pessoa;

				if (pf.getCpf().equals(cpf) && pf.getSenha().equals(chave) && pf.getStatus() == tipo) {
					aux = pessoa;
					
					switch (pessoa.getStatus()) {

					case CLIENTE:
						Status.VALIDO.imprimirAcesso();
						buscarConta(pf);
						break;

					case GERENTE:
						Status.VALIDO.imprimirAcesso();
						FuncoesGerente.pegarConta(pf);
						break;
					}
				}

			}
		}
		
		if(aux == null) {
			throw new ContaInexistenteException("Essa conta nao existe");
		}

	}

	public static void buscarConta(Pessoa pessoa) throws ContaBloqueadaException, ContaInexistenteException {
		if(pessoa == null) {
			throw new ContaInexistenteException("Essa conta nao existe");
		}
		
		
		List<Conta> teste = DadosContas.listarConta();
		for (Conta conta : teste) {
			if (conta.getUsuarioConta().equals(pessoa)) {

				if (conta.getStatusConta() == StatusConta.BLOQUEADA) {
					throw new ContaBloqueadaException("Está conta está Bloqueada");
				}

				FuncoesConta.pegarConta(conta);

			}

		}
	}

	public static Status buscarContaCPF(String cpf) {
		List<Conta> teste = DadosContas.listarConta();
		List<Pessoa> usuarios = DadosUsuario.listar();
		boolean aux = false;

		for (Conta conta : teste) {
			if (conta.getUsuarioConta() instanceof PessoaJuridica) {

				PessoaJuridica pj = (PessoaJuridica) conta.getUsuarioConta();
				if (pj.getCnpj().equals(cpf)) {
					FuncoesConta.pegarConta(conta);
					return Status.VALIDO;

				}

			} else if (conta.getUsuarioConta() instanceof Cliente) {
				Cliente cliente = (Cliente) conta.getUsuarioConta();
				if (cliente.getCpf().equals(cpf)) {
					FuncoesConta.pegarConta(conta);
					return Status.VALIDO;

				}
			}

		}

		if (!aux) {
			Status.INVALIDO.imprimirAcesso();
			return Status.INVALIDO;
		}
		return null;

	}

}
