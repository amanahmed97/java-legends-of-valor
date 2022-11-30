package Game;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import BoardAndCell.Board;
import BoardAndCell.Colors;
import Character.Hero;
import Character.Monster;
import Character.Player;
import Factory.FactoryMonster;
import Item.Armory;
import Item.Potions;
import Item.Spells;
import Item.Weapons;

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
		System.out.println("Board size: 8");
		System.out.println();
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
		//numberHeroes = Player.getNumberHeroes();
		numberHeroes = 3;// todo refactor
		System.out.println("Number of players: 3");
		System.out.println();
		player = new Player("Yours Truly",1);
		Player.heroSet(numberHeroes);
		Weapons.populate();
		Armory.populate();
		Potions.populate();
		Spells.populate();
		FactoryMonster.populate();
		FactoryMonster.spawnMonsters();
	}

	public static boolean generateBattle(int heroSelect){
		//        if(board.getBoardSymbol(player.xPosition, player.yPosition) == 'M')
		//            return false;
		Hero hero = Player.heroes.get(heroSelect);
		int monsterSelect=-1;

		// Check if monster is in range for attack
		for(int i=0;i<FactoryMonster.spawnMonsters.size();i++){
			Monster monster = FactoryMonster.spawnMonsters.get(i);
			if( (monster.getxPosition()==hero.getxPosition()-1 || monster.getxPosition()==hero.getxPosition()+1) && (monster.getLane() == hero.getLane()) ){
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

			if(Player.heroes.get(i).getHP() <= 0)
				continue;

			heroPlay(i);
		}

		return true;
	}

	public static boolean monsterTurn(){
		System.out.println("\nMONSTER TURN"+"\n=========");

		for (int i=0; i < FactoryMonster.spawnMonsters.size(); i++){
			monsterPlay(i);
		}

		return true;
	}

	public static void heroPlay(int heroSelect) {
		// Setting up game variables
		Scanner ip = new Scanner(System.in);
		char gameInput=0;
		boolean gameCheck = false;
		boolean check = false;
		
		Hero hero = Player.heroes.get(heroSelect);

		System.out.println("\n=================================================\n");
		System.out.println("\nGame Turn " + gameTurn + "\n============");
		board.printBoard();

		System.out.println("\nHERO TURN : "+hero.getDisplayName()+" "+hero.getName()+"\n=========");
		System.out.println("Markers: H - Hero, X - Inaccessible space");
		System.out.println(Colors.RED_BACKGROUND+ "     " +Colors.RESET+ " - Nexus, " + Colors.YELLOW_BACKGROUND_BRIGHT + "     " + Colors.RESET + " - Bush, " + Colors.BLUE_BACKGROUND + "     " + Colors.RESET + " - Cave, " + Colors.PURPLE_BACKGROUND+ "     " + Colors.RESET + " - Koulou, " + Colors.GREEN_BACKGROUND + "     " + Colors.RESET + " - Plain");
		System.out.println("Movement: w - Move Up, s - Move Down, a - Move Left, d - Move Right, r - Recall Nexus, t - Teleport");
		System.out.println("Actions: i - info, m - Enter Market, q - Quit");
		System.out.print("Enter: ");


		try {
			gameInput = ip.next().charAt(0);
			gameInput = Character.toLowerCase(gameInput);
			while (!inputValidationAndProceed(hero, gameInput, heroSelect)) {
				System.out.print("Enter valid option: ");
				gameInput = ip.next().charAt(0);
				gameInput = Character.toLowerCase(gameInput);
			}
		} catch (Exception e) {
			// Flush the input token, to ask input again
			//                ip.next();
			gameInput = 0;
			System.out.println("Enter valid option.");
		}


	}

	public static boolean inputValidationAndProceed(Hero hero, int gameInput, int heroSelect) {
		int newXPosition;
		int newYPosition;
		switch (gameInput) {
		case 'w':
			newXPosition = hero.getxPosition() - 1;
			if (Board.validPosition(newXPosition, hero.getyPosition())) {
				hero.setPosition(newXPosition, hero.getyPosition(), hero);
				generateBattle(heroSelect);
			} else
				return false;
			break;
		case 'a':
			newYPosition = hero.getyPosition() - 1;
			if (Board.validPosition(hero.getxPosition(), newYPosition)) {
				hero.setPosition(hero.getxPosition(), newYPosition, hero);
				generateBattle(heroSelect);
			} else 
				return false;
			break;
		case 's':
			newXPosition = hero.getxPosition() + 1;
			if (Board.validPosition(newXPosition, hero.getyPosition())) {
				hero.setPosition(newXPosition, hero.getyPosition(), hero);
				generateBattle(heroSelect);
			} else
				return false;
			break;
		case 'd':
			newYPosition = hero.getyPosition() + 1;
			if (Board.validPosition(hero.getxPosition(), newYPosition)) {
				hero.setPosition(hero.getxPosition(), newYPosition, hero);
				generateBattle(heroSelect);
			} else
				return false;
			break;
		case 'r':
			// Recall. Set to start position in Nexus
			newXPosition = 0;
			hero.setPosition(newXPosition, hero.getyPosition(), hero);
			break;
		case 't':
			hero.teleport();
			break;
		case 'i':
			Info.infoDisplay();
			break;
		case 'm':
			Market.enterMarket(hero);
			break;
		case 'q':
			System.out.println("\n=======================================\n");
			System.out.println("Thank You for playing!!\nSee you soon!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid gameInput. Try again.");
		}
		return true;

	}

	public static void monsterPlay(int monsterSelect){
		Monster monster = FactoryMonster.spawnMonsters.get(monsterSelect);

		// Move the monster toward the Nexus
		System.out.println("MONSTER PLAYS : "+monster.getName()+"\n=============");
		monster.moveNexus();
	}

	public static boolean heroWin(){
		for(Hero hero: Player.heroes){
			if(hero.getHP()>0){
				if(hero.getxPosition() == Board.dimension-1){
					System.out.println("NEXUS REACHED!!!\nHEROES WIN!!!!!"+"\nGAME WON!!!"+"\nThank you! See you again!");
					System.exit(0);
					return true;
				}
			}
		}
		return false;
	}

	public static boolean monsterWin(){
		for(Monster monster: FactoryMonster.spawnMonsters){

			if(monster.getxPosition() == 0){
				System.out.println("NEXUS REACHED!!!\nMONSTERS WIN!!"+"\nGAME OVER!!!"+"\nThank you! See you again!");
				System.exit(0);
				return true;
			}

		}
		return false;
	}

}