package Factory;

import java.io.*;
import java.util.ArrayList;

import Character.Hero;
import Character.Monster;

public class FactoryHero {
	public static ArrayList<Hero> heroList;

	
	public static void populateHeroes() throws IOException {
	        heroList = new ArrayList<Hero>();
	        populateWarriors();
	        populatePaladins();
	        populateSorcerers();
	    
	}

	public static void populateWarriors() throws IOException {
        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Factory/Warriors.txt");
            br = new BufferedReader(new FileReader(file));
        }catch(Exception e){
            file = new File("./src/gamelib/Warriors.txt");
            br = new BufferedReader(new FileReader(file));
        }

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 7) {
                ctr++;
                continue;
            }

            heroList.add(new Hero("warrior", "Warrior " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }

    }

    public static void populatePaladins() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Factory/Paladins.txt");
            br = new BufferedReader(new FileReader(file));
        }catch(Exception e){
            file = new File("./src/gamelib/Paladins.txt");
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
            heroList.add(new Hero("paladin", "Paladin " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }

    }

    public static void populateSorcerers() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file;
        BufferedReader br;
        try{
            file = new File("./Factory/Sorcerers.txt");
            br = new BufferedReader(new FileReader(file));
        }catch(Exception e){
            file = new File("./src/gamelib/Sorcerers.txt");
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

            heroList.add(new Hero("sorcerer", "Sorcerer " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]), Integer.parseInt(iarray[5]), Integer.parseInt(iarray[6])));
            ctr++;
        }
    }

    public static void printHeroList() {
        System.out.println("Headers : Name / mana / strength / agility / dexterity / starting money / starting experience");
        for (int j = 0; j < heroList.size(); j++) {
            Hero hero = heroList.get(j);
            System.out.println("[" + (j + 1) + "] " + hero.getName() + "  " + hero.getMP() + "  " + hero.getStrength() + "  " + hero.getAgility() + "  " + hero.getDexterity() + "  " + hero.getGold() + "  " + hero.getExperience());
        }
    }
    
}
