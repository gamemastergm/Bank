package business;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import domain.Conta;
import domain.Emprestimo;
import enums.Status;
import exceptions.FilaVaziaException;
import persistence.DadosEmprestimos;

public class SolicitacaoEmprestimo {
	static Emprestimo auxiliar;
	static int numeroSenha;
	static Queue<Emprestimo> fila = new LinkedList<>();

	// ARQUIVA EMPRESTIMOS ATUALIZADOS

	public static void arquivarEmprestimo(Emprestimo emprestimo) {
		DadosEmprestimos.adicionar(emprestimo);
	}

	// ADICIONA EMPRESTIMOS EM UMA FILA

	public static void adicionarNaFilaDeEmprestimos(Double valor, Conta conta) {
		numeroSenha++;
		Emprestimo emprestimo = new Emprestimo(valor, Status.INVALIDO, conta, numeroSenha);
		fila.add(emprestimo);

	}

	// BUSCA EMPRSTIMO ESPECIFICO DENTRO DOS EMPRESTIMOS ATUALIZADOS

	public static Emprestimo buscarEmprestimo(int numeroSenha) {
		List<Emprestimo> teste = DadosEmprestimos.listar();
		for (Emprestimo emprestimo : teste) {
			if (emprestimo.getNumeroSenha() == numeroSenha) {
				return emprestimo;

			}
		}
		return null;
	}

	// INICIALIZA O PROCEDIMENTO DE GERENCIA DE EMPRESTIMO

	public static void gerenciarEmprestimo() throws FilaVaziaException {
		auxiliar = fila.peek();

		if (auxiliar == null) {
			throw new FilaVaziaException("A fila de emprestimos está vazia");
		}
		
		fila.remove();

		arquivarEmprestimo(auxiliar);

	}

	public static Emprestimo getEmprestimoAtual() {
		return auxiliar;
	}

	// ATUALIZA O EMPRESTIMO PARA A CONTA CLIENTE

	public static void validarEmprestimo(Status emprestimo) {
		auxiliar.setEmprestimo(emprestimo);
		FuncoesConta.receberEmprestimo(auxiliar);

	}

}
