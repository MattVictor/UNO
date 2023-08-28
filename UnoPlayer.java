package Uno;

import java.util.ArrayList;

public class UnoPlayer {
    private String name;
    private static ArrayList<UnoCard> playerDeck = new ArrayList<UnoCard>();

    public void setName(String newname){
        this.name = newname;
    }

    public String getName(){
        return this.name;
    }

    public UnoPlayer(String name){
        setName(name);
        for(int i = 0; i < 7; i++){
            playerDeck.add(i, UnoDeck.getCard());
            UnoDeck.removeCard();
        }
    }

    public static void printPlayer(){
        for(int i = 0; i < 7; i++){
            System.out.println(playerDeck.get(i));
        }
       
    }
}
