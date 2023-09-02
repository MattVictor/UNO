package Uno;

import java.util.Scanner;

public class UnoGame {
public static void main(String[] args) {
        UnoDeck.reset();
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("Número de Jogadores: ");
        int numPlayers = sc.nextInt();
        UnoPlayer[] players = new UnoPlayer[numPlayers];
        for(i = 0; i < numPlayers; i++){
            System.out.printf("Nome do jogador %d: ", (i+1));
            players[i] = new UnoPlayer(sc.next());
        }
        i = 0;
        int cartaEscolhida;
        UnoCard pilha = new UnoCard(null, null);
        // JOGO
        while(true){
            System.out.printf("Carta atual: %s\n\n", pilha.toString());
            System.out.printf("Cartas de %s:\n", players[i].getName());
            players[i].printPlayer();
            System.out.println();
            System.out.println("Escolha sua carta (número negativo para puxar uma carta)");
            cartaEscolhida = sc.nextInt();
            if(cartaEscolhida < 0){
                players[i].drawCard();
                i++;
                if(i == numPlayers){
                    i = 0;
                }
            }
            else if(UnoFucs.checkCard(pilha,players[i].getCard(cartaEscolhida)) == 1){
                pilha = players[i].getCard(cartaEscolhida);
                players[i].putCard(cartaEscolhida);
                if(players[i].checkSize() == 0){
                    System.out.printf("Ganhador: %s\n", players[i].getName());
                    break;
                }
                i++;
                if(i == numPlayers){
                    i = 0;
                }
            }
            else{
                System.out.println("Carta inválida escolhida");
            }
        }
    }
}
