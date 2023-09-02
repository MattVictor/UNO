package Uno;

import Uno.UnoCard.Color;

public class UnoFucs {
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
}
