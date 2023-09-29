package CardTypes;

import Uno.*;

public class Reverse extends ActionCards{
    // Carta referente a carta Reverse, que permite o jogador mudar o sentido do jogo
    // Fazendo com que a rotação entre os jogadores seja "Anti-Horária"
    public Reverse(Color color, Value value) {
        super(color, value);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void ExecuteAction() {
        Game.setDirection();// Muda o sentido do jogo
    }
    
}
