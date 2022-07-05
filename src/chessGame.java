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
        System.out.print("What is your name?(Max Length: 7) ");
        String player1 = input.nextLine();
        if(player1.length() > 7){
            player1 = player1.substring(0,7);
        }
        System.out.print("What is your opponent's name?(Max Length: 7) ");
        String player2 = input.nextLine();
        if(player2.length() > 7){
            player2 = player2.substring(0,7);
        }

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
            //find the longest name between player1 and player2
            int name_length = nameLength(player1, player2);

            //show current board
            showBoard(gameboard, name_length);

            //player1's time
            boolean player1_time = true;
            //check player1's king is alive
            if(KingDead(player1, gameboard)){
                System.out.println(player1 + "'s king is dead. "+ player2 + " won!");
                king_alive = false;
                player1_time = false;
                continue;
            }
            while (player1_time) {
                try{
                    //ask user player1 to select his piece
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
                            gameboard = player1_Pawn.calPawnA(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            //if the piece is not moved (The piece is not moved when the destination is wrong)
                            if (gameboard[x][y].getPieceName().equals(player1_Pawn.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if Rook is selected
                    } else if (gameboard[x][y].getPieceName().equals(player1_Rook.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player1_Rook.calRook(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player1_Rook.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if Knight is selected
                    } else if (gameboard[x][y].getPieceName().equals(player1_Knight.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player1_Rook.calKnight(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player1_Knight.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if Bishop is selected
                    } else if (gameboard[x][y].getPieceName().equals(player1_Bishop.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player1_Rook.calBishop(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player1_Bishop.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if Queen is selected
                    } else if (gameboard[x][y].getPieceName().equals(player1_Queen.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            if (x == 8 - Integer.parseInt(destinationCo[0]) || y == destinationY) {
                                gameboard = player1_Rook.calRook(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            } else {
                                gameboard = player1_Rook.calBishop(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            }

                            if (gameboard[x][y].getPieceName().equals(player1_Queen.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if King is selected
                    } else if (gameboard[x][y].getPieceName().equals(player1_King.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player1 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player1_Rook.calKing(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player1_King.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player1_time = false;
                            }
                        }
                        //if nothing selected
                    } else {
                        System.out.println("You chose the wrong one. Try again.");
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("You put the wrong coordinate. Try again.");
                }catch(NumberFormatException e){
                    System.out.println("You put the wrong coordinate. Try again.");
                }
                //break line
                System.out.println("");
            }

            //show Board
            showBoard(gameboard, name_length);

            //player2's time
            boolean player2_time = true;
            while(player2_time){
                //check player2's king is alive
                if(KingDead(player2, gameboard)){
                    //announce that player2's king is dead
                    System.out.println(player2 + "'s king is dead. "+ player1 + " won!");
                    player2_time = false;
                    king_alive = false;
                    continue;
                }

                //ask user player2 to select his piece
                try{
                    System.out.print(player2 + ", what do you want to move? Enter the piece's coordinate(ex. 2,C) ");
                    String[] inputCo = input.nextLine().split(",");
                    int x = 8-Integer.parseInt(inputCo[0]);
                    int y = changeYtoInt(inputCo[1]);
                    //if user input wrong coordinate, go back to while loop and ask again
                    if ((x < 0 && x > 7) || (y < 0 && y > 7)) {
                        System.out.println("You put the wrong coordinate. Try again.");
                        continue;

                        //if Pawn is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_Pawn.getPieceName())) {
                        //ask user where to move the piece
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player2_Pawn.calPawnB(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            //if the piece is not moved (The piece is not moved when the destination is wrong)
                            if (gameboard[x][y].getPieceName().equals(player2_Pawn.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if Rook is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_Rook.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player2_Rook.calRook(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player2_Rook.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if Knight is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_Knight.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player2_Rook.calKnight(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player2_Knight.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if Bishop is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_Bishop.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player2_Rook.calBishop(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player2_Bishop.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if Queen is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_Queen.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            if (x == 8 - Integer.parseInt(destinationCo[0]) || y == destinationY) {
                                gameboard = player2_Rook.calRook(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            } else {
                                gameboard = player2_Rook.calBishop(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            }

                            if (gameboard[x][y].getPieceName().equals(player2_Queen.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if King is selected
                    } else if (gameboard[x][y].getPieceName().equals(player2_King.getPieceName())) {
                        boolean cannot_move = true;
                        while (cannot_move) {
                            System.out.print(player2 + ", where do you want to move? Enter the coordinate(ex. 3,D) ");
                            String[] destinationCo = input.nextLine().split(",");
                            int destinationY = changeYtoInt(destinationCo[1]);
                            gameboard = player2_Rook.calKing(gameboard, x, y, 8 - Integer.parseInt(destinationCo[0]), destinationY);
                            if (gameboard[x][y].getPieceName().equals(player2_King.getPieceName())) {
                                System.out.println("Wrong destination. Try again.");
                                continue;
                            } else {
                                cannot_move = false;
                                player2_time = false;
                            }
                        }
                        //if nothing selected
                    } else {
                        System.out.println("You chose the wrong one. Try again.");
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("You put the wrong coordinate. Try again.");
                }catch(NumberFormatException e){
                    System.out.println("You put the wrong coordinate. Try again.");
                }
            }
            //break line
            System.out.println("");
        }
        //announce that the game is over
        System.out.println("Game Over");
    }

    //method that change alphabet to integer
    public static int changeYtoInt (String inputY){
        inputY = inputY.toUpperCase();
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

    //find the longest name to make all blocks' size equal
    public static int nameLength(String player1_Name, String player2_Name){
        int longestName;
        if(player1_Name.length() > player2_Name.length()){
            longestName = player1_Name.length();
        }else{
            longestName = player2_Name.length();
        }
        return longestName+7;
    }

    //method that shows the current board
    public static void showBoard(Piece[][] board, int blockLength){
        //show current board
        System.out.println(" ".repeat((blockLength*8-10)/2)+"<Chess Board>");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                int blank = blockLength - board[i][j].getPieceName().length();
                System.out.print("|" + " ".repeat(blank/2)+ board[i][j].getPieceName()
                                     +" ".repeat(blank-blank/2));
            }
            System.out.print("|" + "\n");
        }
        System.out.println("  |"+" ".repeat(blockLength/2)+"A"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"B"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"C"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"D"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"E"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"F"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"G"+" ".repeat(blockLength-blockLength/2 -1)+"|"
                +" ".repeat(blockLength/2)+"H"+" ".repeat(blockLength-blockLength/2 -1)+"|");
    }

    //method that checks if the king is dead
    public static boolean KingDead(String playername, Piece[][] board){
        boolean king_Dead = true;
        for(int row=0; row<8; row++){
            for(int column=0; column<8; column++){
                if(board[row][column].getPieceName().equals(playername + "_King")){
                    king_Dead = false;
                }
            }
        }
        return king_Dead;
    }
}
