import java.awt.event.KeyEvent;

public class Teclado {

    // Função responsável por receber o clique nos botões do
    public void chavePressionada(KeyEvent event, AcaoBotao acoesBotoes) {
            String caracteres="0987654321/*-+=";
            if(caracteres.contains(event.getKeyChar()+"")){
                acoesBotoes.pegaTeclaDigitada(event.getKeyChar());
                event.consume();
            }else{
                event.consume();
            }		
    }

    public void teclaEnterPressionada(KeyEvent event, AcaoBotao acoesBotoes){
        if(KeyEvent.VK_ENTER == event.getKeyCode()){
            acoesBotoes.pegaTeclaDigitada("=".charAt(0));
        }else if(KeyEvent.VK_BACK_SPACE == event.getKeyCode()){
            acoesBotoes.retirandoValores();
        }else{
            event.consume();
        }
    }

}
