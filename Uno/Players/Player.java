package Players;

import java.util.ArrayList;

import Cards.Card;
import Cards.Deck;
import Outro.Outro;

public abstract class Player {
    // Classe para definição dos players
    protected final String name;
    protected ArrayList <Card> hand;

    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
        for(int i = 0; i < 7; i++){ // Gerador da mão, 7 cartas no começo do jogo
            hand.add(i, Deck.getCard(0));
            Deck.removeCard(0);
        }
    }

    public String getName(){
        return this.name;
    }

    public void printPlayer(){
        //Printa cada carta da mão do jogador
        for(int i = 0; i < hand.size(); i++){
            System.out.println("\u001B[37m"+ (i+1) + " - " + Outro.printCard(hand.get(i)));
        }
    }

    public void drawCard(){
        // puxa uma carta para mão do jogador e remove a mesma do Deck
        hand.add(Deck.getCard(0));
        Deck.removeCard(0);
    }

    public int checkSize(){
        // retorna quantas cartas o jogador tem na mão, para fins de verificação do ganhador e de quanto ele tem que falar UNO
        return hand.size();
    }

    public Card getCard(int i){
        // Pega a carta da mão do jogador
        return hand.get(i);
    }

    public void putCard(int i){
        // remove a carta da mão do jogador
        hand.remove(i);
    }

    public abstract int ChooseCard();

    public abstract boolean sayUno();
}
