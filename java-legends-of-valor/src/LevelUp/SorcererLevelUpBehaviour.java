package LevelUp;

import Character.Hero;
import Character.Player;

public class SorcererLevelUpBehaviour implements LevelUpBehaviour{

	@Override
	public void levelUp(Hero hero) {
		// TODO Auto-generated method stub
		//dexterity and agility

		hero.setExperience(hero.getExperience() + Player.heroes.size() * 2);

		//faint behaviour
		if (hero.getHP()>0)
			hero.setGold(hero.getLevel() * 100);

		//level up only if condition is met
		if(hero.getExperience() > hero.getLevel()*10){
			hero.setLevel(hero.getLevel() + 1);
			// increase hero stats
			hero.setHP(hero.getLevel()*100);
			hero.setMP((int) (hero.getMP() * 1.1));


			int strength = (int) (hero.getStrength() + (0.05 * hero.getStrength()));
			hero.setStrength(strength);

			int dexterity = (int) (hero.getDexterity() + (0.1 * hero.getDexterity()));
			hero.setDexterity(dexterity);

			int agility = (int) (hero.getAgility() + (0.1 * hero.getAgility()));
			hero.setAgility(agility);
			
			System.out.println("HERO "+hero.getName()+" Levels Up!!");
		}
	}

}
