package CardTypes;

import java.util.Scanner;
import Outro.Outro;
import java.util.Random;
import Players.*;

import Uno.*;

public class ChooseColor extends ActionCards{
	// Classe referente a carta Choose Color que permite o jogador escolher
	// a cor da próxima carta que será lançada
    public ChooseColor(Color color, Value value) {
        super(color, value);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void ExecuteAction() {
		if(Game.getCurrentPlayer() instanceof Com){
			Random rand = new Random();
			Game.setCardColor(new Card(Color.getColor(rand.nextInt(4)), null));
		}else{
			String[] colors = {"\u001B[31m","\u001B[34m","\u001B[32m","\u001B[33m"}; // Cores definidas apenas para fins visuais
			int escolha; // Cor que o jogador vai escolher
			Scanner sc = new Scanner(System.in);
				
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
		}
    }
    
}
