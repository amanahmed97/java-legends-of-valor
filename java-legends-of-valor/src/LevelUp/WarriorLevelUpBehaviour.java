package LevelUp;

import Character.Hero;

public class WarriorLevelUpBehaviour implements LevelUpBehaviour{

	@Override
	public void levelUp(Hero hero) {
		//strength and agility
		hero.setLevel(hero.getLevel() + 1);

		//TODO incorporate gold into faint behaviour
		//hero.setGold();

		hero.setHP(hero.getLevel() * 100);

		hero.setMP((int) (hero.getMP() * 1.1));

		int strength = (int) (hero.getStrength() + (0.1 * hero.getStrength()));
		hero.setStrength(strength);

		int dexterity = (int) (hero.getDexterity() + (0.05 * hero.getDexterity()));
		hero.setDexterity(dexterity);

		int agility = (int) (hero.getAgility() + (0.1 * hero.getAgility()));
		hero.setAgility(agility);

	}

}
