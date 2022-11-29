package BoardAndCell;

import CellBehaviour.KoulouCellBehaviour;
import CellBehaviour.PlainCellBehaviour;
import Character.Hero;

public class CellPlain extends Cell{
	
	public static final String ANSI_WHITE_BACKGROUND = "\033[47m";

	public CellPlain(int i, int j) {
		super(i, j);
		this.setColor(Colors.GREEN_BACKGROUND);
		this.setSymbol("-");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		super.cb = new PlainCellBehaviour();
	}
	
	public void heroEnter(Hero hero){
		cb.heroEnterBehaviour(hero);
	}
	
	public void heroLeave(Hero hero) {
		cb.heroLeaveBehaviour(hero);
	}
}
