package Game;
import java.io.IOException;
import java.util.Scanner;

import BoardAndCell.Board;

public class RunGameMain {
// Has the initial Game Menu with method to run menu options
    public static void runGameMenu() throws IOException {

        //Initialize the objects of the game
        Scanner ip = new Scanner(System.in);
        int option = 0;

        // Input for the game
        System.out.print("\nWelcome to the Java Legends of Valor game!!!");
        System.out.println("\n=======================================\nLEGENDS - HEROES AND MONSTERS");

        printGameRules();
        Board board;
        board = new Board(8);
        board.printBoard();

        // Game Menu
        while (true) {
            System.out.println("=======================================\n");
            System.out.println("\nGame Menu:\n1. Play Game\n2. Exit");
            System.out.print("Enter:");

            try {
                option = ip.nextInt();
            } catch (Exception e) {
                // Flush the input token, to ask input again
                ip.next();
                option=0;
            }

            switch (option) {
                case 1:
//                    RunGame.runGame();
                    break;
                case 2:
                    System.out.println("\n=======================================\n");
                    System.out.println("Thank You for playing!!\nSee you soon!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        }

    }

    public static void printGameRules(){
        System.out.println("\nGAME RULES\n==========");
        System.out.println("1. World of play is n x n board, with WASD keys to move");
        System.out.println("2. You play as H1, H2, H3 - Heroes.");
        System.out.println("3. Enter battles with monsters and defeat them to level up");
        System.out.println("4. Buy and Sell inventory at the M - market.");
        System.out.println("5. You can buy weapons, armors, spells and potions");
        System.out.println("6. Cannot enter X - Inaccessible spaces.");
    }

}
