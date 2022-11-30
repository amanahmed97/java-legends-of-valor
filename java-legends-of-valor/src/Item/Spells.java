package Item;
import java.io.*;
import java.util.*;

import Character.Hero;
import Character.Monster;
import Character.Player;
import Factory.FactoryMonster;

public class Spells {
//    Has the attributes and values of the spells and their buy, sell, attack methods.
    String name;
    int cost;
    int level;
    int damage;
    int mana;
    public static ArrayList<Spells> spellsList;

    public Spells(String name, int cost, int level, int damage, int mana) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.mana = mana;
    }

    public static void populate() throws IOException {
        spellsList = new ArrayList<Spells>();
        populateFireSpells();
        populateIceSpells();
        populateLightningSpells();
    }

    public static void populateFireSpells() throws IOException {
        String line;
        int ctr = 0;
        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Item/FireSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }catch (Exception e){
            file = new File("./src/gamelib/FireSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 5) {
                ctr++;
                continue;
            }

            spellsList.add(new Spells("FireSpell "+iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]), Integer.parseInt(iarray[4]) ) );
            ctr++;
        }

    }

    public static void populateIceSpells() throws IOException {
        String line;
        int ctr = 0;
        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Item/IceSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }catch (Exception e){
            file = new File("./src/gamelib/IceSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 5) {
                ctr++;
                continue;
            }

            spellsList.add(new Spells("IceSpell "+iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]), Integer.parseInt(iarray[4]) ) );
            ctr++;
        }

    }

    public static void populateLightningSpells() throws IOException {
        String line;
        int ctr = 0;
        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Item/LightningSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }catch (Exception e){
            file = new File("./src/gamelib/LightningSpells.txt");
            br = new BufferedReader(new FileReader(file));
        }

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 5) {
                ctr++;
                continue;
            }

            spellsList.add(new Spells("LightningSpell "+iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]), Integer.parseInt(iarray[4]) ) );
            ctr++;
        }

    }
    public static void printSpellsList() {
        System.out.println("BUY SPELLS\n==========");
        System.out.println("Headers: Name / cost / required level / damage / mana cost");
        for (int j = 0; j < spellsList.size(); j++) {
            Spells spells = spellsList.get(j);
            System.out.println("[" + (j + 1) + "] " + spells.name + "  " + spells.cost + "  " + spells.level + "  " + spells.damage + "  " + spells.mana);
        }
    }

    public static void printHeroSpells(int heroSelect) {
        System.out.println("\nHERO OWNED SPELLS\n"+"=================");
        System.out.println("Headers: Name / cost / required level / damage / mana cost");
        ArrayList<Spells> heroSpells = Player.heroes.get(heroSelect).getSpellsInventory();
        for (int j = 0; j < heroSpells.size(); j++) {
            Spells spell = heroSpells.get(j);
            System.out.println("[" + (j + 1) + "] " + spell.name + "  " + spell.cost + "  " + spell.level
                    + "  " + spell.damage + "  " + spell.mana);
        }
    }

    public static boolean buySpells(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
        printHeroSpells(heroSelect);
        printSpellsList();

        int spellSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            spellSelect = ip.nextInt();

            while(spellSelect<1 || spellSelect>spellsList.size()){
                System.out.println("Input valid Spell number : ");
                spellSelect = ip.nextInt();
            }
            spellSelect--;
        }catch (Exception e){
            System.out.println("Select valid Spell number.");
            return false;
        }

        if ( hero.getGold() < spellsList.get(spellSelect).cost ){
            System.out.println("Not enough gold.");
            return false;
        }
        if ( hero.getLevel() < spellsList.get(spellSelect).level ){
            System.out.println("Not at required level to buy this.");
            return false;
        }

        if ( !hero.getSpellsInventory().contains(spellsList.get(spellSelect)) ) {
            hero.getSpellsInventory().add(spellsList.get(spellSelect));
            hero.setGold(hero.getGold() - spellsList.get(spellSelect).cost);
            System.out.println("Spell bought : "+spellsList.get(spellSelect).name);
            System.out.println("Hero's Current Gold : "+hero.getGold());
            return true;
        }
        else
            System.out.println("Spell already owned!");

        return true;
    }

    public static boolean sellSpells(int heroSelect){
        Hero hero = Player.heroes.get(heroSelect);
        System.out.println("Hero's Gold : "+hero.getGold());
        System.out.println("You will get half the displayed cost of the spell in your inventory, if you sell.");
        if(hero.getSpellsInventory().size()==0){
            System.out.println("No spells in inventory.");
            return false;
        }
        printHeroSpells(heroSelect);

        int spellSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            spellSelect = ip.nextInt();

            while(spellSelect<1 || spellSelect>hero.getSpellsInventory().size()){
                System.out.println("Input valid Spell number : ");
                spellSelect = ip.nextInt();
            }
            spellSelect--;
        }catch (Exception e){
            System.out.println("Select valid Spell number.");
            return false;
        }

        hero.setGold(hero.getGold() + hero.getSpellsInventory().get(spellSelect).cost / 2);
        System.out.println("Spell sold : "+hero.getSpellsInventory().get(spellSelect).name);
        hero.getSpellsInventory().remove(hero.getSpellsInventory().get(spellSelect));
        System.out.println("Hero's Current Gold : "+hero.getGold());

        return true;
    }

    public static boolean selectSpell(int heroSelect, int monsterSelect){
        System.out.println("CHOOSE HERO SPELL TO CAST"+"\n===========================");
        Hero hero = Player.heroes.get(heroSelect);
        printHeroSpells(heroSelect);

        int spellSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            spellSelect = ip.nextInt();

            while(spellSelect<1 || spellSelect>hero.getSpellsInventory().size()){
                System.out.println("Input valid Spell number : ");
                spellSelect = ip.nextInt();
            }
            spellSelect--;
        }catch (Exception e){
            System.out.println("Select valid Spell number.");
            return false;
        }

        Spells spell = hero.getSpellsInventory().get(spellSelect);
        Monster monster = FactoryMonster.spawnMonsters.get(monsterSelect);

        if(hero.getMP()<spell.mana){
            System.out.println("Not enough mana to cast spell");
            return false;
        }

        long spellDamage = spell.damage + (hero.getDexterity()/10000)*spell.damage;
        // spell-wise damage
        if(spell.name.contains("Fire")){
            monster.setDefense((int) (monster.getDefense() - spellDamage));
        }else if(spell.name.contains("Ice")){
            monster.setDamage((int) (monster.getDamage() - spellDamage));
        }else if(spell.name.contains("Lightning")){
            monster.setDodge((int) (monster.getDodge() - spellDamage));
        }

        System.out.println("Hero "+hero.getName()+" cast "+spell.name+" on Monster "+monster.getName());
        hero.getSpellsInventory().remove(spellSelect);

        return true;
    }


}
