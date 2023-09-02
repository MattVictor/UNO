package Uno;

import java.util.ArrayList;
import java.util.Collections;

public class UnoDeck {
    private static ArrayList<UnoCard> deck = new ArrayList<UnoCard>();
    // static ArrayList<UnoCard> deck;
    // //Arrays.asList(arrayDeck);
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

            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.Draw_Two, UnoCard.Value.Block, UnoCard.Value.Reverse};
            for(UnoCard.Value value : values){
                deck.add(cardsDeck++, new UnoCard(color, value));
                deck.add(cardsDeck++, new UnoCard(color, value));
            }
        }

            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.Choose_Color, UnoCard.Value.Draw_Four};
            for(UnoCard.Value value : values){
                for(int i = 0; i < 4; i++){
                    deck.add(cardsDeck++, new UnoCard(UnoCard.Color.Wild, value));
                }
            }
        Collections.shuffle(deck);
    }

    public static void PrintCard(){
        UnoDeck.reset();
        for(int i = 0; i < 108; i++){
            System.out.println(deck.get(i).toString());
        }
    }

    public static UnoCard getCard(){
        return deck.get(0);
    }

    public static void removeCard(){
        deck.remove(0);
    }
}
