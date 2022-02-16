
//class that makes Pieces
public class Piece {
    //fields
    private String playerName;
    private String PieceName;
    private int Piece_X;
    private int Piece_Y;

    private String calculatedCo;

    //constructor
    public Piece(String givenPlayerName, String givenPieceName, int x_cor, int y_cor) {
        playerName = givenPlayerName;
        PieceName = givenPieceName;
        Piece_X = x_cor;
        Piece_Y = y_cor;
    }

    //get methods
    public String getPlayerName(){
        return playerName;
    }
    public String getPieceName(){
        return PieceName;
    }
    public int getPawn_X(){
        return Piece_X;
    }
    public int getPawn_Y(){
        return Piece_Y;
    }

    //calculate where can it move
    //method that calculates pawn moves
    public String[][] calPawnA(String[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1= (wanted_x<7 && current_x+1 == wanted_x);
        boolean check2=(current_y == wanted_y || current_y+1 == wanted_y || current_y-1 == wanted_y);
        boolean check3=(current_y+1<8 && current_y-1>=0);

        if(check1&&check2){
            if(wanted_y<8 && wanted_y >=0) {
                board[wanted_x][wanted_y] = getPieceName();
                board[current_x][current_y] = "(" + current_x + "," + current_y + ")";
                return board;
            }
            return board;
        }else {
            return board;
        }
    }
}
