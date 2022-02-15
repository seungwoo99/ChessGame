import java.util.Scanner;

//playing chess game through cmd
public class chessGame{
    public static void main(String[] args) {
        //ask users' name
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String player1 = input.nextLine();
        System.out.print("What is your opponent's name? ");
        String player2 = input.nextLine();

        String[][] gameboard = new String[8][8];
        //first set the game
        gameboard[0][0] = player1+"_Rook1";
        gameboard[0][1] = player1+"_Knight1";
        gameboard[0][2] = player1+"_Bishop1";
        gameboard[0][3] = player1+"_King";
        gameboard[0][4] = player1+"_Queen";
        gameboard[0][5] = player1+"_Bishop2";
        gameboard[0][6] = player1+"_Knight2";
        gameboard[0][7] = player1+"_Rook2";
        for(int i=0; i<8; i++){
            gameboard[1][i] = player1+"_Pawn"+(i+1);
            gameboard[6][i] = player2+"_Pawn"+(i+1);
        }
        gameboard[7][0] = player2+"_Rook1";
        gameboard[7][1] = player2+"_Knight1";
        gameboard[7][2] = player2+"_Bishop1";
        gameboard[7][3] = player2+"_King";
        gameboard[7][4] = player2+"_Queen";
        gameboard[7][5] = player2+"_Bishop2";
        gameboard[7][6] = player2+"_Knight2";
        gameboard[7][7] = player2+"_Rook2";

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if (gameboard[i][j] == null){
                    gameboard[i][j] = "("+i+", "+j+")";
                }
            }
        }
        //player1's pieces
        String[] player1_pieces = new String[16];
        int count=0;
        for(int i=0; i<2; i++){
            for(int j=0; j<8; j++) {
                player1_pieces[count] = gameboard[i][j];
                count++;
            }
        }
        //player2's pieces
        String[] player2_pieces = new String[16];
        count = 0;
        for(int i=0; i<2; i++){
            for(int j=0; j<8; j++) {
                player2_pieces[count] = gameboard[i][j];
                count++;
            }
        }
        //show the game board
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                System.out.print(" | "+gameboard[i][j]);
            }
            System.out.println("");
        }

        //play the game
        boolean king_alive = true;
        while(king_alive) {
            boolean wrong_choice = true;
            while(wrong_choice) {
                System.out.print(player1 + ", what do you want to move?(ex. your name_pawn1) ");
                String player1_choice = input.nextLine();
                System.out.println(player1_pieces[0]);
                for(int i=0; i<16; i++) {
                    if(player1_pieces[i].equals(player1_choice)){
                        //wrong_choice = false;
                        System.out.print("Where do you want to move?(ex. 5.1) ");
                        String[] destination = input.nextLine().split(",");
                        gameboard[Integer.parseInt(destination[0])][Integer.parseInt(destination[1])] = player1_pieces[i];
                        for(int k=0; k<8; k++){
                            for(int j=0; j<8; j++) {
                                System.out.print(" | "+gameboard[k][j]);
                            }
                            System.out.println("");
                        }
                        wrong_choice = false;
                        king_alive=false;
                        break;
                    }
                }

            }

        }
    }
}