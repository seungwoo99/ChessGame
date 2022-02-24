
//class that makes Pieces
public class Piece {
    //fields
    private String playerName;
    private String PieceName;
    private String empty;
    private int somethingbetween;

    //constructors
    public Piece(String givenPlayerName, String givenPieceName) {
        playerName = givenPlayerName;
        PieceName = givenPieceName;
        empty = "NotEmpty";
    }
    public Piece(int x_cor, int y_cor){
        empty="empty";
        if(y_cor == 0){
            PieceName = "("+(8-x_cor)+","+"A)";
        }else if(y_cor==1){
            PieceName = "("+(8-x_cor)+","+"B)";
        }else if(y_cor==2){
            PieceName = "("+(8-x_cor)+","+"C)";
        }else if(y_cor==3){
            PieceName = "("+(8-x_cor)+","+"D)";
        }else if(y_cor==4){
            PieceName = "("+(8-x_cor)+","+"E)";
        }else if(y_cor==5){
            PieceName = "("+(8-x_cor)+","+"F)";
        }else if(y_cor==6){
            PieceName = "("+(8-x_cor)+","+"G)";
        }else{
            PieceName = "("+(8-x_cor)+","+"H)";
        }
    }

    //get methods
    public String getPlayerName(){
        return playerName;
    }
    public String getPieceName(){
        return PieceName;
    }
    public String getEmpty(){
        return empty;
    }

    //calculate where can it move
    //method that calculates pawn moves
    public Piece[][] calPawnA(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1= (wanted_x<7 && current_x+1 == wanted_x);
        boolean check2=(current_y == wanted_y || current_y+1 == wanted_y || current_y-1 == wanted_y);
        boolean check3=(current_y+1<8 && current_y-1>=0);

        if(check1&&check2){
            if(wanted_y<8 && wanted_y >=0) {
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
                return board;
            }
            return board;
        }else {
            return board;
        }
    }
    //method that calculates Rook moves
    public Piece[][] calRook(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (current_y == wanted_y)&&(wanted_x>=0 && wanted_x<8)&&(current_x!=wanted_x);
        boolean check2 = (current_x == wanted_x)&&(wanted_y>=0 && wanted_y<8)&&(current_y!=wanted_y);
        somethingbetween=0;
        if (check1||check2){
            //check if other piece is located between them
            if(current_x<wanted_x){
                for(int i=current_x+1; i<wanted_x; i++){
                    if(board[i][current_y].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
                if (somethingbetween>0){
                    return board;
                }else if(somethingbetween==0 && ((board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                        !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName()))){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                    return board;
                }
            }else if(current_x>wanted_x){
                for(int i=wanted_x+1; i<current_x; i++){
                    if(board[i][current_y].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
                if (somethingbetween>0){
                    return board;
                }else if(somethingbetween==0 && ((board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                        !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName()))){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                    return board;
                }
            }else if(current_y<wanted_y){
                for(int i=current_y+1; i<wanted_y; i++){
                    if(board[current_x][i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
                if (somethingbetween>0){
                    return board;
                }else if(somethingbetween==0 && ((board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                        !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName()))){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                    return board;
                }
            }else if(current_y>wanted_y){
                for(int i=wanted_y+1; i<current_y; i++){
                    if(board[current_x][i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
                if (somethingbetween>0){
                    return board;
                }else if(somethingbetween==0 && ((board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                        !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName()))){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                    return board;
                }
            }
        }
        return board;
    }

    //method that calculates Knight moves
    public Piece[][] calKnight(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (0<=wanted_x && wanted_x<8) && (0<=wanted_y && wanted_y<8);
        if(check1){
            if(current_x - 2 == wanted_x && (current_y - 1 == wanted_y || current_y+1 == wanted_y)){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
            }else if((current_x-1 == wanted_x || current_x+1 == wanted_x) && current_y+2 == wanted_y){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
            }else if(current_x+2==wanted_x && (current_y-1 == wanted_y || current_y+1 == wanted_y)){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
            }else if((current_x-1==wanted_x || current_x+1==wanted_x) && current_y-2 == wanted_y){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
            }
        }
        return board;
    }

    //method that calculates Bishop moves
    public Piece[][] calBishop(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (0<=wanted_x && wanted_x<8) && (0<=wanted_y && wanted_y<8);
        if(check1){

        }

        return board;
    }

}
