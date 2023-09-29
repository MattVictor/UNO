package Uno;

import CardTypes.*;
import java.io.IOException;
import java.util.Scanner;
import Players.*;
import Uno.Card.Color;

import Outro.Outro;

public class Game {
    private static int i;
    private static int choosedCard, direction = 1;
    private static int numPlayers;
    private static Scanner sc = new Scanner(System.in);
    private static Card currentCard;
    private static Player[] players;
    
    public static void main(String[] args) throws InterruptedException, IOException {
        Deck.reset(); // CRIANDO O DECK
        while(numPlayers < 1 || numPlayers > 4){ //
            Outro.LimpaConsole();
            System.out.println("Entre 2 e 4 para jogadores reais, 1 para jogar contra o Computador");
            System.out.println("Número de Jogadores: ");
            numPlayers = Outro.getNumEntry();
        }

        //Gera a primeira carta do jogo
        currentCard = Deck.getCard(0);
        Deck.removeCard(0);

        if(numPlayers == 1){
            numPlayers++;
            players = new Player[numPlayers];
            System.out.printf("Nome do jogador: ");
            players[0] = new RealPlayer(sc.next());
            players[1] = new Com("COM");
        } else{
            players = new Player[numPlayers];
            for(i = 0; i < numPlayers; i++){
                System.out.printf("Nome do jogador %d: ", (i+1));
                players[i] = new RealPlayer(sc.next());
            }
        }

        i = 0;
        Outro.LimpaConsole();

        players[0].addCard(new ChooseColor(Card.Color.getColor(4), Card.Value.getValue(13)));
        // GAMELOOP
        while(true){
            Outro.printStatus(players, i);
            System.out.printf("Carta atual: %s\n\n", Outro.printCard(currentCard));
            System.out.printf("\u001B[37mCartas de %s:\n", players[i].getName());

            if(players[i] instanceof RealPlayer){
                players[i].printPlayer();

                System.out.println();
                System.out.println("\u001B[37mEscolha sua carta (número negativo para puxar uma carta, 0 para passar)");
                choosedCard = players[i].ChooseCard();
                //Reduzindo o tamanho do index para não causar erros
                choosedCard--;
            }else{
                choosedCard = players[i].ChooseCard();
            }
            
            if(choosedCard == -1){
                // Se o número escolhido for 0, passa para o próximo player
                verifyPlayerCounter();
            }
            else if(choosedCard < -1 && Deck.getDeckSize() != 0){
                // Se o número for menor que 0, puxa uma carta
                Outro.LimpaConsole();

                players[i].drawCard();
                
                if(!verifyValidCard(players[i].getCard(players[i].checkSize()-1))){
                    // Se a carta puxada for diferente da carta atual (em cor e em valor) passa para o próximo player
                    verifyPlayerCounter();
                }

                Outro.changingPlayers();
            }
            else if(choosedCard < -1 && Deck.getDeckSize() == 0){
                verifyPlayerCounter();

                Outro.changingPlayers();
            }
            else if(choosedCard > players[i].checkSize()-1){ // Verifica se o número escolhido está dentro do tamanho do array
                Outro.LimpaConsole();

                System.out.println("Carta escolhida não existe!");
            }
            else if(verifyValidCard(players[i].getCard(choosedCard))){// Verifica se a carta é valida
                //Pega a carta da mão do jogador e coloca na mesa
                currentCard = players[i].getCard(choosedCard);
                players[i].putCard(choosedCard);

                //Checa se algum jogador está sem cartas na mão (Vencedor)
                if(players[i].checkSize() == 0){
                    Outro.LimpaConsole();
                    System.out.printf("Ganhador: %s\n", players[i].getName());
                    break;
                }else if(players[i].checkSize() == 1){
                    if(!players[i].sayUno()){
                        players[i].drawCard();
                        players[i].drawCard();
                    }
                }
                
                if(currentCard instanceof ActionCards){
                    ((ActionCards)currentCard).ExecuteAction();
                }
                
                verifyPlayerCounter();
                Outro.changingPlayers();
                Outro.LimpaConsole();
            }
            else{ //Caso o player jogue uma carta com valor ou cor diferente da carta na mesa
                Outro.LimpaConsole();
                System.out.println("Carta inválida escolhida");
            }
        }
    }

    public static void setDirection(){
        // Método para a carta Reverse
    	direction*=-1;
    }

    public static int getNumPlayers(){
        // Retorna o número de jogadores
        return numPlayers;
    }

    public static void setCardColor(Card ChoosedCard) {
        // Função para a carta Choose Color
    	currentCard = ChoosedCard;
    }

    public static Player getPlayer(int index){
        // Retorna um player pelo index
        return players[index];
    }

    public static Player getCurrentPlayer(){
        // Retorna o jogador atual
        return players[i];
    }

    public static int NextPlayer(){
        // Verfica e retorna o próximo player, para as cartas de Draw (que afetam o player subsequente)
        if(i == 0 && direction < 0){
            // Se o contador for igual a 0 e a direção for negativa, o próximo player será o último do array
            return numPlayers-1;
        }
        else if(i == numPlayers-1 && direction > 0){
            // se o contador for igual ao número de players-1 e a direção for positiva, o próximo player sera o 0
            return 0;
        }
        // Se nenhuma das condições acima for cumprida, o próximo player será apenas o atual somado a direção (positiva ou negativa)
        return i+direction;
    }

    public static Card getCurrentCard(){
        // retorna a carta atual para fins de verificação
        return currentCard;
    }

    public static void verifyPlayerCounter(){
        // Verfica o contador para que ele não exceda os limites do array
        i+=direction;
        if(i == numPlayers){ // se for igual ao número de players definido lá em cima, o próximo será 0
            i = 0;
        }
        else if(i < 0){ // se for menor que 0 (limite mínimo do array), o próximo player será o numPlayers-1
            i = numPlayers-1;
        }
    }

    public static boolean verifyValidCard(Card cartaPlayer){
        //Verica se a carta é válida para ser jogada, true para caso seja válida
    if(currentCard.getColor() == Color.WILD || cartaPlayer.getColor() == Color.WILD)
        //se a carta for do tipo Wild, pode ser jogada em cima de qualquer outra
        return true;
    else if(currentCard.getColor() == cartaPlayer.getColor()) // se a cores forem igual
        return true;
    else if(currentCard.getValue() == cartaPlayer.getValue()) // se os valores forem iguais
        return true;
    else
        return false;
    }
}