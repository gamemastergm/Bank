package business;

import java.util.ArrayList;
import java.util.List;

import domain.Emprestimo;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import exceptions.ContaCadastradaJaExisteException;
import exceptions.ContaInexistenteException;
import exceptions.FilaVaziaException;
import persistence.DadosUsuario;

public class VerificadoresDeExceptions {

	public static void verificarExistenciaDeUsuarioParaBusca(String acesso, String senha, List<Pessoa> teste)
			throws ContaInexistenteException {
		List<String> aux2 = new ArrayList<>();
		for (Pessoa pessoa : teste) {

			aux2.add(pessoa.getSenha());

			if (pessoa instanceof PessoaFisica) {
				aux2.add(((PessoaFisica) pessoa).getCpf());
			} else if (pessoa instanceof PessoaJuridica) {
				aux2.add(((PessoaJuridica) pessoa).getCnpj());
			}
		}

		if (!aux2.contains(acesso) || !aux2.contains(senha)) {
			throw new ContaInexistenteException("Essa conta não existe");
		}

	}

	public static void verificarExistenciaDeUsuarioParaCadastro(String acesso, String senha)
			throws ContaCadastradaJaExisteException {
		List<Pessoa> teste = DadosUsuario.listar();
		List<String> aux2 = new ArrayList<>();

		for (Pessoa pessoa : teste) {

			aux2.add(pessoa.getSenha());

			if (pessoa instanceof PessoaFisica) {
				aux2.add(((PessoaFisica) pessoa).getCpf());
			} else if (pessoa instanceof PessoaJuridica) {
				aux2.add(((PessoaJuridica) pessoa).getCnpj());
			}
		}

		if (aux2.contains(acesso) && (aux2.contains(senha))) {
			throw new ContaCadastradaJaExisteException("Essa conta não pode ser cadastrada pois já existe");
		}

	}
	
	
}
