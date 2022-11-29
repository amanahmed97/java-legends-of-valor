package BoardAndCell;

public class CellPlain extends Cell{
	
	public static final String ANSI_WHITE_BACKGROUND = "\033[47m";

	public CellPlain(int i, int j) {
		super(i, j);
		this.setColor(Colors.GREEN_BACKGROUND);
		this.setSymbol("   ");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
	}
}
