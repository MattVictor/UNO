package Players;

import Uno.Game;
import java.util.Random;

public class Com extends Player{
    // Pseudo-AI para jogar contra
    // "Pseudo" pois não possui realmente inteligencia, é apenas uma sequência de ações programadas
    public Com(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int ChooseCard() {
        // Verifica se existe alguma carta no deck que possa ser jogada
        for(int i = 0; i < hand.size(); i++){
            if(Game.verifyValidCard(hand.get(i))){
                return i; // se existir, retorna o index desta carta
            }
        }
        return -2; // se não, puxa uma carta
    }

    @Override
    public boolean sayUno() {
        // Método dizer uno no caso do computador é baseado em chance
        // 80% de chance de retornar true
        Random rand = new Random();
        int chance = rand.nextInt(5);
        if(chance < 4){
            return true;
        }
        return false;
    }
    
}
