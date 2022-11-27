import java.io.*;
import java.util.*;

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

    public static ArrayList<Monster> monsterList;
    public static ArrayList<Monster> spawnMonsters;

    public Monster(String name, int level, int damage, int defense, int dodge) {
        this.name = name;
        this.level = level;
        this.HP= level*100;
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
        int xPosition = 0;
        int yPosition = 0;
        this.lane = 0;

    }

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
            System.out.println("[" + (j + 1) + "] " + monster.name + "  " + monster.level + "  " + monster.damage + "  " + monster.defense + "  " + monster.dodge);
        }
    }

    public static boolean spawnMonsters(){
        // todo scale level
        spawnMonsters = new ArrayList<Monster>();
        ArrayList<Integer> selected = new ArrayList<Integer>();
        Random random = new Random();
        //todo scale level
        for(int i=0; i<RunGame.numberHeroes; i++){
            int randomSelector = random.nextInt(monsterList.size());
            if ( !selected.contains(randomSelector) ) {
                spawnMonsters.add(monsterList.get(randomSelector));
                selected.add(randomSelector);
                Monster monster = spawnMonsters.get(i);
                monster.level = Player.heroes.get(i).level;
                monster.HP = monster.level*100;
                // Set starting position for the monsters
                monster.xPosition = Board.dimension-1;
                monster.yPosition = i * 3;
                monster.lane = spawnMonsters.size()-1;
                RunGame.board.setBoard(monster.xPosition, monster.yPosition, 'M');
            } else{
                i--;
            }
        }

        return true;
    }

    public static void printSpawnMonstersStats() {
        System.out.println("SPAWNED MONSTERS\n================");
        System.out.println("Headers : Name / level / damage / defense / dodge chance");
        for (int j = 0; j < spawnMonsters.size(); j++) {
            Monster monster = spawnMonsters.get(j);
            System.out.println("[" + (j + 1) + "] " + monster.name + "  " + monster.level + "  " + monster.damage + "  " + monster.defense + "  " + monster.dodge);
        }
    }

    public static void printSpawnMonsters() {
        System.out.println("SPAWNED MONSTERS\n================");
        for (int j = 0; j < spawnMonsters.size(); j++) {
            Monster monster = spawnMonsters.get(j);
            System.out.println("[" + (j + 1) + "] " + monster.name + " HP: " + monster.HP);
        }
    }

    public static boolean monsterTurn(int heroSelect, int monsterSelect){
        Random random = new Random();
        Monster monster = spawnMonsters.get(monsterSelect);

            HeroType hero = Player.heroes.get(heroSelect);
            // Check Hero agility to dodge
            double dodgeChance = hero.agility * 0.002;
            int randomDodge = random.nextInt(hero.agility);
            double attackDamage;

            if ( randomDodge > dodgeChance ){
                // todo armor
                if(hero.equipArmor != null)
                    attackDamage = (monster.damage*0.02*monster.level - hero.equipArmor.damage*0.05*hero.level);
                else
                    attackDamage = (monster.damage*0.02*monster.level);

                hero.HP -= attackDamage;
                System.out.println("\nMonster "+monster.name+" attacks Hero "+hero.name
                        +" for damage "+attackDamage);
            }else{
                System.out.println("\nHero "+hero.name+" dodged Monster "+monster.name+" attack");
            }


        return true;
    }

    public static int selectMonster(){
        System.out.println("Select which monster to attack : ");
        Monster.printSpawnMonsters();

        int monsterSelect=0;
        Scanner ip = new Scanner(System.in);
        try {
            System.out.print("Enter monster to attack : ");
            monsterSelect = ip.nextInt();

            while(monsterSelect<1 || monsterSelect>Monster.spawnMonsters.size()){
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
        RunGame.board.setBoard(this.xPosition, this.yPosition, '-');
        // New position
        if(xPosition>0)
            xPosition--;
        RunGame.board.setBoard(this.xPosition, this.yPosition, 'M');

        return true;
    }

}
