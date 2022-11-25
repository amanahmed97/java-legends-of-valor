package Factory;

import BoardAndCell.BoardAndCell.Board;

public class FactoryBoard {

	public static Board initializeBoard() {
		Board b = new Board();
		b.createCells();
		return b;
		
	}
}
