package Character;
import java.io.IOException;
import java.util.*;

import Factory.FactoryHero;
import Item.Armory;
import Item.Potions;
import Item.Spells;
import Item.Weapons;
import Game.RunGame;

public class Player {
//  Contains the Player heroes and attributes, methods related to Player heroes.
  String name;
  int team = 0;
  int score = 0;
  String playerMarker;
//  int xPosition = 0;
//  int yPosition = 0;
  public static ArrayList<Hero> heroes;
  public static ArrayList<Monster> monsters;

  public Player(String name, int team) {
      this.name = name;
      this.team = team;
      heroes = new ArrayList<Hero>();
  }

  public Player(String name, int team, String playerMarker) {
      this.name = name;
      this.team = team;
      this.playerMarker = playerMarker;
      heroes = new ArrayList<Hero>();
  }

  public int scoreUpdate(int s) {
      score += s;
      return score;
  }

  public int getScore() {
      return score;
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
      FactoryHero.populateHeroes();

      Scanner ip = new Scanner(System.in);
      int heroSelect=0;

      for (int i = 0; i < numberHeroes; i++) {
          // Player Information input
          System.out.println("Choose Hero " + (i + 1) + " :");
          FactoryHero.printHeroList();
          System.out.print("Enter Hero Number : ");
          try {
              heroSelect = ip.nextInt();

              while(heroSelect<1 || heroSelect>FactoryHero.heroList.size()){
                  System.out.println("Input valid Hero number : ");
                  heroSelect = ip.nextInt();
              }
              heroes.add(FactoryHero.heroList.get(heroSelect-1));

          }catch (Exception e){
              System.out.println("Select valid Hero number.");
              heroSet(numberHeroes);
          }
      }

      // Set starting position for the heroes
      for (int i=0; i<heroes.size();i++) {
          Hero hero = heroes.get(i);
          hero.xPosition = 0;
          hero.yPosition = i * 3;
          //RunGame.board.setBoard(hero.xPosition, hero.yPosition, 'H');
          RunGame.board.getCells().get(hero.xPosition).get(hero.yPosition).setSymbol("H");
          hero.lane = i;
      }

      printHeroes();
  }

  public static void printHeroes(){
      System.out.println("YOUR HEROES :\n=============");
      for (int i=0; i<heroes.size();i++)
          System.out.println("["+(i+1)+"] "+heroes.get(i).getName()+" HP : "+heroes.get(i).getHP());
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
        for(Hero hero: Player.heroes){
            // add experience points for number of monsters defeated, which is same as number of heroes
        	hero.levelUp(hero);
//            hero.setExperience(hero.getExperience() + Player.heroes.size() * 2);
//            // for heroes who did not faint, gold gain
//            if (hero.getHP()>0)
//                hero.setGold(hero.getLevel() * 100);
//
//            // check experience points
//            if(hero.getExperience() > hero.getLevel()*10){
//                hero.setLevel(hero.getLevel() + 1);
//                // increase hero stats
//                hero.setHP(hero.getLevel()*100);
//                hero.setMP((int) (hero.getMP() * 1.1));
//
//                // level up favoured skills
//                if(hero.getName().contains("Warrior")){
//                    hero.setStrength((int) (hero.getStrength()*1.1));
//                    hero.setAgility((int) (hero.getAgility()*1.1));
//                    hero.setDexterity((int) (hero.getDexterity()*1.05));
//                } else if (hero.getName().contains("Paladin")) {
//                    hero.setStrength((int) (hero.getStrength()*1.1));
//                    hero.setAgility((int) (hero.getAgility()*1.05));
//                    hero.setDexterity((int) (hero.getDexterity()*1.1));
//                } else if (hero.getName().contains("Sorcerer")) {
//                    hero.setStrength((int) (hero.getStrength()*1.05));
//                    hero.setAgility((int) (hero.getAgility()*1.1));
//                    hero.setDexterity((int) (hero.getDexterity()*1.1));
//                }

//                System.out.println("HERO "+hero.getName()+" Levels Up!!");
            
        }

    }

    public static void printHeroesDetail(){
        System.out.println("YOUR HEROES :\n=============");
        for (int i=0; i<heroes.size();i++) {
            System.out.println("\n[" + (i + 1) + "] " + heroes.get(i).getName());
            System.out.println("    HP : "+heroes.get(i).getHP());
            System.out.println("    MP : "+heroes.get(i).getMP());
            System.out.println("    Level : "+heroes.get(i).getLevel());
            System.out.println("    Strength : "+heroes.get(i).getStrength());
            System.out.println("    Dexterity : "+heroes.get(i).getMP());
            System.out.println("    Agility : "+heroes.get(i).getAgility());
            System.out.println("    Gold : "+heroes.get(i).getGold());
            System.out.println("    Experience : "+heroes.get(i).getExperience());
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