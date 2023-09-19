package Uno;

public class UnoCard {
    enum Color {
        RED, BLUE, GREEN, YELLOW, WILD;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }

    enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, BLOCK, REVERSE, CHOOSE_COLOR, DRAW_FOUR;
        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }

    private final Color cardColor;
    private final Value cardValue;

    public UnoCard (final Color color, final Value value){
        this.cardColor = color;
        this.cardValue = value;
    }

    public Color getColor(){
        return this.cardColor;
    }

    public Value getValue(){
        return this.cardValue;
    }

    public String toString(){
        return this.cardColor + "_" + this.cardValue;
    }
}
