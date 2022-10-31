package br.ucsal.bank.persistence;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.bank.model.Emprestimo;

public class DadosEmprestimos {
	static List<Emprestimo> cadastroCliente = new ArrayList<Emprestimo>();

	public static void adicionar(Emprestimo a) {
		cadastroCliente.add(a);

	}

	public static void remover(Emprestimo r) {
		cadastroCliente.remove(r);
	}

	public static List<Emprestimo> listar() {
		return cadastroCliente;
	}

}
