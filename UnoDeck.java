package Uno;

import java.util.ArrayList;
import java.util.Collections;

public class UnoDeck {
    private static ArrayList<UnoCard> deck = new ArrayList<UnoCard>();
    private static int cardsDeck;

    public static void reset(){
        UnoCard.Color[] colors = UnoCard.Color.values();
        cardsDeck = 0;

        for(int i = 0; i < colors.length - 1; i++){
            UnoCard.Color color = colors[i];

            deck.add(cardsDeck++, new UnoCard(color, UnoCard.Value.getValue(0)));

            for(int j = 1; j < 10; j++){
                deck.add(cardsDeck++, new UnoCard(color, UnoCard.Value.getValue(j)));
                deck.add(cardsDeck++, new UnoCard(color, UnoCard.Value.getValue(j)));
            }

            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.DRAW_TWO, UnoCard.Value.BLOCK, UnoCard.Value.REVERSE};
            for(UnoCard.Value value : values){
                deck.add(cardsDeck++, new UnoCard(color, value));
                deck.add(cardsDeck++, new UnoCard(color, value));
            }
        }

            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.CHOOSE_COLOR, UnoCard.Value.DRAW_FOUR};
            for(UnoCard.Value value : values){
                for(int i = 0; i < 4; i++){
                    deck.add(cardsDeck++, new UnoCard(UnoCard.Color.WILD, value));
                }
            }
        Collections.shuffle(deck);
    }

    public static UnoCard getCard(int i){
        return deck.get(i);
    }

    public static void removeCard(int i){
        deck.remove(i);
    }
    
    public static int getDeckSize() {
    	return deck.size();
    }
}
