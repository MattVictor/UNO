package CardTypes;

import Uno.Card;

public abstract class ActionCards extends Card{
    // Extensão para instaciar as cartas de ações como: Reverse, Block/Skip, Choose Color, Draw Two/Four
    public ActionCards(Color color, Value value) {
        super(color, value);
        //TODO Auto-generated constructor stub
    }
    
    // Ação definida como abstrata, será definida nas subclasses dependendo da carta
    public abstract void ExecuteAction();
}
