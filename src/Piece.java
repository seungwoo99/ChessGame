
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

    //calculate where can the selected piece move
    //method that calculates player2's Pawn move
    public Piece[][] calPawnA(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (current_x< wanted_x && wanted_x < 8) && (0<= wanted_y && wanted_y <8);

        //initial move allows moving two blocks
        if(check1){
            //basic move
            if(wanted_x - current_x == 1 && current_y == wanted_y){
                //if opponent's piece is located on the wanted coordinate, then cannot move
                if(board[wanted_x][wanted_y].getEmpty().equals("empty")){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
                //initial two block move
            }else if(current_x == 1 && wanted_x - current_x == 2 && current_y == wanted_y){
                if(board[2][current_y].getEmpty().equals("empty") && board[3][current_y].getEmpty().equals("empty")){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
                //take opponent's piece
            }else if((current_x + 1 == wanted_x) && (current_y - 1 == wanted_y || current_y + 1 == wanted_y)){
                if(!board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName())){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
            }
        }

        return board;
    }
    //method that calculates player2's Pawn move
    public Piece[][] calPawnB(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (0<= wanted_x && wanted_x < current_x) && (0<= wanted_y && wanted_y <8);

        //initial move allows moving two blocks
        if(check1){
            //basic move
            if(current_x - wanted_x == 1 && current_y == wanted_y){
                //if opponent's piece is located on the wanted coordinate, then cannot move
                if(board[wanted_x][wanted_y].getEmpty().equals("empty")){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
                //initial two block move
            }else if(current_x == 6 && current_x - wanted_x == 2 && current_y == wanted_y){
                if(board[5][current_y].getEmpty().equals("empty") && board[4][current_y].getEmpty().equals("empty")){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
                //take opponent's piece
            }else if((current_x - 1 == wanted_x) && (current_y - 1 == wanted_y || current_y + 1 == wanted_y)){
                if(!board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName())){
                    board[wanted_x][wanted_y] = board[current_x][current_y];
                    board[current_x][current_y] = new Piece(current_x, current_y);
                }
            }
        }

        return board;
    }

    //method that calculates Rook move
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

    //method that calculates Knight move
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

    //method that calculates Bishop move
    public Piece[][] calBishop(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (0<=wanted_x && wanted_x<8) && (0<=wanted_y && wanted_y<8);
        //difference between wanted and current value
        int diffX = wanted_x - current_x;
        int diffY = wanted_y - current_y;
        boolean check2 = diffX == diffY || diffX == -diffY;
        //if input destination value is appropriate, then move the Bishop
        if(check1 && check2){
            //check if other piece is located between them
            somethingbetween = 0;
            if(current_x < wanted_x && current_y < wanted_y){
                for(int i=1; i<wanted_x-current_x; i++){
                    if(board[current_x + i][current_y + i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
            }else if(current_x < wanted_x && current_y > wanted_y){
                for(int i=1; i<wanted_x-current_x; i++){
                    if(board[current_x + i][current_y - i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
            }else if(current_x > wanted_x && current_y > wanted_y){
                for(int i=1; i<current_x - wanted_x; i++){
                    if(board[current_x - i][current_y - i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
            }else if(current_x > wanted_x && current_y < wanted_y){
                for(int i=1; i<current_x - wanted_x; i++){
                    if(board[current_x - i][current_y + i].getEmpty().equals("NotEmpty")){
                        somethingbetween+=1;
                    }
                }
            }
            //if there is something between current coordinate and its destination
            //then, do not move the piece
            if(somethingbetween > 0) {
                return board;
            //if there is noting between current coordinate and its destination, and no player's piece on the destination coordinate
                //then move the piece
            }else if(somethingbetween==0 && ((board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                    !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName()))){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
                return board;
            }

        }
        return board;
    }

    //method that calculates King move
    public Piece[][] calKing(Piece[][] board, int current_x, int current_y, int wanted_x, int wanted_y){
        boolean check1 = (0<=wanted_x && wanted_x<8) && (0<=wanted_y && wanted_y<8);
        boolean check2 = (board[wanted_x][wanted_y].getEmpty().equals("empty"))||
                !board[wanted_x][wanted_y].getPlayerName().equals(board[current_x][current_y].getPlayerName());
        //difference between wanted and current value
        int diffX = wanted_x - current_x;
        int diffY = wanted_y - current_y;

        if(check1 && check2){
            if((-1 <= diffX && diffX <= 1)&&(-1 <= diffY && diffY <= 1)){
                board[wanted_x][wanted_y] = board[current_x][current_y];
                board[current_x][current_y] = new Piece(current_x, current_y);
            }
        }
        return board;
    }

}
