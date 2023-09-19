package Uno;

import Uno.UnoCard.Color;
import Uno.UnoCard.Value;
import java.util.Scanner;

public class UnoRules {
    public static boolean checkCard(UnoCard cartaPilha, UnoCard cartaPlayer){
        if(cartaPilha.getColor() == Color.WILD || cartaPlayer.getColor() == Color.WILD)
            return true;
        else if(cartaPilha.getColor() == cartaPlayer.getColor())
            return true;
        else if(cartaPilha.getValue() == cartaPlayer.getValue())
            return true;
        else
            return false;
    }

    public static void reverseCard(UnoCard cartaPlayer){// Carta de reversão
        UnoGame.setDirection(-1);// Muda o sentido do jogo
    }

    public static void blockCard(UnoCard cartaPlayer){// Carta de bloqueio
        UnoGame.increaseI();// Aumenta o contador do jogo, pulando o próximo jogador
        if(UnoGame.getI() == UnoGame.getNumPlayers()){
            UnoGame.setI(0);
        }
        else if(UnoGame.getI() < 0){
            UnoGame.setI(UnoGame.getNumPlayers()-1);
        }
    }

    public static void draw(UnoCard cartaPlayer){// Cartas do tipo "Draw", puxar mais cartas
        int j;
        System.out.println(UnoGame.NextPlayer());
        if(cartaPlayer.getValue() == Value.DRAW_TWO){
            for(j = 0; j < 2; j++){
                UnoGame.getPlayer(UnoGame.NextPlayer()).drawCard();
            }
        }
        else if(cartaPlayer.getValue() == Value.DRAW_FOUR){
            for(j = 0; j < 4; j++){
                UnoGame.getPlayer(UnoGame.NextPlayer()).drawCard();
            }
        }
    }
    
    public static void chooseColor(UnoCard cartaPlayer) {// Carta de escolher a cor
        String[] colors = {"\u001B[31m","\u001B[34m","\u001B[32m","\u001B[33m"};
    	int escolha;
    	Scanner sc = new Scanner(System.in);
    		
        while(true) {
    		for(int i = 0; i < 4; i++) {
    			System.out.printf("\u001B[37m%d - %s%s\n", i, colors[i], Color.getColor(i));
    			}
    		System.out.println("\u001B[37mEscolha sua cor:");
			escolha = sc.nextInt();
				
            if(escolha <= 3 && escolha >= 0) {
				UnoGame.setCardColor(new UnoCard(Color.getColor(escolha), null));
				break;
    		}
    	}
    }

    public static void sayUNO(){

    }
}
