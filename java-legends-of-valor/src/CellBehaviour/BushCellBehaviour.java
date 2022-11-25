package CellBehaviour;

import Character.Hero;

public class BushCellBehaviour extends CellBehaviour {

    @Override
    public void heroEnterBehaviour(Hero hero) {
        //increase dexterity when hero enters
    	hero.setDexterity(hero.getDexterity() + 100);
    }

    @Override
    public void heroLeaveBehaviour(Hero hero) {
        //remove the dexterity for hero
    	if (hero.getAgility() > 100)
    		hero.setDexterity(hero.getDexterity() - 100);
    	else
    		hero.setDexterity(0);
    }
}
