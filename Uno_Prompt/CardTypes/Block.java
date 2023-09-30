package CardTypes;

import Uno.*;

public class Block extends ActionCards{
    // Classe referente a Carta Block (Bloqueio) que permite o jogador
    // Bloquear a ação do próximo jogador, ou pular ele
    public Block(Color color, Value value) {
        super(color, value);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void ExecuteAction() {
        Game.verifyPlayerCounter(); // Pula para o próximo jogador
    }
    
}
