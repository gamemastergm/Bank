package br.ucsal.bank.helpers;

import javax.swing.JOptionPane;

public enum Status {
	VALIDO, INVALIDO;

	public boolean verificarStatusBoolean() {
		switch (this) {
		case INVALIDO:
			return false;
		case VALIDO:
			return true;

		}
		return false;
	}

	public void imprimirAcesso() {
		switch (this) {
		case INVALIDO:
			JOptionPane.showMessageDialog(null, "Essa conta nao existe");
			break;
		case VALIDO:
			JOptionPane.showMessageDialog(null, "Acesso permitido\n");
			break;
		}
	}

}
