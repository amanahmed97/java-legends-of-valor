package BoardAndCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.RunGame;


public class Board {
    //    Creates the board and movement to enable running of game world.
//	Contains logic for different tiles like market, inaccessible spaces and common spaces.
    int rows;
    int columns;
    public static int dimension;
    List<List<Cell>> cells = new ArrayList<List<Cell>>();
    List<Cell> cellCols = new ArrayList<Cell>();
    Random rand = new Random();
	int count_0, count_1, count_2, count_3 = 0;

    public Board(int r, int c) {
        rows = r;
        columns = c;
        dimension = r;
    }

    BoardMarker[][] BoardMap;

    public Board(int n) {
        dimension = n;
        BoardMap = new BoardMarker[n][n];
        for (int i = 0; i < n; i++) {
            for (int j=0; j<n; j++)
                BoardMap[i][j] = new BoardMarker(i*n+j);
        }

        createCells();
    }

    public void printBoard() {

        for (int i = 1; i <= dimension; i++) {

            for (int k = 0; k < dimension; k++) {
//                if (dimension * dimension > 9) System.out.print("+----");
//                else
                System.out.print("+-----");
            }
            System.out.print("+\n");

            for (int j = 1; j <= dimension; j++) {
//                if (RunGame.player.xPosition==i && RunGame.player.yPosition==j && BoardMap[i][j].symbol=='M') {
//                    System.out.print("| H/M");
//                } else if (RunGame.player.xPosition==i && RunGame.player.yPosition==j) {
//                    System.out.print("|  H");
//                }else
                if (cells.get(i-1).get(j-1).getSymbol().equals("   "))
                    System.out.print("|"+cells.get(i-1).get(j-1).getColor()+"   "+Colors.RESET);
                else
                    System.out.print("|"+cells.get(i-1).get(j-1).getColor()+"  " + cells.get(i-1).get(j-1).getSymbol()+Colors.RESET);

//                if (RunGame.player.xPosition==i && RunGame.player.yPosition==j && BoardMap[i][j].symbol=='M')
//                    System.out.print(" ");
//                else
                System.out.print(cells.get(i-1).get(j-1).getColor()+"  "+Colors.RESET);
            }
            System.out.print("|\n");
        }
        //Last line pattern
        for (int i = 1; i <= dimension; i++) {
//            if (dimension * dimension > 9) System.out.print("+----");
//            else
            System.out.print("+-----");
        }
        System.out.print("+\n");
    }

    public List<List<Cell>> getCells() {
		return cells;
	}

	public void setCells(List<List<Cell>> cells) {
		this.cells = cells;
	}

	public int setBoard(int xpos, int ypos, char symbol) {
        BoardMap[xpos][ypos].symbol = symbol;
        return BoardMap[xpos][ypos].position;
    }
	
	
    public int setBoardMarker(int xpos, int ypos, String marker, char symbol) {
        BoardMap[xpos][ypos].symbol = symbol;
        BoardMap[xpos][ypos].marker = marker;
        return BoardMap[xpos][ypos].position;
    }
    public int setBoardColor(int xpos, int ypos, char symbol, String color) {
        BoardMap[xpos][ypos].symbol = symbol;
        BoardMap[xpos][ypos].color = color;
        return BoardMap[xpos][ypos].position;
    }
    public int setBoardType(int xpos, int ypos, char symbol, String marker, String color) {
        BoardMap[xpos][ypos].symbol = symbol;
        BoardMap[xpos][ypos].marker = marker;
        BoardMap[xpos][ypos].color = color;
        return BoardMap[xpos][ypos].position;
    }

    public int getBoardSymbol(int xpos, int ypos) {
        return BoardMap[xpos][ypos].symbol;
    }

    public static boolean validPosition(int newXPosition, int newYPosition){
        if( newXPosition<0 || newYPosition<0 || newXPosition>dimension-1 || newYPosition>dimension-1) {
            System.out.println("Invalid move.");
            return false;
        }
        //if( RunGame.board.getBoardSymbol(newXPosition, newYPosition)=='X' ){
        if (RunGame.board.getCells().get(newXPosition).get(newYPosition).getSymbol() == "X") {
            System.out.println("Invalid move.");
            return false;
        }

        return true;
    }

    public void generateBoardCells(){
        generateNexus();
        generateInaccessibleSpaces();
//        generateMarketSpaces();
    }
    
  //initialize cells to different types
  	public void createCells() {
  	    for(int x = 0; x < dimension; x++) {
  	        for(int y = 1; y <= dimension; y++) {
  	        	if ((x == 0 && y%3 != 0) || (x == dimension-1 && y%3 != 0)) {
  	        		cellCols.add(new CellNexus(x, y));
  	        		continue;
  	        	} else if(y%3 == 0) {
  	        		cellCols.add(new CellInaccessible(x, y));
  	        		continue;
  	        	} else {
  	        		boolean cellAdded = false;
  	  	        	while(!cellAdded) {
  	  	        		int int_random = rand.nextInt(4);
  	  		        	if (int_random == 0) {
  	  		        		if (count_0 <= 15){
  	  		        			cellCols.add(new CellPlain(x, y));
  	  		        			count_0++;
  	  		        			cellAdded = true;
  	  		        		}
  	  		        	} else if (int_random == 1){
  	  		        		if (count_1 <= 7) {
  	  		        			cellCols.add(new CellBush(x, y));
  	  		        			count_1++;
  	  		        			cellAdded = true;
  	  		        		}
  	  		        	} else if (int_random == 2) {
  	  		        		if (count_2 <= 7) {
  	  		        			cellCols.add(new CellKoulou(x, y));
  	  		        			count_2++;
  	  		        			cellAdded = true;
  	  		        		}
  	  		        	} else if (int_random == 3) {
  	  		        		if (count_3 <= 7) {
  	  		        			cellCols.add(new CellCave(x, y));
  	  		        			count_3++;
  	  		        			cellAdded = true;
  	  		        		}
  	  		        	} 
  	  	        	}
  	        	}
  	        	
  	        	
  	        }
  	        cells.add(cellCols);
          	cellCols = new ArrayList<Cell>();
  	    }
  	}
    
    public void generateInaccessibleSpaces(){

        for(int i=0;i<dimension;i++) {
            for (int j = 2; j < dimension; j += 3) {
                int xpos = i;
                int ypos = j;
                setBoardColor(xpos, ypos, 'X', Colors.BLACK_BACKGROUND);
                //CellInaccessible c = new CellInaccessible(xpos, ypos);
                //
            }
        }
    }

    public void generateNexus(){

        for(int i=0;i<dimension;i+=(dimension-1)) {
            for (int j = 0; j < dimension; j++) {
                int xpos = i;
                int ypos = j;
                if(i==0)
                    setBoardColor(xpos, ypos, '-', Colors.RED_BACKGROUND);
                else
                    setBoardColor(xpos, ypos, '-', Colors.BLUE_BACKGROUND);
            }
        }
    }

    public void generateMarketSpaces(){
        int numSpaces = (int) (dimension*dimension * 0.3);
        Random random = new Random();

        for(int i=0;i<numSpaces;i++){
            int xpos = random.nextInt(dimension);
            int ypos = random.nextInt(dimension);
            setBoard(xpos, ypos, 'M');
        }
    }

}