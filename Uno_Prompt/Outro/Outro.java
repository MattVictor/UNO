package Outro;

import Uno.Card;
import Uno.Game;
import Players.*;
import java.util.Scanner;

public class Outro {
    // classe criada apenas para auxiliar a execução do programa principal
    public static Scanner sc = new Scanner(System.in);

    public static void LimpaConsole(){
        System.out.print("\033[H\033[2J");  

        System.out.flush(); 
    }

    public static String printCard(Card card){
        // retorna uma string com a carta e sua respectiva cor
        String color;
        switch(card.getColor()){
            case BLUE:
                color = "\u001B[34m";
                break;
            case GREEN:
                color = "\u001B[32m";
                break;
            case RED:
                color = "\u001B[31m";
                break;
            case WILD:
                color = "\u001B[30m";
                break;
            case YELLOW:
                color = "\u001B[33m";
                break;
            default:
                color = "\u001B[0m";
                break;
        }
        return color + card.toString();
    }

    public static void changingPlayers() throws InterruptedException{
        // Dá um delay entre a troca de players, para que o um jogador não veja a mão do outro
        if(!(Game.getPlayer(1) instanceof Com)){
            int i = 3; // contador de 3 segundos
            LimpaConsole();
            while(i > 0){
                System.out.printf("Próximo jogador em %d", i); // printa cada segundo do delay
                Thread.sleep(1000); // delay de um segundo entre os prints
                i-=1;
                LimpaConsole();
            }
        }
    }

    public static void printStatus(Player[] players, int currentPlayer){
        // Printa o status dos outros players (quantas cartas eles possuem agora)
        System.out.printf("Quantidade de cartas: ");
        System.out.println();
        for(int i = 0; i < Game.getNumPlayers(); i++){
            if(i != currentPlayer) // não deve printar a quantidade de cartas do player atual
                System.out.printf("%s - %d | ", players[i].getName(), players[i].checkSize());
        }
        System.out.println();
    }

    public static int getNumEntry(){
        // Método usado para tratar as entradas numéricas, garantindo que elas sejam números inteiros
        String entry;
        int num = 0;
        while(true){
            entry = sc.next(); // pega a entrada como string
            try{
                num = Integer.parseInt(entry);
                break;
            } catch(NumberFormatException e){ // se não for um numero inteiro, retorna um erro
                System.out.println("Entrada inválida, por favor Digite um numero inteiro: ");
            }
        }
        return num;
    }
}
