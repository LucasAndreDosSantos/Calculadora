import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

public class AcaoBotao {

    private String ContaProvisoria = "", NumeroProvisorio = "";
    private boolean FezConta = false, AdicinouSinal = false, BotouNumero = false;
    private int TeclaClicada;
    private String Conta = "";
    private String teclas[];
    private JButton[] botaos;
    private ArrayList<Integer> Prioridade = new ArrayList<Integer>();
    private Calculos calculos;
    private JTextField caixaDialogo;

    public AcaoBotao(String teclas[], JButton[] botaos, JTextField CaixaDialogo) {
        this.teclas = teclas;
        this.botaos = botaos;
        this.caixaDialogo = CaixaDialogo;
        calculos = new Calculos();
    }

    public void pegaTeclaClicada(ActionEvent e) {
        for (int i = 0; i < teclas.length; i++) {
            if (e.getSource() == botaos[i]) {
                TeclaClicada = i; // Define qual tecla foi clicada
            }
        }

        acaoBotoes();

    }

    public void pegaTeclaDigitada(char ValorDigitado) {
        for (int i = 0; i < teclas.length; i++) {
            if (ValorDigitado == teclas[i].charAt(0)) {
                TeclaClicada = i; // Define qual tecla foi clicada
            }
        }

        acaoBotoes();
    }

    // Define as ações do click em cada botão
    private void acaoBotoes() {

        // Verifica se foi clicado em um número, no igual, no limpar ou em um sinal
        if (((teclas[TeclaClicada].matches("^[0-9]*$")) == true)) {

            digitouNumero();

        } else if (teclas[TeclaClicada] == "=") {

            digitouIgual();

        } else if (teclas[TeclaClicada] == " ") {

            Conta = "";

        } else {

            digitouSinal();

        }

        // Coloca o cálculo até o momento na tela
        caixaDialogo.setText(Conta);
    }

    // Função responsável por adicionar o número na conta e fazer as ações
    // necessárias
    private void digitouNumero() {
        if (FezConta == true) {
            FezConta = false;
            Conta = "";
        }

        Conta += teclas[TeclaClicada];

        if (AdicinouSinal == true) {
            AdicinouSinal = false;
        }

        BotouNumero = true;
    }

    // Função responsável por chamar a classe que vai fazer o cálculo
    private void digitouIgual() {
        if (AdicinouSinal == true) {
            Conta += NumeroProvisorio;
            AdicinouSinal = false;
        }

        Conta = calculos.ContaMatematica(Conta, Prioridade);
        FezConta = true;

        if (Integer.parseInt(Conta) < 0) {
            Prioridade.add(0);
            Conta = "0 " + Conta.substring(0, 1) + " " + Conta.substring(1, Conta.length());
        }
    }

    // Função responsável por adicionar o sinal na conta e fazer as ações
    // necessárias de prioridade
    private void digitouSinal() {
        if (FezConta == true) {
            FezConta = false;
        }

        if (Conta == "") {
            Conta += "0";
        } else if (BotouNumero == false) {
            Conta = ContaProvisoria;

            if (teclas[TeclaClicada] == "+") {
                Prioridade.remove(Prioridade.size() - 1);
            } else if (teclas[TeclaClicada] == "-") {
                Prioridade.remove(Prioridade.size() - 1);
            } else {
                Prioridade.remove(Prioridade.size() - 1);
            }
        }
        ContaProvisoria = Conta;
        NumeroProvisorio = Conta;
        Conta += " " + teclas[TeclaClicada] + " ";
        AdicinouSinal = true;
        BotouNumero = false;

        if (teclas[TeclaClicada] == "+") {
            Prioridade.add(0);
        } else if (teclas[TeclaClicada] == "-") {
            Prioridade.add(0);
        } else {
            Prioridade.add(1);
        }
    }

    public void retirandoValores() {
        if (Conta.length() > 1) {
            if ("+-*/".contains(Conta.substring(Conta.length() - 2, Conta.length() - 1))) {
                Conta = Conta.substring(0, Conta.length() - 3);
            } else {
                Conta = Conta.substring(0, Conta.length() - 1);
            }
        }else {
            Conta = "";
        }

        caixaDialogo.setText(Conta);
    }

}
