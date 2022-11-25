//import java.io.IOException;
//import java.util.InputMismatchException;
//import java.util.Random;
//import java.util.Scanner;
//
//public class RunGame {
//    // todo make changes here
//    // Contains the main while loop and switch case, conditions that the game runs on.
//    public static Board board;
//    public static int numberHeroes = 1;
//    public static Player player;
//
//    public static void runGame() throws IOException {
//        // Setting up game variables
//        Scanner ip = new Scanner(System.in);
//        char gameInput=0;
//        int gameTurn = 1;
//        boolean gameCheck = false;
//        int newXPosition;
//        int newYPosition;
//
//        System.out.println("Starting game");
//        setupGame();
//
//        while (true) {
//            System.out.println("\n=================================================\n");
//            System.out.println("\nGame Turn " + gameTurn+"\n============");
//            board.printBoard();
//
//            System.out.println("\nMarkers: H - Party of Heroes, M - Market, X - Inaccessible space");
//            System.out.println("Movement: w - Move Up, s - Move Down, a - Move Left, d - Move Right");
//            System.out.println("Actions: i - info, m - Enter Market, q - Quit");
//            System.out.print("Enter: ");
//
//
//            try {
//                gameInput = ip.next().charAt(0);
//                gameInput = Character.toLowerCase(gameInput);
//
//            } catch (Exception e) {
//                // Flush the input token, to ask input again
////                ip.next();
//                gameInput=0;
//                System.out.println("Enter valid option.");
//            }
//
//            switch (gameInput) {
//                case 'w':
//                    newXPosition = player.xPosition-1;
//                    if (Board.validPosition(newXPosition, player.yPosition)) {
//                        player.setPosition(newXPosition, player.yPosition);
//                        generateBattle();
//                    }
//                    break;
//                case 'a':
//                    newYPosition = player.yPosition-1;
//                    if (Board.validPosition(player.xPosition, newYPosition)) {
//                        player.setPosition(player.xPosition, newYPosition);
//                        generateBattle();
//                    }
//                    break;
//                case 's':
//                    newXPosition = player.xPosition+1;
//                    if (Board.validPosition(newXPosition, player.yPosition)) {
//                        player.setPosition(newXPosition, player.yPosition);
//                        generateBattle();
//                    }
//                    break;
//                case 'd':
//                    newYPosition = player.yPosition+1;
//                    if (Board.validPosition(player.xPosition, newYPosition)) {
//                        player.setPosition(player.xPosition, newYPosition);
//                        generateBattle();
//                    }
//                    break;
//                case 'i':
//                    Info.infoDisplay();
//                    break;
//                case 'm':
//                    Market.enterMarket();
//                    break;
//                case 'q':
//                    System.out.println("\n=======================================\n");
//                    System.out.println("Thank You for playing!!\nSee you soon!");
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Invalid gameInput. Try again.");
//            }
//
//            gameTurn++;
//        }
//    }
//
//    public static void setupGame() throws IOException {
//        // Setup Game variables
//        Scanner ip = new Scanner(System.in);
//        System.out.println("\n=======================================\n");
//        System.out.println("\nLegends - Monsters and Heroes");
//        System.out.println("Choose Board size between 6 and 1000 [8 - Recommended]");
//        System.out.print("Choose Board size : ");
//        int size = 8;
//        try {
//            size = ip.nextInt();
//            if (size < 6 || size > 1000) throw new Exception("Out of valid range");
//        } catch (InputMismatchException e) {
//            ip.next();
//            System.out.println("Valid board size not selected.");
//            System.out.println("Going with default board size 8.");
//            size=8;
//        } catch (Exception e) {
//            System.out.println(e.toString() + "\nValid board size not selected.");
//            System.out.println("Going with default board size 8.");
//            size=8;
//        }
//
//        board = new Board(size);
//        numberHeroes = Player.getNumberHeroes();
//        player = new Player("Yours Truly",1);
//        Player.heroSet(numberHeroes);
//        Weapons.populate();
//        Armory.populate();
//        Potions.populate();
//        Spells.populate();
//        Monster.populate();
//    }
//
//    public static boolean generateBattle(){
//        if(board.getBoardSymbol(player.xPosition, player.yPosition) == 'M')
//            return false;
//        // Roll the dice to check for battle
//        Random random = new Random();
//        int battleChance = random.nextInt(6)+1;
//
//        if (battleChance==6)
//            Battle.enterBattle();
//
//        return true;
//    }
//}
