import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculadora extends JFrame{
	private static final long serialVersionUID = 6399995383040270390L;
	private AcaoBotao acoesBotoes;
	private ActionListener clicar;
	private JTextField CaixaDialogo;
	private JButton [] botaos;
	private String teclas [] = {"7","8","9","/","4","5","6","*","1","2","3","-",
	"0"," ","=","+"};

	public Calculadora() {

		//Define e cria o campo no qual vai aparecer os botões digitados e o resultado das contas
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		CaixaDialogo = new JTextField();
		CaixaDialogo.setEditable(false);
		CaixaDialogo.setPreferredSize(new Dimension(320,40));
		CaixaDialogo.setBackground(Color.WHITE);
		CaixaDialogo.setFont(new Font("Times new Roman", Font.BOLD, 40));
		this.add(CaixaDialogo);

		
		botaos = new JButton[teclas.length]; //Cria um array com todos os botões

		eventoBotao();
		criaBotoes();
	}
	

	// Função responsável por criar o evento de click em um botão
	private void eventoBotao(){
		acoesBotoes = new AcaoBotao(teclas, botaos,CaixaDialogo);

		clicar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				acoesBotoes.acaobotoes(event);
			}
		};

	}

	//Função responsável por criar os botões que aparecem na tela
	private void criaBotoes(){
		for(int i=0;i<16;i++) {
			botaos[i] = new JButton(teclas[i]);
			botaos[i].setPreferredSize(new Dimension(75, 51));
			botaos[i].addActionListener(clicar); //Cria o evento de click dos botões
			this.add(botaos[i]);
		}
	}

}
