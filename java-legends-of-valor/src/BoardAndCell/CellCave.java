package BoardAndCell;

import CellBehaviour.CaveCellBehaviour;

public class CellCave extends Cell{
	
	public static final String ANSI_BLUE_BACKGROUND = "\033[44m";

	public CellCave(int i, int j) {
		super(i, j);
		this.setColor(ANSI_BLUE_BACKGROUND);
		this.setLetter("  ");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		super.cb = new CaveCellBehaviour();
	}

}
