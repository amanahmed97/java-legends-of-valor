package Character;
import java.io.*;
import java.util.*;

import Factory.FactoryMonster;
import Game.RunGame;


public class Monster {
//    Has the implementation and attributes of the monsters from parsing text files to creation and methods for monster's actions.
    String name;
    int level;
    int HP;
    int damage;
    int defense;
    int dodge;
    int xPosition;
    int yPosition;
    int lane;
    
    

    public Monster(String name, int level, int damage, int defense, int dodge) {
        this.name = name;
        this.level = level;
        this.HP= level*100;
        this.setDamage(damage);
        this.defense = defense;
        this.dodge = dodge;
        int xPosition = 0;
        int yPosition = 0;
        this.lane = 0;
    }

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

	public static boolean monsterTurn(int heroSelect, int monsterSelect){
        Random random = new Random();
        Monster monster = FactoryMonster.spawnMonsters.get(monsterSelect);

            Hero hero = Player.heroes.get(heroSelect);
            // Check Hero agility to dodge
            double dodgeChance = hero.getAgility() * 0.002;
            int randomDodge = random.nextInt(hero.getAgility());
            double attackDamage;

            if ( randomDodge > dodgeChance ){
                // todo armor
                if(hero.equipArmor != null)
                    attackDamage = (monster.damage*0.02*monster.level - hero.equipArmor.getDamage()*0.05*hero.getLevel());
                else
                    attackDamage = (monster.damage*0.02*monster.level);

                hero.setHP((int) (hero.getHP() - attackDamage));
                System.out.println("\nMonster "+monster.name+" attacks Hero "+hero.getName()
                        +" for damage "+attackDamage);
            }else{
                System.out.println("\nHero "+hero.getName()+" dodged Monster "+monster.name+" attack");
            }


        return true;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public static int selectMonster(){
        System.out.println("Select which monster to attack : ");
        FactoryMonster.printSpawnMonsters();

        int monsterSelect=0;
        Scanner ip = new Scanner(System.in);
        try {
            System.out.print("Enter monster to attack : ");
            monsterSelect = ip.nextInt();

            while(monsterSelect<1 || monsterSelect>FactoryMonster.spawnMonsters.size()){
                System.out.println("Input valid Monster number : ");
                monsterSelect = ip.nextInt();
            }
            monsterSelect--;
            return monsterSelect;
        }catch (Exception e){
            System.out.println("Select valid Monster number.");
            return -1;
        }
    }
	
	public boolean moveNexus(){
        // Reset old position
        //RunGame.board.setBoard(this.xPosition, this.yPosition, '-');
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).setSymbol("-");
        // New position
        if(xPosition>0)
            xPosition--;
        //RunGame.board.setBoard(this.xPosition, this.yPosition, 'M');
        RunGame.board.getCells().get(this.xPosition).get(this.yPosition).setSymbol("M");

        return true;
    }


}
