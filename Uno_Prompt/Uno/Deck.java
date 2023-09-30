package Uno;

import CardTypes.*;
import Uno.Card.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static int cardsDeck;

    // Gerador de todas as Cartas do Uno
    public static void reset(){
        Card.Color[] colors = Card.Color.values();
        cardsDeck = 0;

        for(int i = 0; i < colors.length - 1; i++){
            Card.Color color = colors[i];

            deck.add(cardsDeck++, new Card(color, Card.Value.getValue(0)));

            for(int j = 1; j < 10; j++){
                deck.add(cardsDeck++, new Card(color, Card.Value.getValue(j)));
                deck.add(cardsDeck++, new Card(color, Card.Value.getValue(j)));
            }

            Card.Value[] values = new Card.Value[]{Card.Value.DRAW_TWO, Card.Value.BLOCK, Card.Value.REVERSE};
            for(Card.Value value : values){
                switch(value){
                    case BLOCK:
                        deck.add(cardsDeck++, new Block(color, value));
                        deck.add(cardsDeck++, new Block(color, value));
                        break;
                    case DRAW_TWO:
                        deck.add(cardsDeck++, new Draw(color, value, 2));
                        deck.add(cardsDeck++, new Draw(color, value, 2));
                        break;
                    case REVERSE:
                        deck.add(cardsDeck++, new Reverse(color, value));
                        deck.add(cardsDeck++, new Reverse(color, value));
                        break;
                    default:
                        break;
                }
            }
        }

            Card.Value[] values = new Card.Value[]{Card.Value.CHOOSE_COLOR, Card.Value.DRAW_FOUR};
            for(Card.Value value : values){
                switch(value){
                    case CHOOSE_COLOR:
                        deck.add(cardsDeck++, new ChooseColor(Color.WILD, value));
                        deck.add(cardsDeck++, new ChooseColor(Color.WILD, value));
                        break;
                    case DRAW_FOUR:
                        deck.add(cardsDeck++, new Draw(Color.WILD, value, 4));
                        deck.add(cardsDeck++, new Draw(Color.WILD, value, 4));
                        break;
                    default:
                        break;
                }
            }
        Collections.shuffle(deck);
    }

    public static Card getCard(int i){
        return deck.get(i);
    }

    public static void removeCard(int i){
        deck.remove(i);
    }
    
    public static int getDeckSize() {
    	return deck.size();
    }
}