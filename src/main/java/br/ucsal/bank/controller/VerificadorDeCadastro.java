package business;

import java.util.Arrays;
import java.util.List;

public class VerificadorDeCadastro {

	// verificador de CNPJ

	public static void receberDadosCnpj(String userCnpj) throws Exception {
		int[] cnpj = new int[14];
		int cnpjInvalido = userCnpj.length();
		if (cnpjInvalido != 14) {
			throw new Exception("CNPJ Invalido");
		} else {
			for (int i = 0; i < 14; ++i) {
				int b = i + 1;
				String posicaocnpj = userCnpj.substring(i, b);
				int posCnpjInt = Integer.parseInt(posicaocnpj);
				cnpj[i] = posCnpjInt;
			}
			verificarCNPJ(cnpj);
		}

	}

	public static void verificarCNPJ(int[] cnpj) throws Exception {
		int digitoVerificador1 = 0;
		int soma = (cnpj[0] * 5) + (cnpj[1] * 4) + (cnpj[2] * 3) + (cnpj[3] * 2) + (cnpj[4] * 9) + (cnpj[5] * 8)
				+ (cnpj[6] * 7) + (cnpj[7] * 6) + (cnpj[8] * 5) + (cnpj[9] * 4) + (cnpj[10] * 3) + (cnpj[11] * 2);
		int resto = 11 - (soma % 11);
		if (resto >= 10) {
			digitoVerificador1 = 0;
		} else {
			digitoVerificador1 = resto;
		}
		int digitoVerificador2 = 0;
		int soma2 = (cnpj[0] * 6) + (cnpj[1] * 5) + (cnpj[2] * 4) + (cnpj[3] * 3) + (cnpj[4] * 2) + (cnpj[5] * 9)
				+ (cnpj[6] * 8) + (cnpj[7] * 7) + (cnpj[8] * 6) + (cnpj[9] * 5) + (cnpj[10] * 4) + (cnpj[11] * 3)
				+ (cnpj[12] * 2);
		int resto2 = 11 - (soma2 % 11);
		if (resto2 >= 10) {
			digitoVerificador2 = 0;
		} else {
			digitoVerificador2 = resto2;
		}
		if (digitoVerificador1 != cnpj[12] || digitoVerificador2 != cnpj[13]) {
			throw new Exception("CNPJ Invalido");
		}

	}

	// Verificador de CPF

	public static void receberDadosCpf(String userCpf) throws Exception {
		int[] cpf = new int[11];
		int cpfInvalido = userCpf.length();
		if (cpfInvalido != 11) {
			throw new Exception("CPF Invalido");
		}

		for (int i = 0; i < 11; ++i) {
			int b = i + 1;
			String posicaoCPF = userCpf.substring(i, b);
			int posCpfInt = Integer.parseInt(posicaoCPF);

			cpf[i] = posCpfInt;
		}

		verificarCPF(cpf);

	}

	public static void verificarCPF(int[] cpf) throws Exception {
		int digitoVerificador1 = 0;
		int soma = (cpf[0] * 10) + (cpf[1] * 9) + (cpf[2] * 8) + (cpf[3] * 7) + (cpf[4] * 6) + (cpf[5] * 5)
				+ (cpf[6] * 4) + (cpf[7] * 3) + (cpf[8] * 2);
		int resto = 11 - (soma % 11);
		if (resto >= 10) {
			digitoVerificador1 = 0;
		} else {
			digitoVerificador1 = resto;
		}

		int digitoVerificador2 = 0;
		int soma2 = (cpf[0] * 11) + (cpf[1] * 10) + (cpf[2] * 9) + (cpf[3] * 8) + (cpf[4] * 7) + (cpf[5] * 6)
				+ (cpf[6] * 5) + (cpf[7] * 4) + (cpf[8] * 3) + (cpf[9] * 2);
		int resto2 = 11 - (soma2 % 11);
		if (resto2 >= 10) {
			digitoVerificador2 = 0;
		} else {
			digitoVerificador2 = resto2;
		}
		if (digitoVerificador1 != cpf[9] || digitoVerificador2 != cpf[10]) {
			throw new Exception("CPF Invalido");

		}

	}

//Verificador de Idade
//Primeiro vai checar se exitem letras no input do usuario
//Se nenhuma exception for encontrada ele vai verificar se o usuario tem idade para poder se cadastrar

	public static void verificarIdade(String idade) throws Exception {
		int aux;
		List<String> idadeValida = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

		for (int i = 0; i < idade.length(); ++i) {
			int b = i + 1;
			String posicaoIdade = idade.substring(i, b);
			if (!idadeValida.contains(posicaoIdade)) {
				throw new Exception("Insira uma idade valida, letras nao sao permitidas");
			}
		}

		aux = Integer.parseInt(idade);
		if (aux < 18) {
			throw new Exception("Idade incorreta, cadastro permitido apenas para maiores de 18 anos");

		}
	}

	//Verificador de Telefone
	//Primeiro vai checar se o numero de caracteres bate com o tamanho real de um telefone
	//Se o tamanho bater ele vai verificar se existem letras no input do usuario
	
	public static void verificarTelefone(String telefone) throws Exception {
		if (telefone.length() != 11) {
			throw new Exception("Informe um numero de telefone valido");
		}

		List<String> telefoneValido = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

		for (int i = 0; i < telefone.length(); ++i) {
			int b = i + 1;
			String posicaoTelefone = telefone.substring(i, b);
			if (!telefoneValido.contains(posicaoTelefone)) {
				throw new Exception("Insira um telefone valido, letras nao sao permitidas");
			}
		}
	}

}
