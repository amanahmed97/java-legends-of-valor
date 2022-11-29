package CellBehaviour;

import Character.Hero;

public class BushCellBehaviour implements CellBehaviour {

    @Override
    public void heroEnterBehaviour(Hero hero) {
        //increase dexterity when hero enters
    	hero.setDexterity(hero.getDexterity() + 100);
    	System.out.println("Hero's dexterity increased by 100: " + hero.getDexterity());
    }

    @Override
    public void heroLeaveBehaviour(Hero hero) {
        //remove the dexterity for hero
    	if (hero.getDexterity() > 100)
    		hero.setDexterity(hero.getDexterity() - 100);
    	else
    		hero.setDexterity(0);
    }
}
