package Players;

import Outro.Outro;
import java.util.Scanner;

public class RealPlayer extends Player{

    public RealPlayer(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int ChooseCard() {
        // Método usado para o jogador escolher sua carta
        int num = Outro.getNumEntry();
        return num;
    }

    @Override
    public boolean sayUno() {
        // Método para o jogador dizer Uno
        String uno = "UNO"; // Define a palavra que tem que ser dita
        String spell; // Define a String que o jogador irá fornecer
        Scanner sc = new Scanner(System.in); // o jogador fornece a String
        long before = System.currentTimeMillis(); // Pega o tempo exato em milissegundos antes do jogador digitar
        System.out.println("Digite UNO: ");
        spell = sc.next();
        long after = System.currentTimeMillis(); // Pega o tempo em milissegundos depois do jogador digitar
        if(uno.compareTo(spell.toUpperCase()) == 0 && (after - before) < 3000){ // UpperCase usado apenas para caso o jogador digite a mesma palavra mas com uma formatação diferente
            // Se o jogador digitou Uno em menos de 3 segundos, retorna true
            sc.close();
            return true;
        }
        // Caso contrário retorna false
        return false;
    }
}
