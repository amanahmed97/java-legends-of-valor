package CellBehaviour;

import Character.Hero;

public class KoulouCellBehaviour extends CellBehaviour{

	@Override
	public void heroEnterBehaviour(Hero hero) {
		// increase strength
		hero.setStrength(hero.getStrength() + 100);
		
	}

	@Override
	public void heroLeaveBehaviour(Hero hero) {
		// decrease strength
		if (hero.getStrength() > 100)
			hero.setStrength(hero.getStrength() - 100);
		else
			hero.setStrength(0);
	}

}
