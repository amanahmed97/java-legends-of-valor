import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RunGame {
    // todo make changes here
    // Contains the main while loop and switch case, conditions that the game runs on.
    public static Board board;
    public static int numberHeroes = 1;
    public static Player player;
    public static int playerTurn=1;
    public static int gameTurn = 1;
    public static void runGame() throws IOException {

        System.out.println("Starting game");
        setupGame();


        while( !(heroWin() || monsterWin()) ){
//            Player.printHeroes();

            switch(playerTurn){
                case 1:
                    heroTurn();
                    playerTurn=2;
                    break;
                case 2:
                    monsterTurn();
                    playerTurn=1;
                    gameTurn++;
                    break;
            }

        }

    }


    public static void setupGame() throws IOException {
        // Setup Game variables
        Scanner ip = new Scanner(System.in);
        System.out.println("\n=======================================\n");
        System.out.println("\nLegends - Monsters and Heroes");
        System.out.println("Choose Board size between 6 and 1000 [8 - Recommended]");
        System.out.print("Choose Board size : ");
        int size = 8;
        try {
//            size = ip.nextInt(); // todo remove default
            if (size < 6 || size > 1000) throw new Exception("Out of valid range");
        } catch (InputMismatchException e) {
            ip.next();
            System.out.println("Valid board size not selected.");
            System.out.println("Going with default board size 8.");
            size=8;
        } catch (Exception e) {
            System.out.println(e.toString() + "\nValid board size not selected.");
            System.out.println("Going with default board size 8.");
            size=8;
        }

        board = new Board(size);
        numberHeroes = Player.getNumberHeroes();
        numberHeroes = 3;// todo refactor
        player = new Player("Yours Truly",1);
        Player.heroSet(numberHeroes);
        Weapons.populate();
        Armory.populate();
        Potions.populate();
        Spells.populate();
        Monster.populate();
        Monster.spawnMonsters();
    }

    public static boolean generateBattle(int heroSelect){
//        if(board.getBoardSymbol(player.xPosition, player.yPosition) == 'M')
//            return false;
        HeroType hero = Player.heroes.get(heroSelect);
        int monsterSelect=-1;

        // Check if monster is in range for attack
        for(int i=0;i<Monster.spawnMonsters.size();i++){
            Monster monster = Monster.spawnMonsters.get(i);
            if( (monster.xPosition==hero.xPosition-1 || monster.xPosition==hero.xPosition+1) && (monster.lane == hero.lane) ){
                monsterSelect=i;
            }
        }

        // Enter into battle if in range
        if (monsterSelect != -1) {
            Battle.enterBattle(heroSelect, monsterSelect);
            return true;
        }

        return false;
    }

    public static boolean heroTurn(){
        System.out.println("\nHERO TURN"+"\n=========");

        for (int i=0; i < RunGame.numberHeroes; i++){

            if(Player.heroes.get(i).HP <= 0)
                continue;

            heroPlay(i);
        }

        return true;
    }

    public static boolean monsterTurn(){
        System.out.println("\nMONSTER TURN"+"\n=========");

        for (int i=0; i < Monster.spawnMonsters.size(); i++){
            monsterPlay(i);
        }

        return true;
    }

    public static void heroPlay(int heroSelect) {
            // Setting up game variables
            Scanner ip = new Scanner(System.in);
            char gameInput=0;
            boolean gameCheck = false;
            int newXPosition;
            int newYPosition;
            HeroType hero = Player.heroes.get(heroSelect);

            System.out.println("\n=================================================\n");
            System.out.println("\nGame Turn " + gameTurn + "\n============");
            board.printBoard();

            System.out.println("\nHERO TURN : "+hero.name+"\n=========");
            System.out.println("Markers: H - Party of Heroes, M - Market, X - Inaccessible space");
            System.out.println("Movement: w - Move Up, s - Move Down, a - Move Left, d - Move Right");
            System.out.println("Actions: i - info, m - Enter Market, q - Quit");
            System.out.print("Enter: ");


            try {
                gameInput = ip.next().charAt(0);
                gameInput = Character.toLowerCase(gameInput);

            } catch (Exception e) {
                // Flush the input token, to ask input again
//                ip.next();
                gameInput = 0;
                System.out.println("Enter valid option.");
            }

            switch (gameInput) {
                case 'w':
                    newXPosition = hero.xPosition - 1;
                    if (Board.validPosition(newXPosition, hero.yPosition)) {
                        hero.setPosition(newXPosition, hero.yPosition);
                        generateBattle(heroSelect);
                    }
                    break;
                case 'a':
                    newYPosition = hero.yPosition - 1;
                    if (Board.validPosition(hero.xPosition, newYPosition)) {
                        hero.setPosition(hero.xPosition, newYPosition);
                        generateBattle(heroSelect);
                    }
                    break;
                case 's':
                    newXPosition = hero.xPosition + 1;
                    if (Board.validPosition(newXPosition, hero.yPosition)) {
                        hero.setPosition(newXPosition, hero.yPosition);
                        generateBattle(heroSelect);
                    }
                    break;
                case 'd':
                    newYPosition = hero.yPosition + 1;
                    if (Board.validPosition(hero.xPosition, newYPosition)) {
                        hero.setPosition(hero.xPosition, newYPosition);
                        generateBattle(heroSelect);
                    }
                    break;
                case 'i':
                    Info.infoDisplay();
                    break;
                case 'm':
                    Market.enterMarket();
                    break;
                case 'q':
                    System.out.println("\n=======================================\n");
                    System.out.println("Thank You for playing!!\nSee you soon!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid gameInput. Try again.");
            }

    }

    public static void monsterPlay(int monsterSelect){
        Monster monster = Monster.spawnMonsters.get(monsterSelect);

        // Move the monster toward the Nexus
        System.out.println("MONSTER PLAYS : "+monster.name+"\n=============");
        monster.moveNexus();
    }

    public static boolean heroWin(){
        for(HeroType hero: Player.heroes){
            if(hero.HP>0){
                if(hero.xPosition == Board.dimension-1){
                    System.out.println("NEXUS REACHED!!!\nHEROES WIN!!!!!"+"\nGAME WON!!!"+"\nThank you! See you again!");
                    System.exit(0);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean monsterWin(){
        for(Monster monster: Monster.spawnMonsters){

            if(monster.xPosition == 0){
                System.out.println("NEXUS REACHED!!!\nMONSTERS WIN!!"+"\nGAME OVER!!!"+"\nThank you! See you again!");
                System.exit(0);
                return true;
            }

        }
        return false;
    }

}
