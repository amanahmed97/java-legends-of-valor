package BoardAndCell;
import java.util.Random;

public class Board {
    //    Creates the board and movement to enable running of game world.
//	Contains logic for different tiles like market, inaccessible spaces and common spaces.
    int rows;
    int columns;
    public static int dimension;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    public Board(int r, int c) {
        rows = r;
        columns = c;
        dimension = r;
    }

//    BoardMarker[][] BoardMap;

    public Board(int n) {
        dimension = n;
        dimension = n;
        dimension=n;
//        BoardMap = new BoardMarker[n][n];
        for (int i = 0; i < n; i++) {
            for (int j=0; j<n; j++)
//                BoardMap[i][j] = new BoardMarker(i*n+j);
                ;
        }
        generateInaccessibleSpaces();
        generateMarketSpaces();
    }

    public void printBoard() {

        for (int i = 0; i < dimension; i++) {

            for (int k = 0; k < dimension; k++) {
//                if (dimension * dimension > 9) System.out.print("+----");
//                else
                System.out.print(Colors.RED+"+-----"+Colors.RESET);
            }
            System.out.print("+\n");

            for (int j = 0; j < dimension; j++) {
                System.out.print("|"+getCellColor(i,j)+"   "+Colors.RESET);

//                if ((dimension * dimension > 9 && (i * dimension + j) < 9) || (dimension * dimension > 9 && BoardMap[i][j].symbol != '-'))
//                    System.out.print("   ");
////                else
//                if (RunGame.player.xPosition==i && RunGame.player.yPosition==j && BoardMap[i][j].symbol=='M')
//                    System.out.print(" ");
//                else
                System.out.print(getCellColor(i,j)+"  "+Colors.RESET);
            }
            System.out.print("|\n");
        }

//            for (int j = 0; j < dimension; j++) {
//                if (RunGame.player.xPosition==i && RunGame.player.yPosition==j && BoardMap[i][j].symbol=='M') {
//                    System.out.print("| H/M");
//                } else if (RunGame.player.xPosition==i && RunGame.player.yPosition==j) {
//                    System.out.print("|  H");
//                }else if (BoardMap[i][j].symbol == '-')
//                    System.out.print("|   ");
//                else
//                    System.out.print("|  " + BoardMap[i][j].symbol);
//
////                if ((dimension * dimension > 9 && (i * dimension + j) < 9) || (dimension * dimension > 9 && BoardMap[i][j].symbol != '-'))
////                    System.out.print("   ");
////                else
//                if (RunGame.player.xPosition==i && RunGame.player.yPosition==j && BoardMap[i][j].symbol=='M')
//                    System.out.print(" ");
//                else
//                    System.out.print("  ");
//            }
//            System.out.print("|\n");
//        }
        //Last line pattern
        for (int i = 1; i <= dimension; i++) {
//            if (dimension * dimension > 9) System.out.print("+----");
//            else
            System.out.print("+-----");
        }
        System.out.print("+\n");
    }

//    public int setBoard(int xpos, int ypos, char symbol) {
//        BoardMap[xpos][ypos].symbol = symbol;
//        return BoardMap[xpos][ypos].position;
//    }
//
//    public int getBoardSymbol(int xpos, int ypos) {
//        return BoardMap[xpos][ypos].symbol;
//    }

//    public static boolean validPosition(int newXPosition, int newYPosition){
//        if( newXPosition<0 || newYPosition<0 || newXPosition>dimension || newYPosition>dimension) {
//            System.out.println("Invalid move.");
//            return false;
//        }
//        if( RunGame.board.getBoardSymbol(newXPosition, newYPosition)=='X' ){
//            System.out.println("Invalid move.");
//            return false;
//        }
//
//        return true;
//    }

    public void generateInaccessibleSpaces(){
        int numSpaces = (int) (dimension*dimension * 0.2);
        Random random = new Random();

        for(int i=0;i<numSpaces;i++){
            int xpos = random.nextInt(dimension);
            int ypos = random.nextInt(dimension);
//            setBoard(xpos, ypos, 'X');
        }
    }

    public void generateMarketSpaces(){
        int numSpaces = (int) (dimension*dimension * 0.3);
        Random random = new Random();

        for(int i=0;i<numSpaces;i++){
            int xpos = random.nextInt(dimension);
            int ypos = random.nextInt(dimension);
//            setBoard(xpos, ypos, 'M');
        }
    }

    public String getCellColor(int i, int j){
        if(j==2 || j==5)
            return Colors.BLACK_BACKGROUND;
        if(i==0)
            return Colors.RED_BACKGROUND;
        if(i==dimension-1)
            return Colors.BLUE_BACKGROUND;

        return Colors.RESET;
    }
}