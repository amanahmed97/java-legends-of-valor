package LevelUp;

import Character.Hero;

public class SorcererLevelUpBehaviour implements LevelUpBehaviour{
	
	@Override
	public void levelUp(Hero hero) {
		// TODO Auto-generated method stub
		//dexterity and agility
		hero.setLevel(hero.getLevel() + 1);
		
		//TODO incorporate gold into faint behaviour
		//hero.setGold();
		
		hero.setHP(hero.getLevel() * 100);
		
		hero.setMP(hero.getMP() * 1.1);
		
		long strength = (long) (hero.getStrength() + (0.05 * hero.getStrength()));
		hero.setStrength(strength);
		
		long dexterity = (long) (hero.getDexterity() + (0.1 * hero.getDexterity()));
		hero.setDexterity(dexterity);
		
		long agility = (long) (hero.getAgility() + (0.1 * hero.getAgility()));
		hero.setAgility(agility);
		
	}
	
}
