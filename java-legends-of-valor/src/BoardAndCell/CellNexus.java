package BoardAndCell;

public class CellNexus extends Cell{
	
	public static final String ANSI_GREEN_BACKGROUND = "\033[42m";  // GREEN

	public CellNexus(int i, int j) {
		super(i, j);
		this.setColor(Colors.WHITE_BACKGROUND);
		this.setSymbol("   ");
		this.setCellEnter(true);
		this.setCanEnterMarket(false);
	}

}
