package CardTypes;

import Uno.*;

public class Draw extends ActionCards{
    // Classe referente as cartas de Draw (ou Puxar), tanto as cartas de
    // Draw Two (Puxa dois) quanto Draw Four (Puxa quatro) podem ser 
    // instanciadas nessa classe definindo apenas o número de cartas que
    // serão puxadas através de seu único atributo
    private int number;

    public Draw(Color color, Value value, int number) {
        super(color, value);
        this.number = number;
    }

    @Override
    public void ExecuteAction() {
        int j;
        for(j = 0; j < this.number; j++){ // Puxa quantas cartas estiverem definidas no atributo deste objeto
            Game.getPlayer(Game.NextPlayer()).drawCard();
        }
    }
}
