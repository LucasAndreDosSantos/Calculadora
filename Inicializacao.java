import javax.swing.JFrame;

public class Inicializacao extends JFrame {
    public static void main(String[] args) { //Função main responsável por inicializar a tela da calculadora
		Calculadora janela = new Calculadora();
		janela.setTitle("Calculadora");
		janela.setSize(350, 320);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
        janela.setResizable(false);

	}
}
