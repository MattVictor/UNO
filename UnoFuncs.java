package Uno;

import Uno.UnoCard.Color;
import Uno.UnoCard.Value;

public class UnoFuncs {
    public static int checkCard(UnoCard cartaPilha, UnoCard cartaPlayer){
        if(cartaPilha.getColor() == null || cartaPilha.getValue() == null){
            return 1;
        }
        else if(cartaPilha.getColor() == Color.getColor(4) || cartaPlayer.getColor() == Color.getColor(4)){
            return 1;
        }
        else if(cartaPilha.getColor() == cartaPlayer.getColor())
            return 1;
        else if(cartaPilha.getValue() == cartaPlayer.getValue())
            return 1;
        else
            return 0;
    }

    public static void reverseCard(UnoCard cartaPlayer){
        if(cartaPlayer.getValue() == Value.getValue(12))
            UnoGame.setFluxo(-1);
    }

    public static void blockCard(UnoCard cartaPlayer){
        if(cartaPlayer.getValue() == Value.getValue(11))
            UnoGame.increaseI();
    }

    public static int draw(UnoCard cartaPlayer){
        if(cartaPlayer.getValue() == Value.getValue(10))
            return 2;
        else if(cartaPlayer.getValue() == Value.getValue(14))
            return 4;
            return 0;
    }
}
