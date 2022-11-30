package Character;
import java.io.*;
import java.util.*;

import Factory.FactoryMonster;
import Item.Armory;
import Item.Potions;
import Item.Spells;
import Item.Weapons;
import LevelUp.LevelUpBehaviour;
import LevelUp.PaladinLevelUpBehaviour;
import LevelUp.SorcererLevelUpBehaviour;
import LevelUp.WarriorLevelUpBehaviour;
import Game.RunGame;

public class Hero {
//    	Has the implementation and attributes of the heroes from parsing text files to creation and methods for hero's actions.
	private int MP;
	private int strength;
	private int dexterity;
	private int agility;
	private int gold;
	private int experience;
    private String name;
    private int level;
    private int HP;
    int xPosition;
    int yPosition;
    int lane;
    String displayName;
    

    public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	private ArrayList<Weapons> weaponsInventory;
    private ArrayList<Armory> armoryInventory;
    private ArrayList<Spells> spellsInventory;
    private ArrayList<Potions> potionsInventory;
    Weapons equipWeapon; //todo Selling unequip
    Armory equipArmor;
    
    protected LevelUpBehaviour lu;

    public Hero(String category, String name, int MP, int strength, int agility, int dexterity, int gold, int experience) {
        this.setName(name);
        this.setLevel(1);
        this.setHP(getLevel() * 100);
        this.MP = MP;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.experience = experience;
        this.setWeaponsInventory(new ArrayList<Weapons>());
        this.setArmoryInventory(new ArrayList<Armory>());
        this.setSpellsInventory(new ArrayList<Spells>());
        this.setPotionsInventory(new ArrayList<Potions>());
        this.equipWeapon = null;
        this.equipArmor = null;
        int xPosition = 0;
        int yPosition = 0;
        this.lane=0;
        this.displayName = "H0";
        if (category == "paladin") {
			lu = new PaladinLevelUpBehaviour();
		} else if (category == "warrior") {
			lu = new WarriorLevelUpBehaviour();
		} else if(category == "sorcerer") {
			lu = new SorcererLevelUpBehaviour();
		}
    }
    
    public void levelUp(Hero hero){
		lu.levelUp(hero);
	}
	
	private ArrayList<String> inventory = new ArrayList<String>();
	
