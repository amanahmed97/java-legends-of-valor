import java.io.*;
import java.util.*;

public class HeroType {
//    	Has the implementation and attributes of the heroes from parsing text files to creation and methods for hero's actions.
    String name;
    int level;
    int HP;
    int MP;
    int strength;
    int dexterity;
    int agility;
    int gold;
    int experience;
    int xPosition;
    int yPosition;
    int lane;

    ArrayList<Weapons> weaponsInventory;
    ArrayList<Armory> armoryInventory;
    ArrayList<Spells> spellsInventory;
    ArrayList<Potions> potionsInventory;
    public static ArrayList<HeroType> heroList;
    Weapons equipWeapon; //todo Selling unequip
    Armory equipArmor;

    public HeroType(String name, int MP, int strength, int agility, int dexterity, int gold, int experience) {
        this.name = name;
        this.level = 1;
        this.HP = level * 100;
        this.MP = MP;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.experience = experience;
        this.weaponsInventory = new ArrayList<Weapons>();
        this.armoryInventory = new ArrayList<Armory>();
        this.spellsInventory = new ArrayList<Spells>();
        this.potionsInventory = new ArrayList<Potions>();
        this.equipWeapon = null;
        this.equipArmor = null;
        int xPosition = 0;
        int yPosition = 0;
        this.lane=0;
    }

    public static void populate() throws IOException {
        heroList = new ArrayList<HeroType>();
        populateWarriors();
        populatePaladins();
        populateSorcerers();
    }

    public boolean setPosition(int xPosition, int yPosition){
        // Reset old position
        RunGame.board.setBoard(this.xPosition, this.yPosition, '-');
        // New position
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        RunGame.board.setBoard(this.xPosition, this.yPosition, 'H');

        return true;
    }

    public static void populateWarriors() throws IOException {
        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file = new File("./src/gamelib/Warriors.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 7) {
                ctr++;
                continue;
            }

            heroList.add(new HeroType("Warrior " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }

    }

    public static void populatePaladins() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file = new File("./src/gamelib/Paladins.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);
            // Skip the first header line
            if (ctr == 0 || iarray.length < 4) {
                ctr++;
                continue;
            }
            heroList.add(new HeroType("Paladin " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }

    }

    public static void populateSorcerers() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file = new File("./src/gamelib/Sorcerers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);
            // Skip the first header line
            if (ctr == 0 || iarray.length < 4) {
                ctr++;
                continue;
            }

            heroList.add(new HeroType("Sorcerer " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }
    }

    public static void printHeroList() {
        System.out.println("Headers : Name / mana / strength / agility / dexterity / starting money / starting experience");
        for (int j = 0; j < heroList.size(); j++) {
            HeroType hero = heroList.get(j);
            System.out.println("[" + (j + 1) + "] " + hero.name + "  " + hero.MP + "  " + hero.strength + "  " + hero.agility + "  " + hero.dexterity + "  " + hero.gold + "  " + hero.experience);
        }
    }

    public boolean equipWeapon(int heroSelect){
        System.out.println("HERO : "+name+"\n=================");
        if(Player.heroes.get(heroSelect).weaponsInventory.size()==0){
            System.out.println("No weapons in inventory.");
            return false;
        }
        System.out.println("CHOOSE HERO WEAPON TO EQUIP"+"\n============================");

        Weapons.printHeroWeapons(heroSelect);

        int weaponSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            weaponSelect = ip.nextInt();

            while(weaponSelect<1 || weaponSelect>weaponsInventory.size()){
                System.out.println("Input valid Weapon number : ");
                weaponSelect = ip.nextInt();
            }
            weaponSelect--;
        }catch (Exception e){
            System.out.println("Select valid Weapon number.");
            return false;
        }

        equipWeapon = weaponsInventory.get(weaponSelect);
        return true;
    }

    public boolean equipArmor(int heroSelect){
        System.out.println("\nHERO : "+name);
        if(Player.heroes.get(heroSelect).armoryInventory.size()==0){
            System.out.println("No armor in inventory.");
            return false;
        }
        System.out.println("CHOOSE HERO ARMOR TO EQUIP"+"\n===========================");

        Armory.printHeroArmory(heroSelect);

        int armorSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            armorSelect = ip.nextInt();

            while(armorSelect<1 || armorSelect>weaponsInventory.size()){
                System.out.println("Input valid Armor number : ");
                armorSelect = ip.nextInt();
            }
            armorSelect--;
        }catch (Exception e){
            System.out.println("Select valid Armor number.");
            return false;
        }

        equipArmor = armoryInventory.get(armorSelect);
        return true;
    }

    public boolean attack(){
        Random random = new Random();
        // Select which monster to attack
        int monsterSelect = Monster.selectMonster();
        if (monsterSelect<0)
            return false;

        Monster monster = Monster.spawnMonsters.get(monsterSelect);

        // Check Monster dodge
        double dodgeChance = monster.dodge * 0.01;
        int randomDodge = random.nextInt(monster.dodge);
        int heroAttack=0;

        if ( randomDodge > dodgeChance ){
            // todo defense
            if (equipWeapon!=null){
                heroAttack = (int) ( (strength+equipWeapon.damage)*0.05 - monster.defense*0.05);
            }else{
                heroAttack = (int) ( (strength)*0.05 - monster.defense*0.05);
            }

            monster.HP -= heroAttack;

            System.out.println("\nHero "+name+" attacks Monster "+monster.name
                    +" for damage "+heroAttack);

            // Check if monster is finished
            if(monster.HP <= 0){
                System.out.println("\nMonster "+monster.name+" is finished.");
                RunGame.board.setBoard(monster.xPosition, monster.yPosition, '-');
                Monster.spawnMonsters.remove(monster);
            }

        }else{
            System.out.println("\nMonster "+monster.name+" dodged Hero "+name+" attack");
        }

        return true;
    }

    public boolean usePotion(int heroSelect){
        System.out.println("\nHERO : "+name);
        if(Player.heroes.get(heroSelect).potionsInventory.size()==0){
            System.out.println("No potions in inventory.");
            return false;
        }
        return Potions.selectPotion(heroSelect);
    }

    public boolean castSpell(int heroSelect){
        Random random = new Random();
        // Select which monster to attack
        int monsterSelect = Monster.selectMonster();
        if (monsterSelect<0)
            return false;

        return Spells.selectSpell(heroSelect, monsterSelect);
    }

}
