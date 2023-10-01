package Cards;

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
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), 
        SIX(6), SEVEN(7), EIGHT(8), NINE(9), 
        DRAW_TWO(20), BLOCK(20), REVERSE(20), 
        CHOOSE_COLOR(50), DRAW_FOUR(50);
        
        private int value;

        private int getPoint(){
            return this.value;
        }

        Value(int value){
            this.value = value;
        }

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

    public int getCardPoints(){
        return this.cardValue.getPoint();
    }

    // Override do método toString para que este retorne o objeto no formato "Cor_Valor" da carta
    public String toString(){
        return this.cardColor + "_" + this.cardValue;
    }
}
