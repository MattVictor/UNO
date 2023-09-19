package Uno;

import java.io.IOException;
import java.util.Scanner;

public class UnoGame {
    private static int i;
    private static int cartaEscolhida, direction = 1;
    private static int numPlayers;
    private static Scanner sc = new Scanner(System.in);
    private static UnoCard pilha;
    private static UnoPlayer[] players;
    
    public static void increaseI(){
        i+=direction;
    }

    public static void setI(int num){
        i = num;
    }

    public static int getI(){
        return i;
    }

    public static void setDirection(int novoFluxo){
    	direction = novoFluxo;
    }
    
    public static int getDirection(){
        return direction;
    }

    public static int getNumPlayers(){
        return numPlayers;
    }

    public static UnoPlayer getPlayer(int i){
        return players[i];
    }

    public static void setCardColor(UnoCard ChoosedCard) {
    	pilha = ChoosedCard;
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
        UnoDeck.reset(); // CRIANDO O DECK
        while(numPlayers < 1 || numPlayers > 4){ //
            Outro.LimpaConsole();
            System.out.println("Número de Jogadores: ");
            numPlayers = sc.nextInt();
        }

        //Gera a primeira carta do jogo
        pilha = UnoDeck.getCard(0);
        UnoDeck.removeCard(0);

        UnoPlayer[] players = new UnoPlayer[numPlayers];
        for(i = 0; i < numPlayers; i++){
            System.out.printf("Nome do jogador %d: ", (i+1));
            players[i] = new UnoPlayer(sc.next());
        }
        i = 0;
        Outro.LimpaConsole();
        // GAMELOOP
        while(true){
            System.out.printf("Carta atual: %s\n\n", Outro.printCard(pilha));
            System.out.printf("\u001B[37mCartas de %s:\n", players[i].getName());

            players[i].printPlayer();

            System.out.println();
            System.out.println("\u001B[37mEscolha sua carta (número negativo para puxar uma carta)");
            cartaEscolhida = sc.nextInt();

            if(cartaEscolhida < 0 && UnoDeck.getDeckSize() != 0){
                Outro.LimpaConsole();
                players[i].drawCard();
                i+=direction;
                if(i == numPlayers){
                    i = 0;
                }
                else if(i < 0){
                    i = numPlayers-1;
                }
            }
            else if(UnoRules.checkCard(pilha,players[i].getCard(cartaEscolhida))){// Verifica se a carta é valida
                //Pega a carta da mão do jogador e coloca na mesa
                pilha = players[i].getCard(cartaEscolhida);
                players[i].putCard(cartaEscolhida);

                //Checa se algum jogador está sem cartas na mão (Vencedor)
                if(players[i].checkSize() == 0){
                    Outro.LimpaConsole();
                    System.out.printf("Ganhador: %s\n", players[i].getName());
                    break;
                }
                
                switch(pilha.getValue()){
                    case BLOCK:
                        UnoRules.blockCard(pilha);
                        break;
                    case CHOOSE_COLOR:
                        UnoRules.chooseColor(pilha);
                        break;
                    case DRAW_FOUR:
                        UnoRules.draw(pilha);
                        break;
                    case DRAW_TWO:
                        UnoRules.draw(pilha);
                        break;
                    case REVERSE:
                        UnoRules.reverseCard(pilha);
                        break;
                    default:
                        break;
                    
                }
                
                //Depois de feitas as verificações, coloca a carta na mesa
                i+=direction;

                //Verfica o contador para que não exceda o limite do array
                if(i == numPlayers){
                    i = 0;
                }
                else if(i < 0){
                    i = numPlayers-1;
                }

                Outro.LimpaConsole();
            }
            else{ //Caso o player jogue uma carta com valor ou cor diferente da carta na mesa
                Outro.LimpaConsole();
                System.out.println("Carta inválida escolhida");
            }
        }
    }
}
