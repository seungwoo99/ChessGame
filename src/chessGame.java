//By - Seung Woo Choi
import java.util.Scanner;

public class chessGame {
    //main method
    public static void main(String[] args) {
        //ask users' name
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String player1 = input.nextLine();
        System.out.print("What is your opponent's name? ");
        String player2 = input.nextLine();

        //set player1's pieces
        Piece player1_Rook1 = new Piece(player1, player1 + "_Rook1", 0, 0);
        Piece player1_Knight1 = new Piece(player1, player1 + "_Knight1", 0, 1);
        Piece player1_Bishop1 = new Piece(player1, player1 + "_Bishop1", 0, 2);
        Piece player1_Queen = new Piece(player1, player1 + "_Queen", 0, 3);
        Piece player1_King = new Piece(player1, player1 + "_King", 0, 4);
        Piece player1_Bishop2 = new Piece(player1, player1 + "_Bishop2", 0, 5);
        Piece player1_Knight2 = new Piece(player1, player1 + "_Knight2", 0, 6);
        Piece player1_Rook2 = new Piece(player1, player1 + "_Rook2", 0, 7);

        Piece player1_pawn1 = new Piece(player1, player1 + "_Pawn1", 1, 0);
        Piece player1_pawn2 = new Piece(player1, player1 + "_Pawn2", 1, 1);
        Piece player1_pawn3 = new Piece(player1, player1 + "_Pawn3", 1, 2);
        Piece player1_pawn4 = new Piece(player1, player1 + "_Pawn4", 1, 3);
        Piece player1_pawn5 = new Piece(player1, player1 + "_Pawn5", 1, 4);
        Piece player1_pawn6 = new Piece(player1, player1 + "_Pawn6", 1, 5);
        Piece player1_pawn7 = new Piece(player1, player1 + "_Pawn7", 1, 6);
        Piece player1_pawn8 = new Piece(player1, player1 + "_Pawn8", 1, 7);

        //set player2's pieces
        Piece player2_Rook1 = new Piece(player2, player2 + "_Rook1", 7, 0);
        Piece player2_Knight1 = new Piece(player2, player2 + "_Knight1", 7, 1);
        Piece player2_Bishop1 = new Piece(player2, player2 + "_Bishop1", 7, 2);
        Piece player2_King = new Piece(player2, player2 + "_King", 7, 3);
        Piece player2_Queen = new Piece(player2, player2 + "_Queen", 7, 4);
        Piece player2_Bishop2 = new Piece(player2, player2 + "_Bishop2", 7, 5);
        Piece player2_Knight2 = new Piece(player2, player2 + "_Knight2", 7, 6);
        Piece player2_Rook2 = new Piece(player2, player2 + "_Rook2", 7, 7);

        Piece player2_pawn1 = new Piece(player2, player2 + "_Pawn1", 6, 0);
        Piece player2_pawn2 = new Piece(player2, player2 + "_Pawn2", 6, 1);
        Piece player2_pawn3 = new Piece(player2, player2 + "_Pawn3", 6, 2);
        Piece player2_pawn4 = new Piece(player2, player2 + "_Pawn4", 6, 3);
        Piece player2_pawn5 = new Piece(player2, player2 + "_Pawn5", 6, 4);
        Piece player2_pawn6 = new Piece(player2, player2 + "_Pawn6", 6, 5);
        Piece player2_pawn7 = new Piece(player2, player2 + "_Pawn7", 6, 6);
        Piece player2_pawn8 = new Piece(player2, player2 + "_Pawn8", 6, 7);

        //set on the board
        String[][] gameboard = new String[8][8];
        gameboard[0][0] = player1_Rook1.getPieceName();
        gameboard[1][0] = player1_pawn1.getPieceName();
        gameboard[0][1] = player1_Knight1.getPieceName();
        gameboard[1][1] = player1_pawn2.getPieceName();
        gameboard[0][2] = player1_Bishop1.getPieceName();
        gameboard[1][2] = player1_pawn3.getPieceName();
        gameboard[0][3] = player1_Queen.getPieceName();
        gameboard[1][3] = player1_pawn4.getPieceName();
        gameboard[0][4] = player1_King.getPieceName();
        gameboard[1][4] = player1_pawn5.getPieceName();
        gameboard[0][5] = player1_Bishop2.getPieceName();
        gameboard[1][5] = player1_pawn6.getPieceName();
        gameboard[0][6] = player1_Knight2.getPieceName();
        gameboard[1][6] = player1_pawn7.getPieceName();
        gameboard[0][7] = player1_Rook2.getPieceName();
        gameboard[1][7] = player1_pawn8.getPieceName();

        gameboard[7][0] = player2_Rook1.getPieceName();
        gameboard[6][0] = player2_pawn1.getPieceName();
        gameboard[7][1] = player2_Knight1.getPieceName();
        gameboard[6][1] = player2_pawn2.getPieceName();
        gameboard[7][2] = player2_Bishop1.getPieceName();
        gameboard[6][2] = player2_pawn3.getPieceName();
        gameboard[7][3] = player2_King.getPieceName();
        gameboard[6][3] = player2_pawn4.getPieceName();
        gameboard[7][4] = player2_Queen.getPieceName();
        gameboard[6][4] = player2_pawn5.getPieceName();
        gameboard[7][5] = player2_Bishop2.getPieceName();
        gameboard[6][5] = player2_pawn6.getPieceName();
        gameboard[7][6] = player2_Knight2.getPieceName();
        gameboard[6][6] = player2_pawn7.getPieceName();
        gameboard[7][7] = player2_Rook2.getPieceName();
        gameboard[6][7] = player2_pawn8.getPieceName();

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j] == null) {
                    gameboard[i][j] = "(" + i + "," + j + ")";
                }
            }
        }

        //start the game
        boolean king_alive =true;
        while(king_alive){
            //show current board
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    System.out.print(" | "+gameboard[i][j]);
                }
                System.out.print(" |"+"\n");
            }
            boolean player1_time = true;
            while(player1_time) {
                System.out.print(player1 + ", what do you want to move? Enter the piece's coordinate(ex. 2,4) ");
                String[] inputCo = input.nextLine().split(",");
                int x = Integer.parseInt(inputCo[0]);
                int y = Integer.parseInt(inputCo[1]);
                if ((x<0 && x>7)||(y<0 && y>7)){
                    System.out.println("You put the wrong coordinate. Try again.");
                    continue;
                }else if(gameboard[x][y].equals(player1_pawn1.getPieceName())){
                    //ask user where to move the piece
                    boolean cannot_move=true;
                    while(cannot_move) {
                        System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,5) ");
                        String[] destinationCo = input.nextLine().split(",");
                        gameboard = player1_pawn1.calPawnA(gameboard, x, y, Integer.parseInt(destinationCo[0]), Integer.parseInt(destinationCo[1]));
                        if(gameboard[x][y].equals(player1_pawn1.getPieceName())){
                            System.out.println("Wrong destination. Try again.");
                            continue;
                        }else{
                            cannot_move = false;
                            player1_time =false;
                        }
                    }
                }
            }

        }
    }
}
