public class BoardMarker {
//    	Has the tile marker for each tile on the board.
    int position;
    char symbol;
    String marker;
    String color;

    public BoardMarker(int pos){
        position = pos;
        symbol = '-';
        marker= new String();
        color= Colors.RESET;
    }

}
