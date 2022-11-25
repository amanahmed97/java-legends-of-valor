package Item;
import java.io.*;
import java.util.*;

import Player;
import Character.HeroType;

public class Weapons {
//    	Has the attributes and values of the weapons and their buy, sell, attack methods.
    String name;
    int cost;
    int level;
    private int damage;
    public static ArrayList<Weapons> weaponsList;

    public Weapons(String name, int cost, int level, int damage) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.setDamage(damage);
    }

    public static void populate() throws IOException {
        weaponsList = new ArrayList<Weapons>();

        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file = new File("./src/gamelib/Weaponry.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 4) {
                ctr++;
                continue;
            }

            weaponsList.add(new Weapons(iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]) ) );
            ctr++;
        }
        
    }

    public static void printWeaponsList() {
        System.out.println("\nWEAPONS LIST\n"+"============");
        System.out.println("Headers : Name / cost / required level / damage reduction");
        for (int j = 0; j < weaponsList.size(); j++) {
            Weapons weapon = weaponsList.get(j);
            System.out.println("[" + (j + 1) + "] " + weapon.name + "  " + weapon.cost + "  " + weapon.level + "  " + weapon.getDamage());
        }
    }

    public static void printHeroWeapons(int heroSelect) {
        System.out.println("\nHERO OWNED WEAPONS\n"+"==================");
        System.out.println("Headers : Name / cost / required level / damage reduction");
        ArrayList<Weapons> heroWeapons = Player.heroes.get(heroSelect).weaponsInventory;
        for (int j = 0; j < heroWeapons.size(); j++) {
            Weapons weapon = heroWeapons.get(j);
            System.out.println("[" + (j + 1) + "] " + weapon.name + "  " + weapon.cost + "  " + weapon.level + "  " + weapon.getDamage());
        }
    }

    public static boolean buyWeapons(int heroSelect){
        HeroType hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.gold);
        printHeroWeapons(heroSelect);
        printWeaponsList();

        int weaponSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            weaponSelect = ip.nextInt();

            while(weaponSelect<1 || weaponSelect>weaponsList.size()){
                System.out.println("Input valid Weapon number : ");
                weaponSelect = ip.nextInt();
            }
            weaponSelect--;
        }catch (Exception e){
            System.out.println("Select valid Weapon number.");
            return false;
        }

        if ( hero.gold < weaponsList.get(weaponSelect).cost ){
            System.out.println("Not enough gold.");
            return false;
        }

        if ( !hero.weaponsInventory.contains(weaponsList.get(weaponSelect)) ) {
            hero.weaponsInventory.add(weaponsList.get(weaponSelect));
            hero.gold -= weaponsList.get(weaponSelect).cost;
            System.out.println("Weapon bought : "+weaponsList.get(weaponSelect).name);
            System.out.println("Hero's Current Gold : "+hero.gold);
            return true;
        }
        else
            System.out.println("Weapon already owned!");

        return true;
    }

    public static boolean sellWeapons(int heroSelect){
        HeroType hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.gold);
        System.out.println("You will get half the displayed cost of the weapons in your inventory, if you sell.");
        if(hero.weaponsInventory.size()==0){
            System.out.println("No weapons in inventory.");
            return false;
        }

        printHeroWeapons(heroSelect);
        //todo check equipWeapon
        int weaponSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            weaponSelect = ip.nextInt();

            while(weaponSelect<1 || weaponSelect>hero.weaponsInventory.size()){
                System.out.println("Input valid Weapon number : ");
                weaponSelect = ip.nextInt();
            }
            weaponSelect--;
        }catch (Exception e){
            System.out.println("Select valid Weapon number.");
            return false;
        }

        hero.gold += hero.weaponsInventory.get(weaponSelect).cost / 2;
        System.out.println("Weapon sold : "+hero.weaponsInventory.get(weaponSelect).name);
        hero.weaponsInventory.remove(hero.weaponsInventory.get(weaponSelect));
        System.out.println("Hero's Current Gold : "+hero.gold);

        return true;
    }

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


}
