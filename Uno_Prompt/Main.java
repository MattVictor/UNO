import java.util.Scanner;
import Outro.Outro;
import Uno.Game;

public class Main {
    private static Scanner sc;
    private static Scanner scanner = new Scanner(System.in);
    private static int opcao;
    public static void main(String[] args) throws InterruptedException{
        while(true){
            opcao = 0;
            Outro.LimpaConsole();
            System.out.println("\u001B[5mUNO");
            System.out.println("1 - Começar o jogo");
            System.out.println("2 - Regras");
            System.out.println("3 - Como Jogar");
            System.out.println("4 - Sair");

            opcao = Outro.getNumEntry();

            switch(opcao){
                case 1:
                    Outro.LimpaConsole();
                    Game.runGame();
                    break;
                case 2:
                    Outro.LimpaConsole();
                    regras();
                    break;
                case 3:
                    Outro.LimpaConsole();
                    comoJogar();
                    break;
                case 4:
                    System.exit(opcao);
                    break;
                default:
                    break;
            }
        }
    }

    public static void regras(){
        System.out.println(
            "REGRAS\n\n"+

            "Ganha o primeiro jogador a ficar sem cartas.\n\n"+

            "Existem um total de 5 cores:\n"+
            "- \u001B[31mRed;\n"+
            "- \u001B[32mGreen;\n"+
            "- \u001B[33mYellow;\n"+
            "- \u001B[34mBlue;\n"+

            "\u001B[37mExistem 108 cartas divididas igualmente sendo:\n"+
            "- 19 cartas enumeradas de 0 a 9 para cada cor (apenas 1 delas é 0);\n"+
            "- 8 cartas Reverse (Inverte o sentido do jogo);\n"+
            "- 8 cartas Block (Bloqueia o próximo jogador de jogar);\n"+
            "- 8 cartas Draw Two (Faz o próximo jogador puxar duas cartas);\n"+
            "- 4 cartas Draw Four (Faz o próximo jogador puxar quatro cartas);\n"+
            "- 4 cartas coringa Choose Color (O jogador pode escolher a cor que será jogada pelo próximo jogador);\n\n"+

            "Uma carta aleatória é selecionada para dar início ao jogo,\n"+
            "e 7 cartas são distribuídas para cada jogador\n"+
            "Cada jogador pode jogar escolher uma carta de igual valor\n"+
            "ou cor com a carta que está na mesa, e passar para o próximo\n"+
            "jogador.\n\n"+

            "Se o jogador não tiver nenhuma carta com a respectiva cor\n"+
            "ou valor da carta na mesa, ele puxa uma carta, Se a carta\n"+
            "puxada tiver o mesmo valor ou cor da carta na mesa, o jogador\n"+
            "pode (caso queira) jogar esta carta, caso seja diferente, ele passa.\n\n"+

            "Quando algum dos jogadores tiver apenas uma carta ele poderá dizer UNO\n"+
            "Caso não diga a tempo, ele puxa duas cartas.\n\n"+
            
            "Ganha o jogador que estiver sem cartas na mão.\n"
        );
        System.out.println();
        System.out.println("Digite qualquer coisa e pressione Enter para voltar");
        scanner.next();
    }

    public static void comoJogar(){
        System.out.println(
            "COMO JOGAR\n\n"+

            "Para começar, você deverá informar a quantidade de jogadores\n"+
            "Se apenas 1 jogador for informado, este terá o Computador\n"+
            "Como seu oponente\n\n"+

            "7 cartas serão distribuídas para cada um dos jogadores\n"+
            "As cartas só vão ser mostradas na vez do jogador\n"+
            "As cartas aparecerão da seguinte maneira:\n"+
            "(Número para referência) - Cor_Valor\n\n"+

            "Para jogar uma carta você deve informar o número de referência da mesma\n"+
            "Caso queira puxar uma carta, digite algum valor negativo\n"+
            "Se puxar e quiser passar, digite 0\n\n"+

            "Quando algum dos jogadores ficar apenas com uma carta\n"+
            "Uma ação vai ser iniciada, este jogador deve digitar 'Uno'\n"+
            "em menos de 3 segundos, do contrário puxará duas cartas\n\n"+

            "Para as cartas do tipo 'Choose Color' uma ação se iniciará onde o jogador\n"+
            "Deverá escolher a cor a ser jogada pelo próximo jogador\n"
        );
        System.out.println();
        System.out.println("Digite qualquer coisa e pressione Enter para voltar");
        scanner.next();
    }
}
