package CellBehaviour;

import Character.Hero;

public class CaveCellBehaviour extends CellBehaviour{

	@Override
	public void heroEnterBehaviour(Hero hero) {
		// increase agility
		hero.setAgility(hero.getAgility() + 100);
		
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
