package Uno;

import java.util.Scanner;

public class UnoGame {
    private static int i, j, nextPLayer = 0, cartaEscolhida, fluxo = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void increaseI(){
        i+=fluxo;
    }

    public static void setFluxo(int novoFluxo){
        fluxo = novoFluxo;
    }

public static void main(String[] args) {
        UnoDeck.reset(); // CRIANDO O DECK
        System.out.println("NÃºmero de Jogadores: ");
        int numPlayers = sc.nextInt();
        UnoPlayer[] players = new UnoPlayer[numPlayers];
        for(i = 0; i < numPlayers; i++){
            System.out.printf("Nome do jogador %d: ", (i+1));
            players[i] = new UnoPlayer(sc.next());
        }
        i = 0;
        UnoCard pilha = new UnoCard(null, null); // PRIMEIRA CARTA
        // JOGO
        while(true){
            System.out.printf("Carta atual: %s\n\n", pilha.toString());
            System.out.printf("Cartas de %s:\n", players[i].getName());
            players[i].printPlayer();
            System.out.println();
            System.out.println("Escolha sua carta (nÃºmero negativo para puxar uma carta)");
            cartaEscolhida = sc.nextInt();
            if(cartaEscolhida < 0){
                players[i].drawCard();
                i+=fluxo;
                if(i == numPlayers){
                    i = 0;
                }
            }
            else if(UnoFuncs.checkCard(pilha,players[i].getCard(cartaEscolhida)) == 1){
                pilha = players[i].getCard(cartaEscolhida);
                UnoFuncs.blockCard(pilha);
                UnoFuncs.reverseCard(pilha);
                if(i == 0 && fluxo < 0){
                    nextPLayer = numPlayers + fluxo;
                }
                else if(i == numPlayers-1 && fluxo > 0){
                    nextPLayer = 0;
                }
                else{
                    nextPLayer+=fluxo;
                }
                for(j = 0; j < UnoFuncs.draw(players[i].getCard(cartaEscolhida)); j++){
                    players[nextPLayer].drawCard();
                }
                if(players[i].checkSize() == 0){
                    System.out.printf("Ganhador: %s\n", players[i].getName());
                    break;
                }
                players[i].putCard(cartaEscolhida);
                i+=fluxo;
                if(i == numPlayers){
                    i = 0;
                }
                else if(i < 0){
                    i = numPlayers-1;
                }
            }
            else{
                System.out.println("Carta inválida escolhida");
            }
        }
    }
}
