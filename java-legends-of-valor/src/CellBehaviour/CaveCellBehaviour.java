package CellBehaviour;

import Character.Hero;

public class CaveCellBehaviour implements CellBehaviour{

	@Override
	public void heroEnterBehaviour(Hero hero) {
		// increase agility
		hero.setAgility(hero.getAgility() + 100);
		System.out.println("Hero's agility increased by 100: " + hero.getAgility());
		
	}

	@Override
	public void heroLeaveBehaviour(Hero hero) {
		// decrease agility
		if (hero.getAgility() > 100)
			hero.setAgility(hero.getAgility() - 100);
		else
			hero.setAgility(0);
		
	}

}
