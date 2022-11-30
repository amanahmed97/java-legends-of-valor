package Game;

import java.io.*;
import java.util.*;

import Character.Player;
import Character.Hero;
import Item.Armory;
import Item.Potions;
import Item.Spells;
import Item.Weapons;

public class Market {
//    Contains the implementation of the marketplace where heroes can buy and sell inventory.
    public static void enterMarket(Hero hero){
        if (hero.getxPosition() != 0){
            System.out.println("Hero "+hero.getDisplayName()+" "+hero.getName()+" not in Nexus. Can't access market.");
            return;
        }

        System.out.println("\n=====================\n"+"WELCOME TO THE MARKET\n"+"=====================\n");

        int mOption = 0;
        Scanner ip = new Scanner(System.in);

        while(true) {
            // Market Menu
            System.out.println("OPTIONS:\n1. BUY \n2. SELL \n3. EXIT");

            try {
                System.out.print("Enter : ");
                mOption = ip.nextInt();
            } catch (Exception e) {
                System.out.println("Enter valid option.");
                mOption = 0;
            }

            switch (mOption) {
                case 1:
                    buyMarket(hero);
                    break;
                case 2:
                    sellMarket(hero);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }

    public static void buyMarket(Hero hero){
        System.out.println("BUY MENU\n========");

        int buyOption = 0;
        Scanner ip = new Scanner(System.in);

        // Buy Menu
        System.out.println("\nBUY ITEMS:\n1. WEAPONRY \n2. ARMORY \n3. POTIONS \n4. SPELLS \n5. RETURN");

        try{
            System.out.print("Enter : ");
            buyOption = ip.nextInt();
        }catch (Exception e){
            System.out.println("Enter valid option.");
            buyOption = 0;
        }

//        int heroSelect = Player.selectHero();
        int heroSelect = Player.heroes.indexOf(hero);

        switch (buyOption){
            case 1:
                Weapons.buyWeapons(heroSelect);
                break;
            case 2:
                Armory.buyArmory(heroSelect);
                break;
            case 3:
                Potions.buyPotions(heroSelect);
                break;
            case 4:
                Spells.buySpells(heroSelect);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

    public static void sellMarket(Hero hero){
        System.out.println("\nSELL MENU\n========");

        int buyOption = 0;
        Scanner ip = new Scanner(System.in);

        // Buy Menu
        System.out.println("SELL ITEMS:\n1. WEAPONRY \n2. ARMORY \n3. POTIONS \n4. SPELLS \n5. RETURN");

        try{
            System.out.print("Enter : ");
            buyOption = ip.nextInt();
        }catch (Exception e){
            System.out.println("Enter valid option.");
            buyOption = 0;
        }

//        int hero = Player.selectHero();
        int heroSelect = Player.heroes.indexOf(hero);

        switch (buyOption){
            case 1:
                Weapons.sellWeapons(heroSelect);
                break;
            case 2:
                Armory.sellArmory(heroSelect);
                break;
            case 3:
                Potions.sellPotions(heroSelect);
                break;
            case 4:
                Spells.sellSpells(heroSelect);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

}