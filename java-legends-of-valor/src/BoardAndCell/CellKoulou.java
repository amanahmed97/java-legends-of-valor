package BoardAndCell;

import CellBehaviour.KoulouCellBehaviour;
import Character.Hero;

public class CellKoulou extends Cell{
	
	public static final String ANSI_PURPLE_BACKGROUND = "\033[45m";
	//CellBehaviour cb;

	public CellKoulou(int i, int j) {
		super(i, j);
		this.setColor(ANSI_PURPLE_BACKGROUND);
		this.setSymbol("-");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		cb = new KoulouCellBehaviour();
	}
	
	public void heroEnter(Hero hero){
		cb.heroEnterBehaviour(hero);
	}
	
	public void heroLeave(Hero hero) {
		cb.heroLeaveBehaviour(hero);
	}

}
