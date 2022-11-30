package Item;
import java.io.*;
import java.util.*;

import Character.Hero;
import Character.Player;

public class Armory {
//    Has the attributes and values of the armors and their buy, sell, equip methods.
    public String name;
    int cost;
    int level;
    private int damage;
    public static ArrayList<Armory> armoryList;

    public Armory(String name, int cost, int level, int damage) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.setDamage(damage);
    }

    public static void populate() throws IOException {
        armoryList = new ArrayList<Armory>();

        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Item/Armory.txt");
            br = new BufferedReader(new FileReader(file));
        }catch (Exception e){
            file = new File("./src/gamelib/Armory.txt");
            br = new BufferedReader(new FileReader(file));
        }

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 4) {
                ctr++;
                continue;
            }

            armoryList.add(new Armory(iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]) ) );
            ctr++;
        }

    }

    public static void printArmoryList() {
        System.out.println("\nARMORY LIST\n"+"============");
        System.out.println("Headers : Name / cost / required level / damage reduction");
        for (int j = 0; j < armoryList.size(); j++) {
            Armory armory = armoryList.get(j);
            System.out.println("[" + (j + 1) + "] " + armory.name + "  " + armory.cost + "  " + armory.level + "  " + armory.getDamage());
        }
    }

    public static void printHeroArmory(int heroSelect) {
        System.out.println("\nHERO OWNED ARMORY\n"+"============");
        System.out.println("Headers : Name / cost / required level / damage reduction");
        ArrayList<Armory> heroArmory = Player.heroes.get(heroSelect).getArmoryInventory();
        for (int j = 0; j < heroArmory.size(); j++) {
            Armory armor = heroArmory.get(j);
            System.out.println("[" + (j + 1) + "] " + armor.name + "  " + armor.cost + "  " + armor.level + "  " + armor.getDamage());
        }
    }

    public static boolean buyArmory(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
        printHeroArmory(heroSelect);
        printArmoryList();

        int armorSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            armorSelect = ip.nextInt();

            while(armorSelect<1 || armorSelect>armoryList.size()){
                System.out.println("Input valid Armory number : ");
                armorSelect = ip.nextInt();
            }
            armorSelect--;
        }catch (Exception e){
            System.out.println("Select valid Armory number.");
            return false;
        }

        if ( hero.getGold() < armoryList.get(armorSelect).cost ){
            System.out.println("Not enough gold.");
            return false;
        }

        if ( !hero.getArmoryInventory().contains(armoryList.get(armorSelect)) ) {
            hero.getArmoryInventory().add(armoryList.get(armorSelect));
            hero.setGold(hero.getGold() - armoryList.get(armorSelect).cost);
            System.out.println("Armory bought : "+armoryList.get(armorSelect).name);
            System.out.println("Hero's Current Gold : "+hero.getGold());
            return true;
        }
        else
            System.out.println("Armory already owned!");

        return true;
    }

    public static boolean sellArmory(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
        System.out.println("You will get half the displayed cost of the armory in your inventory, if you sell.");
        if(hero.getArmoryInventory().size()==0){
            System.out.println("No armor in inventory.");
            return false;
        }

        printHeroArmory(heroSelect);

        int armorSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            armorSelect = ip.nextInt();

            while(armorSelect<1 || armorSelect>hero.getArmoryInventory().size()){
                System.out.println("Input valid Armory number : ");
                armorSelect = ip.nextInt();
            }
            armorSelect--;
        }catch (Exception e){
            System.out.println("Select valid Armory number.");
            return false;
        }

        hero.setGold(hero.getGold() + hero.getArmoryInventory().get(armorSelect).cost / 2);
        System.out.println("Armory sold : "+hero.getArmoryInventory().get(armorSelect).name);
        hero.getArmoryInventory().remove(hero.getArmoryInventory().get(armorSelect));
        System.out.println("Hero's Current Gold : "+hero.getGold());

        return true;
    }

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
