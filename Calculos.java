import java.util.ArrayList;

public class Calculos {

	private int Sinais = 0;
	private String[] valores;
	private int[] NumerosEscritos = { 0, 0 };
	private int QuantidadeNumeros = 0;
	private int Resultado = 0;
	private String sinal = null;
	private String ContaPrimaria = "";
	private boolean Ultimo = false;

	// Função responsável por calcular com os botões selecionados
	public String ContaMatematica(String Conta, ArrayList<Integer> Prioridade) {

		valores = Conta.split(" ", 100); // Divide a string da conta em diversas strings
		contaMultDiv(Prioridade);

		if (Ultimo == false)
			ContaPrimaria += NumerosEscritos[1];
		else if (Ultimo == true)
			ContaPrimaria += NumerosEscritos[0];

		Conta = Integer.toString(Resultado);

		valores = ContaPrimaria.split(" ", 100); // Divide a string da conta em diversas strings
		QuantidadeNumeros = 0;

		contaSomaSubt();

		ContaPrimaria = Integer.toString(Resultado);
		Conta = ContaPrimaria;
		ContaPrimaria = "";
		return Conta;

	}

	// Função responsável por realizar as ações necessárias para fazer a conta de
	// soma ou subtração
	private void contaSomaSubt() {
		for (int i = 0; i < valores.length; i++) {
			if ((valores[i].matches("^[0-9]*$")) == true) {
				if (QuantidadeNumeros == 0) {
					NumerosEscritos[0] = Integer.parseInt(valores[i]);
					QuantidadeNumeros++;
				} else {
					NumerosEscritos[1] = Integer.parseInt(valores[i]);
					QuantidadeNumeros = 1;
					Resultado = CalculoBasico(sinal, NumerosEscritos[0], NumerosEscritos[1]);
					NumerosEscritos[0] = Resultado;
				}

			} else {
				sinal = valores[i];
			}
		}

		QuantidadeNumeros = 0;
	}

	// Função responsável por realizar as ações necessárias para fazer a conta de
	// divisão ou multiplicação
	private void contaMultDiv(ArrayList<Integer> Prioridade) {
		for (int i = 0; i < valores.length; i++) {
			if ((valores[i].matches("^[0-9]*$")) == true) {

				if (QuantidadeNumeros == 0) {
					NumerosEscritos[0] = Integer.parseInt(valores[i]);
					QuantidadeNumeros++;
				} else {
					NumerosEscritos[1] = Integer.parseInt(valores[i]);
					QuantidadeNumeros = 1;

					if (sinal != "") {
						Resultado = CalculoAvancado(sinal, NumerosEscritos[0], NumerosEscritos[1]);
						NumerosEscritos[0] = Resultado;
						Ultimo = true;
					} else {
						Ultimo = false;
						NumerosEscritos[0] = NumerosEscritos[1];
					}
				}

			} else {
				if (Prioridade.get(Sinais) == 1) {
					sinal = valores[i];
				} else {
					ContaPrimaria += NumerosEscritos[0];
					ContaPrimaria += " " + valores[i] + " ";
					sinal = "";
				}
				Sinais++;
			}
		}

		QuantidadeNumeros = 0;

	}

	// Função responsável por fazer a soma ou a subtração
	private int CalculoBasico(String sinal, int Valor1, int Valor2) {
		switch (sinal) {
			case "+":
				return (Valor1 + Valor2);
			case "-":
				return (Valor1 - Valor2);
		}

		return 0;

	}

	// Função responsável por fazer a divisão ou a multiplicação
	private int CalculoAvancado(String sinal, int Valor1, int Valor2) {
		switch (sinal) {
			case "*":
				return Valor1 * Valor2;
			case "/":
				if (Valor2 != 0) {
					return Valor1 / Valor2;
				} else {
					return 0;
				}
		}

		return 5;

	}
}
