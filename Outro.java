package Uno;

import java.io.IOException;

public class Outro {
    public static void LimpaConsole() throws InterruptedException, IOException{
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    public static String printCard(UnoCard card){
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
}
