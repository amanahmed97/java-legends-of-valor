package Item;
import java.io.*;
import java.util.*;

import Player;
import Character.HeroType;

public class Potions {
//    Has the attributes and values of the potions and their buy, sell, consume methods.
    String name;
    int cost;
    int level;
    int increase;
    String attribute;
    public static ArrayList<Potions> potionsList;

    public Potions(String name, int cost, int level, int increase, String attribute) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.increase = increase;
        this.attribute = attribute;
    }

    public static void populate() throws IOException {
        potionsList = new ArrayList<Potions>();

        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file = new File("./src/gamelib/Potions.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 5) {
                ctr++;
                continue;
            }

            potionsList.add(new Potions(iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]), iarray[4] ) );
            ctr++;
        }

    }

    public static void printPotionsList() {
        System.out.println("\nPOTIONS LIST\n"+"============");
        System.out.println("Headers : Name / cost / required level / attribute increase / attribute affected");
        for (int j = 0; j < potionsList.size(); j++) {
            Potions potions = potionsList.get(j);
            System.out.println("[" + (j + 1) + "] " + potions.name + "  " + potions.cost + "  " + potions.level
                    + "  " + potions.increase+ "  " + potions.attribute);
        }
    }

    public static void printHeroPotions(int heroSelect) {
        System.out.println("\nHERO OWNED POTIONS\n"+"============");
        System.out.println("Headers : Name / cost / required level / attribute increase / attribute affected");
        ArrayList<Potions> heroPotions = Player.heroes.get(heroSelect).potionsInventory;
        for (int j = 0; j < heroPotions.size(); j++) {
            Potions potion = heroPotions.get(j);
            System.out.println("[" + (j + 1) + "] " + potion.name + "  " + potion.cost + "  " + potion.level
                    + "  " + potion.increase + "  " + potion.attribute);
        }
    }

    public static boolean buyPotions(int heroSelect){
        HeroType hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.gold);
        printHeroPotions(heroSelect);
        printPotionsList();

        int potionSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            potionSelect = ip.nextInt();

            while(potionSelect<1 || potionSelect>potionsList.size()){
                System.out.println("Input valid Potion number : ");
                potionSelect = ip.nextInt();
            }
            potionSelect--;
        }catch (Exception e){
            System.out.println("Select valid Potion number.");
            return false;
        }

        if ( hero.gold < potionsList.get(potionSelect).cost ){
            System.out.println("Not enough gold.");
            return false;
        }
        if ( hero.level < potionsList.get(potionSelect).level ){
            System.out.println("Not at required level to buy this.");
            return false;
        }

        if ( !hero.potionsInventory.contains(potionsList.get(potionSelect)) ) {
            hero.potionsInventory.add(potionsList.get(potionSelect));
            hero.gold -= potionsList.get(potionSelect).cost;
            System.out.println("Potion bought : "+potionsList.get(potionSelect).name);
            System.out.println("Hero's Current Gold : "+hero.gold);
            return true;
        }
        else
            System.out.println("Potion already owned!");

        return true;
    }

    public static boolean sellPotions(int heroSelect){
        HeroType hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.gold);
        System.out.println("You will get half the displayed cost of the potion in your inventory, if you sell.");
        if(hero.potionsInventory.size()==0){
            System.out.println("No potions in inventory.");
            return false;
        }
        printHeroPotions(heroSelect);

        int potionSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            potionSelect = ip.nextInt();

            while(potionSelect<1 || potionSelect>hero.potionsInventory.size()){
                System.out.println("Input valid Potion number : ");
                potionSelect = ip.nextInt();
            }
            potionSelect--;
        }catch (Exception e){
            System.out.println("Select valid Potion number.");
            return false;
        }

        hero.gold += hero.potionsInventory.get(potionSelect).cost / 2;
        System.out.println("Potion sold : "+hero.potionsInventory.get(potionSelect).name);
        hero.potionsInventory.remove(hero.potionsInventory.get(potionSelect));
        System.out.println("Hero's Current Gold : "+hero.gold);

        return true;
    }

    public static boolean selectPotion(int heroSelect){
        System.out.println("CHOOSE HERO POTION TO USE"+"\n===========================");
        HeroType hero = Player.heroes.get(heroSelect);
        printHeroPotions(heroSelect);

        int potionSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            potionSelect = ip.nextInt();

            while(potionSelect<1 || potionSelect>hero.potionsInventory.size()){
                System.out.println("Input valid Potion number : ");
                potionSelect = ip.nextInt();
            }
            potionSelect--;
        }catch (Exception e){
            System.out.println("Select valid Potion number.");
            return false;
        }

        Potions potion = hero.potionsInventory.get(potionSelect);

        // attribute-wise increase
        if(potion.attribute.contains("Health")){
            hero.HP += potion.increase;
        }
        if(potion.attribute.contains("Strength")){
            hero.strength += potion.increase;
        }
        if(potion.attribute.contains("Mana")){
            hero.MP += potion.increase;
        }
        if(potion.attribute.contains("Agility")){
            hero.agility += potion.increase;
        }
        if(potion.attribute.contains("Dexterity")){
            hero.dexterity += potion.increase;
        }

        hero.potionsInventory.remove(potionSelect);
        System.out.println("Potion "+potion.name+" consumed.\n");

        return true;
    }

}
