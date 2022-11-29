package BoardAndCell;

public class CellInaccessible extends Cell{

	public static final String ANSI_RED_BACKGROUND = "\033[41m";

	public CellInaccessible(int i, int j) {
		super(i, j);
		this.setColor(Colors.BLACK_BACKGROUND);
		this.setSymbol("X");
		this.setCellEnter(false);
		this.setCanEnterMarket(false);
	}
}
