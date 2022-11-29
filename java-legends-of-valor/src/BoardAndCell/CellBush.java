package BoardAndCell;

import CellBehaviour.BushCellBehaviour;

public class CellBush extends Cell{
	
	public static final String ANSI_YELLOW_BACKGROUND = "\033[43m";

	public CellBush(int i, int j) {
		super(i, j);
		this.setColor(Colors.YELLOW_BACKGROUND_BRIGHT);
		this.setSymbol("   ");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		super.cb = new BushCellBehaviour();
	}

}
