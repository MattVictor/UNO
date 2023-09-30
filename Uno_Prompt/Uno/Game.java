package Uno;

import CardTypes.*;

import java.io.IOException;
import java.util.Scanner;
import Players.*;
import Uno.Card.Color;

import Outro.Outro;

public class Game {
    private static int playerCounter;
    private static int choosedCard, direction = 1;
    private static int numPlayers;
    private static Scanner sc = new Scanner(System.in);
    private static Card currentCard;
    private static Player[] players;
    private static boolean gameClosed;

    public static void runGame() throws InterruptedException, IOException{
        setNumPlayers();

        createGame();

        Outro.LimpaConsole();
        // GAMELOOP
        while(true){ // Enquanto não tiver um ganhador, o loop continua
            Outro.printStatus(players, playerCounter);
            System.out.printf("Carta atual: %s\n\n", Outro.printCard(currentCard));
            System.out.printf("\u001B[37mCartas de %s:\n", players[playerCounter].getName());

            if(players[playerCounter] instanceof RealPlayer){ // Caso seja um jogador real então imprime as duas cartas
                players[playerCounter].printPlayer();

                System.out.println();
                System.out.println("\u001B[37mEscolha sua carta (número negativo para puxar uma carta, 0 para passar)");
                choosedCard = players[playerCounter].ChooseCard();
                //Reduzindo o tamanho do index para não causar erros
                choosedCard--;
            }else{
                choosedCard = players[playerCounter].ChooseCard(); // Necessário separar pois o Com joga as cartas baseado no index
            }
            
            if(choosedCard == -1){
                // Se o número escolhido for 0, passa para o próximo player
                verifyPlayerCounter();
                Outro.changingPlayers();
            }
            else if(choosedCard < -1 && Deck.getDeckSize() != 0){
                // Se o número for menor que 0, puxa uma carta
                Outro.LimpaConsole();

                players[playerCounter].drawCard();
                
                if(!verifyValidCard(players[playerCounter].getCard(players[playerCounter].checkSize()-1))){
                    // Se a carta puxada for diferente da carta atual (em cor e em valor) passa para o próximo player
                    verifyPlayerCounter();
                    Outro.changingPlayers();
                }

            }
            else if(choosedCard < -1 && Deck.getDeckSize() == 0){
                verifyPlayerCounter();
                if(players[playerCounter] instanceof RealPlayer){
                    Outro.LimpaConsole();
                    System.out.println("Não Há mais cartas a serem puxadas!");
                    Outro.sleep(3);

                    Outro.changingPlayers();
                }
                if(gameClosed){ // Verifica se o jogo foi fechado por alguma ação (especificamente pela carta Choose Color)
                    winnerByPoints();
                    break;
                }
            }
            else if(choosedCard > players[playerCounter].checkSize()-1){ // Verifica se o número escolhido está dentro do tamanho do array
                Outro.LimpaConsole();

                System.out.println("Carta escolhida não existe!");
            }
            else if(verifyValidCard(players[playerCounter].getCard(choosedCard))){// Verifica se a carta é valida
                //Pega a carta da mão do jogador e coloca na mesa
                currentCard = players[playerCounter].getCard(choosedCard);
                players[playerCounter].putCard(choosedCard);

                //Apenas entra nesta condição caso o deck tenha duas cartas ou mais
                if(players[playerCounter].checkSize() == 1 && Deck.getDeckSize() > 1){
                    // Se o jogadoor não conseguir dizer Uno ele puxa duas cartas
                    if(!players[playerCounter].sayUno()){
                        players[playerCounter].drawCard();
                        players[playerCounter].drawCard();
                    }
                }
                
                if(verifyWinner()){ // Verifica se o jogador atual ganhou o jogo
                    break;
                }

                // Se a carta que foi jogada for uma Instância de ActionCards, então executa a ação respectiva
                if(currentCard instanceof ActionCards){
                    ((ActionCards)currentCard).ExecuteAction();
                }
                
                if(gameClosed){ // Verifica se o jogo foi fechado por alguma ação (especificamente pela carta Choose Color)
                    winnerByPoints();
                    break;
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

    //--------------------------------------------------------------------------------------------------------------------------------------------------
    // Métodos para o início do jogo
    public static void createGame(){
        direction = 1;
        playerCounter = 0;
        gameClosed = false;
        Deck.reset(); // Resetando deck de cartas
        currentCard = Deck.getCard(0); // Pegando carta aleatória do deck
        Deck.removeCard(0); // Removendo a carta que pegamos

        if(numPlayers == 1){ // Se tiver apenas um jogador, então o jogo será contra o Computador
            numPlayers++;
            players = new Player[numPlayers];
            System.out.printf("Nome do jogador: ");
            players[0] = new RealPlayer(sc.next());
            players[1] = new Com("COM");
        } else{ // Se não, então o jogo será contra outros jogadores
            players = new Player[numPlayers];
            for(int i = 0; i < numPlayers; i++){
                System.out.printf("Nome do jogador %d: ", (i+1));
                players[i] = new RealPlayer(sc.next());
            }
        }

        playerCounter = 0;
    }

    public static void setNumPlayers() throws InterruptedException, IOException{
        // Define o número de jogadores
        while(numPlayers < 1 || numPlayers > 4){ //
            Outro.LimpaConsole();
            System.out.println("Entre 2 e 4 para jogadores reais, 1 para jogar contra o Computador");
            System.out.println("Número de Jogadores: ");
            numPlayers = Outro.getNumEntry();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    // Getters e Setters, apenas os necessários
    public static void setDirection(){
        // Método para a carta Reverse
    	direction*=-1;
    }

    public static void setCardColor(Card ChoosedCard) {
        // Função para a carta Choose Color
    	currentCard = ChoosedCard;
    }

    public static Card getCurrentCard(){
        // retorna a carta atual para fins de verificação
        return currentCard;
    }

    public static int getNumPlayers(){
        // Retorna o número de jogadores
        return numPlayers;
    }

    public static Player getPlayer(int index){
        // Retorna um player pelo index
        return players[index];
    }

    public static Player getCurrentPlayer(){
        // Retorna o jogador atual
        return players[playerCounter];
    }

    //---------------------------------------------------------------------------------------------------------------------------
    // Métodos para tratamentos para o contador do player e o próximo player (principalmente pelas cartas de Ação)
    public static int NextPlayer(){
        // Verfica e retorna o próximo player, para as cartas de Draw (que afetam o player subsequente)
        if(playerCounter == 0 && direction < 0){
            // Se o contador for igual a 0 e a direção for negativa, o próximo player será o último do array
            return numPlayers-1;
        }
        else if(playerCounter == numPlayers-1 && direction > 0){
            // se o contador for igual ao número de players-1 e a direção for positiva, o próximo player sera o 0
            return 0;
        }
        // Se nenhuma das condições acima for cumprida, o próximo player será apenas o atual somado a direção (positiva ou negativa)
        return playerCounter+direction;
    }

    public static void verifyPlayerCounter(){
        // Verfica o contador para que ele não exceda os limites do array
        playerCounter+=direction;
        if(playerCounter == numPlayers){ // se for igual ao número de players definido lá em cima, o próximo será 0
            playerCounter = 0;
        }
        else if(playerCounter < 0){ // se for menor que 0 (limite mínimo do array), o próximo player será o numPlayers-1
            playerCounter = numPlayers-1;
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    // Métodos mais complexos, para verificações com multiplas condições
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

    public static boolean verifyWinner() throws InterruptedException, IOException{
        if(players[playerCounter].checkSize() == 0){ // Se o jogador atual tiver sem cartas na mão, então ele ganhou
            Outro.LimpaConsole();
            System.out.printf("Ganhador: %s\n\n", players[playerCounter].getName());
            System.out.println("Digite e aperte Enter para voltar ao Menu principal");
            sc.next();
            return true;
        }
        return false;
    }

    public static void winnerByPoints() throws InterruptedException, IOException{
        // Contagem de pontos para caso o jogo feche
        Player wPlayer = players[0];
        int mostPoints = 0;
        int playerPoints = 0;
        for(Player player : players){
            playerPoints = 0;
            for(int j = 0; j < player.checkSize(); j++){
                playerPoints += player.getCard(j).getCardPoints();
            }
            if(playerPoints > mostPoints){
                wPlayer = player;
                mostPoints = playerPoints;
            }
        }
        Outro.LimpaConsole();
        System.out.printf("Ganhador %s com um total de %d Pontos\n\n", wPlayer.getName(), mostPoints);
        System.out.println("Digite e aperte Enter para voltar ao Menu principal");
        sc.next();
    }

    public static void CloseGame(){
        // Verificação para saber se o jogo foi fechado
        boolean exists = false; // Primeiramente assumimos que por padrão, não existe mais cartas com a mesma cor que a carta colocada
		if(Deck.getDeckSize() != 0){
            // Se ainda existir alguma carta no deck, verificamos ele
			for(int i = 0; i < Deck.getDeckSize(); i++){
				if(Game.getCurrentCard().getColor() == Deck.getCard(i).getColor()){
					exists = true; // se alguma carta de mesma cor for encontrada no deck, então a cor ainda existe e  jogo pode continuar
					break;
				}
			}
		}
        if(!exists){ // Se a carta for encontrada no deck, então não precisamos verficar nos players
            // Se a carta não for encontrada no deck, verificamos na mão de cada player se a carta ainda existe
			for(Player player : players){
				for(int i = 0; i < player.checkSize(); i++){
					if(Game.getCurrentCard().getColor() == player.getCard(i).getColor()){
						exists = true; // Da mesma forma, se as cores forem iguais ainda existe alguma carta
						break;
					}
				}
			}
		}
		if(!exists){ // Se não existir nenhuma carta com a mesma cor, então o jogo é fechado
			gameClosed = true;
		}
    }
}