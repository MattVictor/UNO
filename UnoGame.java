package Uno;

import java.util.Scanner;

public class UnoGame {
    private static int i, j, nextPLayer = 0, cartaEscolhida, sentido = 1;
    private static Scanner sc = new Scanner(System.in);
    private static UnoCard pilha = new UnoCard(null, null);
    
    public static void increaseI(){
        i+=sentido;
    }

    public static void setFluxo(int novoFluxo){
    	sentido = novoFluxo;
    }
    
    public static void setCardColor(UnoCard ChoosedCard) {
    	pilha = ChoosedCard;
    }

public static void main(String[] args) {
        UnoDeck.reset(); // CRIANDO O DECK
        System.out.println("Número de Jogadores: ");
        int numPlayers = sc.nextInt();
        UnoPlayer[] players = new UnoPlayer[numPlayers];
        for(i = 0; i < numPlayers; i++){
            System.out.printf("Nome do jogador %d: ", (i+1));
            players[i] = new UnoPlayer(sc.next());
        }
        i = 0;
        // JOGO
        while(true){
            System.out.printf("Carta atual: %s\n\n", pilha.toString());
            System.out.printf("Cartas de %s:\n", players[i].getName());
            players[i].printPlayer();
            System.out.println();
            System.out.println("Escolha sua carta (nÃºmero negativo para puxar uma carta)");
            cartaEscolhida = sc.nextInt();
            if(cartaEscolhida < 0 && UnoDeck.getDeckSize() != 0){
                players[i].drawCard();
                i+=sentido;
                if(i == numPlayers){
                    i = 0;
                }
            }
            else if(UnoFuncs.checkCard(pilha,players[i].getCard(cartaEscolhida)) == 1){
                pilha = players[i].getCard(cartaEscolhida);
                UnoFuncs.blockCard(pilha);
                UnoFuncs.reverseCard(pilha);
                UnoFuncs.chooseColor(pilha);
                if(i == 0 && sentido < 0){
                    nextPLayer = numPlayers + sentido;
                }
                else if(i == numPlayers-1 && sentido > 0){
                    nextPLayer = 0;
                }
                else{
                    nextPLayer+=sentido;
                }
                for(j = 0; j < UnoFuncs.draw(players[i].getCard(cartaEscolhida)); j++){
                    players[nextPLayer].drawCard();
                }
                if(players[i].checkSize() == 0){
                    System.out.printf("Ganhador: %s\n", players[i].getName());
                    break;
                }
                players[i].putCard(cartaEscolhida);
                i+=sentido;
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
