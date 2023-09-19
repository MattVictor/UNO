package Uno;

import java.util.ArrayList;

public class UnoPlayer {
    private String name;
    private ArrayList<UnoCard> playerDeck = new ArrayList<UnoCard>();

    public void setName(String newname){
        this.name = newname;
    }

    public String getName(){
        return this.name;
    }

    public UnoCard getCard(int i){
        return playerDeck.get(i);
    }

    public UnoPlayer(String name){
        setName(name);
        for(int i = 0; i < 7; i++){
            playerDeck.add(i, UnoDeck.getCard(0));
            UnoDeck.removeCard(0);
        }
    }

    public void printPlayer(){
        for(int i = 0; i < playerDeck.size(); i++){
            System.out.println("\u001B[37m"+ i + " - " + Outro.printCard(playerDeck.get(i)));
        }
    }

    public void putCard(int i){
        playerDeck.remove(i);
    }

    public void drawCard(){
        playerDeck.add(UnoDeck.getCard(0));
        UnoDeck.removeCard(0);
    }

    public int checkSize(){
        return playerDeck.size();
    }
}
