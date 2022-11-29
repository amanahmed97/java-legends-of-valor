package BoardAndCell;

import CellBehaviour.BushCellBehaviour;
import Character.Hero;

public class CellBush extends Cell{
	
	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";

	public CellBush(int i, int j) {
		super(i, j);
		this.setColor(Colors.YELLOW_BACKGROUND_BRIGHT);
		this.setSymbol("-");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		super.cb = new BushCellBehaviour();
	}
	
	public void heroEnter(Hero hero){
		cb.heroEnterBehaviour(hero);
	}
	
	public void heroLeave(Hero hero) {
		cb.heroLeaveBehaviour(hero);
	}

}
