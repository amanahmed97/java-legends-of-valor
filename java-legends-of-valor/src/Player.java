import java.io.IOException;
import java.util.*;

import Character.HeroType;
import Item.Armory;
import Item.Potions;
import Item.Spells;
import Item.Weapons;

class Player {
//    Contains the Player heroes and attributes, methods related to Player heroes.
    String name;
    int team = 0;
    int score = 0;
    String playerMarker;
    int xPosition = 0;
    int yPosition = 0;
    public static ArrayList<HeroType> heroes;

    public Player(String name, int team) {
        this.name = name;
        this.team = team;
        this.xPosition = 0;
        this.yPosition = 0;
//        RunGame.board.setBoard(this.xPosition, this.yPosition, 'H');
        heroes = new ArrayList<HeroType>();
    }

    public Player(String name, int team, String playerMarker) {
        this.name = name;
        this.team = team;
        this.playerMarker = playerMarker;
        this.xPosition = 0;
        this.yPosition = 0;
//        RunGame.board.setBoard(this.xPosition, this.yPosition, 'H');
        heroes = new ArrayList<HeroType>();
    }

    public int scoreUpdate(int s) {
        score += s;
        return score;
    }

    public int getScore() {
        return score;
    }

    public boolean setPosition(int xPosition, int yPosition){
        // Reset old position
//        RunGame.board.setBoard(this.xPosition, this.yPosition, '-');
        // New position
        this.xPosition = xPosition;
        this.yPosition = yPosition;
//        RunGame.board.setBoard(this.xPosition, this.yPosition, 'H');

        return true;
    }

    public static int getNumberHeroes() {
        // Default set to 1 player Hero
        int numberHeroes = 1;
        Scanner ip = new Scanner(System.in);

        System.out.print("Enter number of heroes to play - Max 3 : ");
        try {
            numberHeroes = ip.nextInt();
            while (numberHeroes<1 || numberHeroes>3) {
                System.out.println("Enter value between 1 to 3 : ");
                numberHeroes = ip.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Enter valid value.");
            numberHeroes = getNumberHeroes();
        }

        return numberHeroes;
    }

    public static void heroSet(int numberHeroes) throws IOException {
        HeroType.populate();

        Scanner ip = new Scanner(System.in);
        int heroSelect=0;

        for (int i = 0; i < numberHeroes; i++) {
            // Player Information input
            System.out.println("Choose Hero " + (i + 1) + " :");
            HeroType.printHeroList();
            System.out.print("Enter Hero Number : ");
            try {
                heroSelect = ip.nextInt();

                while(heroSelect<1 || heroSelect>HeroType.heroList.size()){
                    System.out.println("Input valid Hero number : ");
                    heroSelect = ip.nextInt();
                }
                heroes.add(HeroType.heroList.get(heroSelect-1));

            }catch (Exception e){
                System.out.println("Select valid Hero number.");
                heroSet(numberHeroes);
            }
        }
        printHeroes();
    }

    public static void printHeroes(){
        System.out.println("YOUR HEROES :\n=============");
        for (int i=0; i<heroes.size();i++)
            System.out.println("["+(i+1)+"] "+heroes.get(i).name+" HP : "+heroes.get(i).HP);
    }

    public static int selectHero(){
        printHeroes();

        int heroSelect=0;
        Scanner ip = new Scanner(System.in);

        try {
            System.out.print("Enter selection : ");
            heroSelect = ip.nextInt();

            while(heroSelect<1 || heroSelect>heroes.size()){
                System.out.println("Input valid Hero number : ");
                heroSelect = ip.nextInt();
            }

        }catch (Exception e){
            System.out.println("Select valid Hero number.");
            heroSelect = selectHero()+1;
        }

        return heroSelect-1;
    }

    public static void levelUpHeroes(){
        // loop through heroes.
        for(HeroType hero: Player.heroes){
            // add experience points for number of monsters defeated, which is same as number of heroes
            hero.experience += Player.heroes.size() * 2;
            // for heroes who did not faint, gold gain
            if (hero.HP>0)
                hero.gold = hero.level * 100;

            // check experience points
            if(hero.experience > hero.level*10){
                hero.level++;
                // increase hero stats
                hero.HP = hero.level*100;
                hero.MP = (int) (hero.MP * 1.1);

                // level up favoured skills
                if(hero.name.contains("Warrior")){
                    hero.strength = (int) (hero.strength*1.1);
                    hero.agility = (int) (hero.agility*1.1);
                    hero.dexterity = (int) (hero.dexterity*1.05);
                } else if (hero.name.contains("Paladin")) {
                    hero.strength = (int) (hero.strength*1.1);
                    hero.agility = (int) (hero.agility*1.05);
                    hero.dexterity = (int) (hero.dexterity*1.1);
                } else if (hero.name.contains("Sorcerer")) {
                    hero.strength = (int) (hero.strength*1.05);
                    hero.agility = (int) (hero.agility*1.1);
                    hero.dexterity = (int) (hero.dexterity*1.1);
                }

                System.out.println("HERO "+hero.name+" Levels Up!!");
            }
        }

    }

    public static void printHeroesDetail(){
        System.out.println("YOUR HEROES :\n=============");
        for (int i=0; i<heroes.size();i++) {
            System.out.println("\n[" + (i + 1) + "] " + heroes.get(i).name);
            System.out.println("    HP : "+heroes.get(i).HP);
            System.out.println("    MP : "+heroes.get(i).MP);
            System.out.println("    Level : "+heroes.get(i).level);
            System.out.println("    Strength : "+heroes.get(i).strength);
            System.out.println("    Dexterity : "+heroes.get(i).MP);
            System.out.println("    Agility : "+heroes.get(i).agility);
            System.out.println("    Gold : "+heroes.get(i).gold);
            System.out.println("    Experience : "+heroes.get(i).experience);
            if(heroes.get(i).equipWeapon != null)
                System.out.println("    Equipped Weapon : "+heroes.get(i).equipWeapon.name);
            else
                System.out.println("    Equipped Weapon : "+heroes.get(i).equipWeapon);
            if(heroes.get(i).equipArmor != null)
                System.out.println("    Equipped Armor : "+heroes.get(i).equipArmor.name);
            else
                System.out.println("    Equipped Armor : "+heroes.get(i).equipArmor);
            System.out.println("    Weapons Inventory : ");Weapons.printHeroWeapons(i);
            System.out.println("    Armor Inventory : ");Armory.printHeroArmory(i);
            System.out.println("    Spells Inventory : ");Spells.printHeroSpells(i);
            System.out.println("    Potions Inventory : ");Potions.printHeroPotions(i);
        }
    }

}