package CardTypes;

import java.util.Random;
import Outro.Outro;
import Players.*;
import Uno.Card.Color;

import Uno.*;

public class ChooseColor extends ActionCards{
	// Classe referente a carta Choose Color que permite o jogador escolher
	// a cor da próxima carta que será lançada
    public ChooseColor(Color color, Value value) {
        super(color, value);
    }

    @Override
    public void ExecuteAction() {
		// Faz com que o jogador atual escolha a cor que vai ser jogada pelo próximo jogador
		if(Game.getCurrentPlayer() instanceof Com){ // O Com precisa de um estado especial para esta carta pois ele não pode realmente escolher
			Random rand = new Random();
			Color color;
			if(Deck.getDeckSize() != 0){
				// Se ainda existirem cartas no deck, ele define uma carta aleatória do deck como a carta a ser jogada
				color = Deck.getCard(rand.nextInt(Deck.getDeckSize())).getColor();
				Game.setCardColor(new Card(color, null));
			}
			else{
				// Se não, ele vai pegar a cor de uma carta aleatória do jogador rival e definir como a cor atual 
				color = Game.getPlayer(0).getCard(rand.nextInt(Game.getPlayer(0).checkSize())).getColor();
				Game.setCardColor(new Card(color, null));
			}
			// Com as duas condições mencionadas acima o jogo nunca fecha, na parte do Com
		}else{
			String[] colors = {"\u001B[31m","\u001B[34m","\u001B[32m","\u001B[33m"}; // Cores definidas apenas para fins visuais
			int escolha; // Cor que o jogador vai escolher
				
			while(true) {
				for(int i = 0; i < 4; i++) {
					System.out.printf("\u001B[37m%d - %s%s\n", (i+1), colors[i], Color.getColor(i));
					}
				System.out.println("\u001B[37mEscolha sua cor:");
				escolha = Outro.getNumEntry();
				escolha--; // Ajustes para que a cor seja corretamente chamada pelo index
				if(escolha <= 3 && escolha >= 0) { 
					// se a cor escolhida estiver entre as cores possíveis define ela como a carta atual, e o seu valor como nulo
					Game.setCardColor(new Card(Color.getColor(escolha), null));
					break;
				}
			}
			// Como o jogador é livre para escolher qualquer cor, ele pode muito bem fechar o jogo, seja por querer ou não
			// Diferente do Com que sempre vai escolher uma carta válida
			Game.CloseGame();
		}
    }
}
