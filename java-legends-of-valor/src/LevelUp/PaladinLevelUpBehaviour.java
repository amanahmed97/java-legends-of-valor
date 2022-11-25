package LevelUp;

import Character.Hero;

public class PaladinLevelUpBehaviour implements LevelUpBehaviour{

	@Override
	public void levelUp(Hero hero) {
		//check for experience before calling this function
		
		//level, experience, gold gain, HP, MP?
		hero.setLevel(hero.getLevel() + 1);
		
		//TODO incorporate gold into faint behaviour
		//hero.setGold();
		
		hero.setHP(hero.getLevel() * 100);
		
		hero.setMP(hero.getMP() * 1.1);
		
		//strength and dexterity
		long strength = (long) (hero.getStrength() + (0.1 * hero.getStrength()));
		hero.setStrength(strength);
		
		long dexterity = (long) (hero.getDexterity() + (0.1 * hero.getDexterity()));
		hero.setDexterity(dexterity);
		
		long agility = (long) (hero.getAgility() + (0.05 * hero.getAgility()));
		hero.setAgility(agility);
	}

}
