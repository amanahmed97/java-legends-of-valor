package BoardAndCell;

import CellBehaviour.CellBehaviour;
import CellBehaviour.PlainCellBehaviour;
import Character.Hero;

public class Cell {

    private int row, column;
    private String color;
    private String symbol;
    private boolean cellEnter = false;
    private boolean isTeamPresent = false;
    private boolean canEnterMarket = false;
    protected CellBehaviour cb;

    public Cell(int i, int j) {
        this.setRow(i);
        this.setColumn(j);


    }

//	public String display() {
//
//		return ;
//
//	}
    public void heroEnter(Hero hero){
		cb.heroEnterBehaviour(hero);
	}
	
	public void heroLeave(Hero hero) {
		cb.heroLeaveBehaviour(hero);
	}

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String letter) {
        this.symbol = letter;
    }

    public boolean isTeamPresent() {
        return isTeamPresent;
    }

    public void setTeamPresent(boolean isTeamPresent) {
        this.isTeamPresent = isTeamPresent;
    }

    public boolean isCellEnter() {
        return cellEnter;
    }

    public void setCellEnter(boolean cellEnter) {
        this.cellEnter = cellEnter;
    }

    public boolean isCanEnterMarket() {
        return canEnterMarket;
    }

    public void setCanEnterMarket(boolean canEnterMarket) {
        this.canEnterMarket = canEnterMarket;
    }

}
