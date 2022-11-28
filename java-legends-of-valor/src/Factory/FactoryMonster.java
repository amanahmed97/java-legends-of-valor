package Factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Character.Monster;
import Character.Player;
import Game.RunGameMain;

public class FactoryMonster {
	
	public static ArrayList<Monster> monsterList;
	public static ArrayList<Monster> spawnMonsters;
	
	public static void populate() throws IOException {
        monsterList = new ArrayList<Monster>();
        populateDragons();
        populateExoskeletons();
        populateSpirits();
    }

    public static void populateDragons() throws IOException {
        String line;
        int ctr = 0;
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Parse the file
        File file = new File("./src/gamelib/Dragons.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);

            // Skip the first header line
            if (ctr == 0 || iarray.length < 7) {
                ctr++;
                continue;
            }

            monsterList.add(new Monster("Dragon " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]) ) );
            ctr++;
        }

    }

    public static void populateExoskeletons() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file = new File("./src/gamelib/Exoskeletons.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);
            // Skip the first header line
            if (ctr == 0 || iarray.length < 5) {
                ctr++;
                continue;
            }
            monsterList.add(new Monster("Exoskeleton " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]) ) );
            ctr++;
        }

    }

    public static void populateSpirits() throws IOException {

        String line;
        int ctr = 0;

        // Parse the file
        File file = new File("./src/gamelib/Spirits.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            String sp = "\\s+";
            String[] iarray = line.split(sp);
            // Skip the first header line
            if (ctr == 0 || iarray.length < 4) {
                ctr++;
                continue;
            }

            monsterList.add(new Monster("Spirit " + iarray[0], Integer.parseInt(iarray[1]), Integer.parseInt(iarray[2]), Integer.parseInt(iarray[3]),
                    Integer.parseInt(iarray[4]) ) );
            ctr++;
        }
    }

    public static void printMonsterList() {
        System.out.println("Headers : Name / level / damage / defense / dodge chance");
        for (int j = 0; j < monsterList.size(); j++) {
            Monster monster = monsterList.get(j);
            System.out.println("[" + (j + 1) + "] " + monster.getName() + "  " + monster.getLevel() + "  " + monster.getDamage() + "  " + monster.getDefense() + "  " + monster.getDodge());
        }
    }
    
    public static boolean spawnMonsters(){
        // todo scale level
        spawnMonsters = new ArrayList<Monster>();
        ArrayList<Integer> selected = new ArrayList<Integer>();
        Random random = new Random();
        //todo scale level
        for(int i=0; i<RunGameMain.numberHeroes; i++){
            int randomSelector = random.nextInt(monsterList.size());
            if ( !selected.contains(randomSelector) ) {
                spawnMonsters.add(monsterList.get(randomSelector));
                selected.add(randomSelector);
                spawnMonsters.get(i).setLevel(Player.heroes.get(i).getLevel());
                spawnMonsters.get(i).setHP(spawnMonsters.get(i).getLevel()*100);
            }
        }

        return true;
    }

    public static void printSpawnMonstersStats() {
        System.out.println("SPAWNED MONSTERS\n================");
        System.out.println("Headers : Name / level / damage / defense / dodge chance");
        for (int j = 0; j < spawnMonsters.size(); j++) {
            Monster monster = spawnMonsters.get(j);
            System.out.println("[" + (j + 1) + "] " + monster.getName() + "  " + monster.getLevel() + "  " + monster.getDamage() + "  " + monster.getDefense() + "  " + monster.getDodge());
        }
    }

    public static void printSpawnMonsters() {
        System.out.println("SPAWNED MONSTERS\n================");
        for (int j = 0; j < spawnMonsters.size(); j++) {
            Monster monster = spawnMonsters.get(j);
            System.out.println("[" + (j + 1) + "] " + monster.getName() + " HP: " + monster.getHP());
        }
    }

}
