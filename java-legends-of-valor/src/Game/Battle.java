package Game;
import java.io.*;
import java.util.*;

import Character.Hero;
import Character.Monster;
import Character.Player;
import Factory.FactoryMonster;

public class Battle {
//	Contains the implementation of the battle rounds between heroes and monsters.
public static int battleRound=0;
public static int attackTurn=1;

public static void enterBattle(int heroSelect, int monsterSelect){
    battleRound=1;
    System.out.println("\n======\n"+"BATTLE"+"\n======");
    // todo condition check
    Scanner ip = new Scanner(System.in);
//    Monster.spawnMonsters();

    while(heroCondition(heroSelect) && monsterCondition(monsterSelect)){
        System.out.println("\n\nROUND "+battleRound+"\n========"+"\nSTATS\n=====");
        Player.printHeroes();
        FactoryMonster.printSpawnMonsters();

        switch(attackTurn){
            case 1:
                heroTurn(heroSelect, monsterSelect);
                attackTurn=2;
                break;
            case 2:
                Monster.monsterTurn(heroSelect, monsterSelect);
                attackTurn=1;
                battleRound++;
                healHeroes();
                break;
        }

    }

    if(heroCondition(heroSelect)){
        System.out.println("\nHERO WINS!!!\n=============");
        Player.levelUpHeroes();
    }else{
        System.out.println("\nHERO DEFEATED!!!\nMonster defeated you!");
        System.exit(0);
    }
}

public static boolean heroTurn(int heroSelect, int monsterSelect){
    System.out.println("\nHERO TURN"+"\n=========");

    heroOptions(heroSelect, monsterSelect);

    return true;
}

public static boolean heroOptions(int heroSelect, int monsterSelect){
    int heroOption=0;
    Hero hero = Player.heroes.get(heroSelect);
    Scanner ip = new Scanner(System.in);

    System.out.println("HERO : "+hero.getName()+"  HP : "+hero.getHP());

    System.out.println("HERO SELECT:\n"+"[1] Attack\n"+"[2] Cast spell\n"+"[3] Use potion\n"
            +"[4] Equip Weapon\n"+"[5] Equip Armor\n"+"[6] Exit");

    try{
        System.out.print("Enter : ");
        heroOption = ip.nextInt();
    }catch (Exception e){
        System.out.println("Enter valid option.");
        heroOption = 0;
    }

    switch(heroOption){
        case 1:
            // Attack
            hero.attack(monsterSelect);
            break;
        case 2:
            hero.castSpell(heroSelect, monsterSelect);
            break;
        case 3:
            hero.usePotion(heroSelect);
            break;
        case 4:
            // equip weapon
            hero.equipWeapon(heroSelect);
            break;
        case 5:
            hero.equipArmor(heroSelect);
            break;
        case 6:
            System.out.println("\n=======================================\n");
            System.out.println("Thank You for playing!!\nSee you soon!");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid option.");
            break;
    }

    return true;
}

public static boolean heroCondition(int heroSelect){
    Hero hero = Player.heroes.get(heroSelect);
    if(hero.getHP() > 0)
        return true;
    else{
        //RunGame.board.setBoard(hero.getxPosition(), hero.getyPosition(), '-');
        RunGame.board.getCells().get(hero.getxPosition()).get(hero.getxPosition()).setSymbol("M");
    }

    return false;
}

public static boolean monsterCondition(int monsterSelect){
    Monster monster = FactoryMonster.spawnMonsters.get(monsterSelect);

    if(monster.getHP() <= 0){
    	FactoryMonster.spawnMonsters.remove(monster);
        return false;
    }

    return true;
}

public static void healHeroes(){
    for(Hero hero: Player.heroes){

        if(hero.getHP() > 0){
            hero.setHP((int) (hero.getHP()*1.1));
        }
        if(hero.getMP() > 0){
            hero.setMP((int) (hero.getMP()*1.1));
        }

    }
}


}
