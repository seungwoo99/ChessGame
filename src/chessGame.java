//By - Seung Woo Choi
import java.util.Scanner;

public class chessGame {
    //fields
    public static int integerY;
    public static String AlphabetY;

    //main method
    public static void main(String[] args) {
        //ask users' name
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String player1 = input.nextLine();
        System.out.print("What is your opponent's name? ");
        String player2 = input.nextLine();

        // set player1's pieces
        Piece player1_Rook = new Piece(player1, player1 + "_Rook");
        Piece player1_Knight = new Piece(player1, player1 + "_Knight");
        Piece player1_Bishop = new Piece(player1, player1 + "_Bishop");
        Piece player1_Queen = new Piece(player1, player1 + "_Queen");
        Piece player1_King = new Piece(player1, player1 + "_King");
        Piece player1_Pawn = new Piece(player1, player1 + "_Pawn");

        //set player2's pieces
        Piece player2_Rook = new Piece(player2, player2 + "_Rook");
        Piece player2_Knight = new Piece(player2, player2 + "_Knight");
        Piece player2_Bishop = new Piece(player2, player2 + "_Bishop");
        Piece player2_Queen = new Piece(player2, player2 + "_Queen");
        Piece player2_King = new Piece(player2, player2 + "_King");
        Piece player2_Pawn = new Piece(player2, player2 + "_Pawn");

        //set on the board
        Piece[][] gameboard = new Piece[8][8];
        gameboard[0][0] = gameboard[0][7] = player1_Rook;
        gameboard[0][1] = gameboard[0][6] = player1_Knight;
        gameboard[0][2] = gameboard[0][5] = player1_Bishop;
        gameboard[0][3] = player1_Queen;
        gameboard[0][4] = player1_King;
        for (int i = 0; i < 8; i++) {
            gameboard[1][i] = player1_Pawn;
            gameboard[6][i] = player2_Pawn;
        }
        gameboard[7][0] = gameboard[7][7] = player2_Rook;
        gameboard[7][1] = gameboard[7][6] = player2_Knight;
        gameboard[7][2] = gameboard[7][5] = player2_Bishop;
        gameboard[7][3] = player2_Queen;
        gameboard[7][4] = player2_King;

        //set empty places
        Piece emptyPlace;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j] == null) {
                    emptyPlace = new Piece(i, j);
                    gameboard[i][j] = emptyPlace;
                }
            }
        }

        //start the game
        boolean king_alive = true;
        while (king_alive) {
            //show current board
            for (int i = 0; i < 8; i++) {
                System.out.print(8 - i);
                for (int j = 0; j < 8; j++) {
                    System.out.print(" | " + gameboard[i][j].getPieceName());
                }
                System.out.print(" |" + "\n");
            }
            System.out.println("     A    |    B    |    C    |    D    |    E    |    F    |     G    |    H");

            //player1's time
            boolean player1_time = true;
            while (player1_time) {
                System.out.print(player1 + ", what do you want to move? Enter the piece's coordinate(ex. 2,C) ");
                String[] inputCo = input.nextLine().split(",");
                int x = 8-Integer.parseInt(inputCo[0]);
                int y = changeYtoInt(inputCo[1]);
                //if user input wrong coordinate, go back to while loop and ask again
                if ((x < 0 && x > 7) || (y < 0 && y > 7)) {
                    System.out.println("You put the wrong coordinate. Try again.");
                    continue;
                    //if Pawn is selected
                } else if (gameboard[x][y].getPieceName().equals(player1_Pawn.getPieceName())) {
                    //ask user where to move the piece
                    boolean cannot_move = true;
                    while (cannot_move) {
                        System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                        String[] destinationCo = input.nextLine().split(",");
                        int destinationY = changeYtoInt(destinationCo[1]);
                        gameboard = player1_Pawn.calPawnA(gameboard, x, y, 8-Integer.parseInt(destinationCo[0]), destinationY);
                        if (gameboard[x][y].getPieceName().equals(player1_Pawn.getPieceName())) {
                            System.out.println("Wrong destination. Try again.");
                            continue;
                        } else {
                            cannot_move = false;
                            player1_time = false;
                        }
                    }
                    //if Rook is selected
                }else if(gameboard[x][y].getPieceName().equals(player1_Rook.getPieceName())){
                    boolean cannot_move=true;
                    while(cannot_move) {
                        System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                        String[] destinationCo = input.nextLine().split(",");
                        int destinationY = changeYtoInt(destinationCo[1]);
                        gameboard = player1_Rook.calRookA(gameboard, x, y, 8-Integer.parseInt(destinationCo[0]), destinationY);
                        if(gameboard[x][y].getPieceName().equals(player1_Rook.getPieceName())){
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
    public static String changeYtoString(int inputY){
        if (inputY == 0){
            AlphabetY = "A";
        }else if(inputY == 1){
            AlphabetY = "B";
        }else if(inputY == 2){
            AlphabetY = "C";
        }else if(inputY == 3){
            AlphabetY = "D";
        }else if(inputY == 4){
            AlphabetY = "E";
        }else if(inputY == 5){
            AlphabetY = "F";
        }else if(inputY == 6){
            AlphabetY = "G";
        }else{
            AlphabetY = "H";
        }
        return AlphabetY;
    }
    public static int changeYtoInt (String inputY){
        if (inputY.equals("A")) {
            integerY = 0;
        } else if (inputY.equals("B")) {
            integerY = 1;
        } else if (inputY.equals("C")) {
            integerY = 2;
        } else if (inputY.equals("D")) {
            integerY = 3;
        } else if (inputY.equals("E")) {
            integerY = 4;
        } else if (inputY.equals("F")) {
            integerY = 5;
        } else if (inputY.equals("G")) {
            integerY = 6;
        } else if (inputY.equals("H")) {
            integerY = 7;
        } else {
            integerY = -1;
        }
        return integerY;
    }
}
