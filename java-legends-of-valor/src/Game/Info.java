package Game;
import java.util.*;

import Character.Hero;
import Character.Player;

import java.io.*;

public class Info {
//    Contains the methods for displaying heroes stats and attributes and equip from inventory.
    public static void infoDisplay(){
        System.out.println("INFO DISPLAY\n============");
        Player.printHeroesDetail();

        int hero = Player.selectHero();
        heroInfoOptions(hero);
    }

    public static boolean heroInfoOptions(int heroSelect){
        int heroOption=0;
        Hero hero = Player.heroes.get(heroSelect);
        Scanner ip = new Scanner(System.in);

        System.out.println("HERO : "+hero.getName()+"  HP : "+hero.getHP());

        System.out.println("HERO SELECT:\n"+"[1] Equip Weapon\n"+"[2] Equip Armor\n"+"[3] Use Potion\n"+"[4] Return");

        try{
            System.out.print("Enter : ");
            heroOption = ip.nextInt();
        }catch (Exception e){
            System.out.println("Enter valid option.");
            heroOption = 0;
        }

        switch(heroOption){
            case 1:
                // equip weapon
                hero.equipWeapon(heroSelect);
                break;
            case 2:
                hero.equipArmor(heroSelect);
                break;
            case 3:
                hero.usePotion(heroSelect);
                break;
            case 4:
                return false;
            default:
                System.out.println("Invalid option.");
                break;
        }
        return true;
    }

}
