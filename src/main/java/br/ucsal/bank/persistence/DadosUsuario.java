
package br.ucsal.bank.persistence;

import br.ucsal.bank.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class DadosUsuario {
	static List<Cliente> cadastroCliente = new ArrayList<Cliente>();

	public static void adicionar(Cliente a) {
		cadastroCliente.add(a);

	}

	public static void remover(Cliente r) {
		cadastroCliente.remove(r);
	}

	public static List<Cliente> listar() {
		return cadastroCliente;
	}

}