	public int getMP() {
		return MP;
	}
	public void setMP(int d) {
		MP = d;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDexterity() {
		return dexterity;
	}
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public ArrayList<String> getInventory() {
		return inventory;
	}
	public void setInventory(ArrayList<String> inventory) {
		this.inventory = inventory;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
    public String getDisplayName() {
        return displayName;
    }


    public boolean equipWeapon(int heroSelect){
        System.out.println("HERO : "+getName()+"\n=================");
        if(Player.heroes.get(heroSelect).getWeaponsInventory().size()==0){
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

            while(weaponSelect<1 || weaponSelect>getWeaponsInventory().size()){
                System.out.println("Input valid Weapon number : ");
                weaponSelect = ip.nextInt();
            }
            weaponSelect--;
        }catch (Exception e){
            System.out.println("Select valid Weapon number.");
            return false;
        }

        equipWeapon = getWeaponsInventory().get(weaponSelect);
        return true;
    }

    public boolean equipArmor(int heroSelect){
        System.out.println("\nHERO : "+getName());
        if(Player.heroes.get(heroSelect).getArmoryInventory().size()==0){
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

            while(armorSelect<1 || armorSelect>getWeaponsInventory().size()){
                System.out.println("Input valid Armor number : ");
                armorSelect = ip.nextInt();
            }
            armorSelect--;
        }catch (Exception e){
            System.out.println("Select valid Armor number.");
            return false;
        }

        equipArmor = getArmoryInventory().get(armorSelect);
        return true;
    }

    public boolean attack(int monsterSelect){
        Random random = new Random();
        // Select which monster to attack
//        int monsterSelect = Monster.selectMonster();
        if (monsterSelect<0)
            return false;

        Monster monster = FactoryMonster.spawnMonsters.get(monsterSelect);

        // Check Monster dodge
        double dodgeChance = monster.dodge * 0.01;
        int randomDodge = random.nextInt(monster.dodge);
        int heroAttack=0;

        if ( randomDodge > dodgeChance ){
            // todo defense
            if (equipWeapon!=null){
                heroAttack = (int) ( (strength+equipWeapon.getDamage())*0.05 - monster.defense*0.05);
            }else{
                heroAttack = (int) ( (strength)*0.05 - monster.defense*0.05);
            }

            monster.HP -= heroAttack;

            System.out.println("\nHero "+name+" attacks Monster "+monster.name
                    +" for damage "+heroAttack);

            // Check if monster is finished
            if(monster.HP <= 0){
                System.out.println("\nMonster "+monster.name+" is finished.");
//                RunGame.board.setBoard(monster.xPosition, monster.yPosition, '-');
                RunGame.board.getCells().get(monster.xPosition).get(monster.yPosition).setSymbol("-");
//                Monster.spawnMonsters.remove(monster);  removing in battle class
            }

        }else{
            System.out.println("\nMonster "+monster.name+" dodged Hero "+name+" attack");
        }

        return true;
    }

    public boolean usePotion(int heroSelect){
        System.out.println("\nHERO : "+getName());
        if(Player.heroes.get(heroSelect).getPotionsInventory().size()==0){
            System.out.println("No potions in inventory.");
            return false;
        }
        return Potions.selectPotion(heroSelect);
    }

    public boolean castSpell(int heroSelect, int monsterSelect){
        Random random = new Random();
        // Select which monster to attack
//        int monsterSelect = Monster.selectMonster();
        if (monsterSelect<0)
            return false;

        return Spells.selectSpell(heroSelect, monsterSelect);
    }
    
    public boolean setPosition(int xPosition, int yPosition, Hero hero){
        // Reset old position
        //RunGame.board.setBoard(this.xPosition, this.yPosition, '-');
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).setSymbol("-");
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).heroLeave(hero);
        
        // New position
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        //RunGame.board.setBoard(this.xPosition, this.yPosition, 'H');
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).setSymbol(displayName);
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).heroEnter(hero);
        return true;
    }

    public boolean teleport(){
        // Teleport the hero to different lane
        System.out.println("Teleport Options:\nSelect lane to teleport to:");
        for (int i=1;i<=3;i++){
            // Skip current lane
            if(i-1==this.lane)
                continue;
            System.out.println(i+". Lane "+i);
        }
        Scanner ip = new Scanner(System.in);
        int newLane;
        int teleportSelect;
        try {
            System.out.print("Enter lane : ");
            newLane = ip.nextInt();
            while(newLane<1 || newLane>3 || newLane==this.lane+1){
                System.out.print("Enter valid lane : ");
                newLane = ip.nextInt();
            }
        }catch(Exception e){
            System.out.println("Invalid Lane selected.");
            return teleport();
        }
        newLane--;
        // Decide where to place the teleported hero. 3 cases.
        // CASE 1
        // If hero in lane, teleport to its adjacent cell or just above cell
        int tHeroX=-1;
        int tHeroY=-1;
        int newX=-1;
        int newY=-1;
        for (Hero tHero: Player.heroes){
            if(tHero.lane == newLane){
                tHeroX = tHero.xPosition;
                tHeroY = tHero.yPosition;
            }
        }
        if(tHeroX!=-1){
            // Set the position for teleport
            System.out.println("Select area to teleport to:\n1. Adjacent to Lane Hero\n2. Above Lane Hero");
            try {
                System.out.print("Enter selection : ");
                teleportSelect = ip.nextInt();
                while(!(teleportSelect==1 || teleportSelect==2)){
                    System.out.print("Enter valid selection : ");
                    teleportSelect = ip.nextInt();
                }
            }catch(Exception e){
                System.out.println("Invalid selection.");
                return teleport();
            }

            switch(teleportSelect){
                case 1:
                    // Adjacent to Lane Hero
                    if(newLane*3 == tHeroY)
                        newY = tHeroY+1;
                    else if( (newLane*3+1) == tHeroY )
                        newY = tHeroY-1;
                    newX= tHeroX;
                    break;
                case 2:
                    // Above Lane Hero
                    if(tHeroX==0){
                        System.out.println("Lane hero still in Nexus. Can't teleport above.");
                        return false;
                    }
                    newY= tHeroY;
                    newX= tHeroX-1;
                    break;
                default:
                    return false;
            }

            // Set the teleported coordinates
            setLane(newLane);
            setPosition(newX, newY, this);
            return true;
        }
        // CASE 2
        // Teleport above monster
        int tMonsterX=-1;
        int tMonsterY=-1;
        for (Monster monster: FactoryMonster.spawnMonsters){
            if(monster.lane == newLane){
                tMonsterX = monster.xPosition;
                tMonsterY = monster.yPosition;
            }
        }
        if(tMonsterX!=-1){
            // Set the position for teleport
            // Above Lane Monster
            if(tMonsterX==0){
                System.out.println("Lane monster still in Nexus. Can't teleport above.");
                return false;
            }
            newY= tMonsterY;
            newX= tMonsterX-1;
            setLane(newLane);
            setPosition(newX,newY,this);
            return true;
        }
        // CASE 3
        // If no hero or monster in lane, teleport to same row as current
        newY = newLane*3;
        newX= tMonsterX;
        setLane(newLane);
        setPosition(newX, newY, this);
        return true;

//        return false;
    }
    
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Weapons> getWeaponsInventory() {
		return weaponsInventory;
	}

	public void setWeaponsInventory(ArrayList<Weapons> weaponsInventory) {
		this.weaponsInventory = weaponsInventory;
	}

	public ArrayList<Armory> getArmoryInventory() {
		return armoryInventory;
	}

	public void setArmoryInventory(ArrayList<Armory> armoryInventory) {
		this.armoryInventory = armoryInventory;
	}

	public ArrayList<Spells> getSpellsInventory() {
		return spellsInventory;
	}

	public void setSpellsInventory(ArrayList<Spells> spellsInventory) {
		this.spellsInventory = spellsInventory;
	}

	public ArrayList<Potions> getPotionsInventory() {
		return potionsInventory;
	}

	public void setPotionsInventory(ArrayList<Potions> potionsInventory) {
		this.potionsInventory = potionsInventory;
	}

}
