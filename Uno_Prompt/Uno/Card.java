package Uno;

public class Card {
    // Super classe de todas as cartas

    // Enum para criar as cores
    public enum Color {
        RED, BLUE, GREEN, YELLOW, WILD;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }

    // Enum para criar os Valores
    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, BLOCK, REVERSE, CHOOSE_COLOR, DRAW_FOUR;
        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }

    // Atributos de Cor e valor
    // Final pois estes não podem ser mudados
    private final Color cardColor;
    private final Value cardValue;

    public Card (final Color color, final Value value){
        this.cardColor = color;
        this.cardValue = value;
    }

    public Color getColor(){
        return this.cardColor;
    }

    public Value getValue(){
        return this.cardValue;
    }

    // Override do método toString para que este retorne o objeto no formato "Cor_Valor" da carta
    public String toString(){
        return this.cardColor + "_" + this.cardValue;
    }
}
