package br.ucsal.bank.persistence;

import br.ucsal.bank.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class DadosContas {
	static List<Conta> contasBanco = new ArrayList<Conta>();

	public static void adicionarConta(Conta a) {
		contasBanco.add(a);
	}

	public static void removerConta(Conta r) {
		contasBanco.remove(r);
	}

	public static List<Conta> listarConta() {
		return contasBanco;
	}

}
