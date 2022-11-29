package BoardAndCell;

import CellBehaviour.CaveCellBehaviour;
import Character.Hero;

public class CellCave extends Cell{
	
	public static final String ANSI_BLUE_BACKGROUND = "\033[44m";

	public CellCave(int i, int j) {
		super(i, j);
		this.setColor(ANSI_BLUE_BACKGROUND);
		this.setSymbol("-");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		super.cb = new CaveCellBehaviour();
	}
	
	public void heroEnter(Hero hero){
		cb.heroEnterBehaviour(hero);
	}
	
	public void heroLeave(Hero hero) {
		cb.heroLeaveBehaviour(hero);
	}

}
