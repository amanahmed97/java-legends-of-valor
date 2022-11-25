package BoardAndCell;

import CellBehaviour.KoulouCellBehaviour;

public class CellKoulou extends Cell{
	
	public static final String ANSI_PURPLE_BACKGROUND = "\033[45m";
	//CellBehaviour cb;

	public CellKoulou(int i, int j) {
		super(i, j);
		this.setColor(ANSI_PURPLE_BACKGROUND);
		this.setLetter("  ");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
		cb = new KoulouCellBehaviour();
	}

}
