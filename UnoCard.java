package Uno;

public class UnoCard {
    enum Color {
        Red, Blue, Green, Yellow, Wild;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }

    enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Draw_Two, Block, Reverse, Choose_Color, Draw_Four;
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
