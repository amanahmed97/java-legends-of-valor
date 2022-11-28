package Item;
import java.io.*;
import java.util.*;

import Character.Hero;
import Character.Player;

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
        ArrayList<Potions> heroPotions = Player.heroes.get(heroSelect).getPotionsInventory();
        for (int j = 0; j < heroPotions.size(); j++) {
            Potions potion = heroPotions.get(j);
            System.out.println("[" + (j + 1) + "] " + potion.name + "  " + potion.cost + "  " + potion.level
                    + "  " + potion.increase + "  " + potion.attribute);
        }
    }

    public static boolean buyPotions(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
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

        if ( hero.getGold() < potionsList.get(potionSelect).cost ){
            System.out.println("Not enough gold.");
            return false;
        }
        if ( hero.getLevel() < potionsList.get(potionSelect).level ){
            System.out.println("Not at required level to buy this.");
            return false;
        }

        if ( !hero.getPotionsInventory().contains(potionsList.get(potionSelect)) ) {
            hero.getPotionsInventory().add(potionsList.get(potionSelect));
            hero.setGold(hero.getGold() - potionsList.get(potionSelect).cost);
            System.out.println("Potion bought : "+potionsList.get(potionSelect).name);
            System.out.println("Hero's Current Gold : "+hero.getGold());
            return true;
        }
        else
            System.out.println("Potion already owned!");

        return true;
    }

    public static boolean sellPotions(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
        System.out.println("You will get half the displayed cost of the potion in your inventory, if you sell.");
        if(hero.getPotionsInventory().size()==0){
            System.out.println("No potions in inventory.");
            return false;
        }
        printHeroPotions(heroSelect);

        int potionSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            potionSelect = ip.nextInt();

            while(potionSelect<1 || potionSelect>hero.getPotionsInventory().size()){
                System.out.println("Input valid Potion number : ");
                potionSelect = ip.nextInt();
            }
            potionSelect--;
        }catch (Exception e){
            System.out.println("Select valid Potion number.");
            return false;
        }

        hero.setGold(hero.getGold() + hero.getPotionsInventory().get(potionSelect).cost / 2);
        System.out.println("Potion sold : "+hero.getPotionsInventory().get(potionSelect).name);
        hero.getPotionsInventory().remove(hero.getPotionsInventory().get(potionSelect));
        System.out.println("Hero's Current Gold : "+hero.getGold());

        return true;
    }

    public static boolean selectPotion(int heroSelect){
        System.out.println("CHOOSE HERO POTION TO USE"+"\n===========================");
        Hero hero = Player.heroes.get(heroSelect);
        printHeroPotions(heroSelect);

        int potionSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            potionSelect = ip.nextInt();

            while(potionSelect<1 || potionSelect>hero.getPotionsInventory().size()){
                System.out.println("Input valid Potion number : ");
                potionSelect = ip.nextInt();
            }
            potionSelect--;
        }catch (Exception e){
            System.out.println("Select valid Potion number.");
            return false;
        }

        Potions potion = hero.getPotionsInventory().get(potionSelect);

        // attribute-wise increase
        if(potion.attribute.contains("Health")){
            hero.setHP(hero.getHP() + potion.increase);
        }
        if(potion.attribute.contains("Strength")){
            hero.setStrength(hero.getStrength() + potion.increase);
        }
        if(potion.attribute.contains("Mana")){
            hero.setMP(hero.getMP() + potion.increase);
        }
        if(potion.attribute.contains("Agility")){
            hero.setAgility(hero.getAgility() + potion.increase);
        }
        if(potion.attribute.contains("Dexterity")){
            hero.setDexterity(hero.getDexterity() + potion.increase);
        }

        hero.getPotionsInventory().remove(potionSelect);
        System.out.println("Potion "+potion.name+" consumed.\n");

        return true;
    }

}
