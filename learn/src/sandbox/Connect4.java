package sandbox;

import java.util.Arrays;
import java.util.Random;

public class Connect4 {

    static int MAX_ROWS = 6;
    static int MAX_COLS = 7;
    // Simple Integer based board
    // 0 -> Free
    // 1 -> Player 1 occupied.
    // 2 -> Player 2 occupied.
    static int  board [][] = new int [MAX_ROWS][MAX_COLS];

    static boolean player1Turn = true;

    static void drawBoard () {
        // start at the top left most (0,0) and
        // print the columns
        // go to next row till the last row
        for (int row = 0; row < MAX_ROWS; row++) {
                StringBuilder sb = new StringBuilder();
            for (int col = 0; col < MAX_COLS; col++) {
                int val = board[row][col];
                sb.append(" ");
                if (0 == val)
                    sb.append(" ");
                else if (1 == val)
                    sb.append("X");
                else
                    sb.append("O");
            }
            // Row print;
            System.out.println(sb.toString());
        }
    }

    static int getRandomColumn () {
        return ((Math.abs(new Random().nextInt())) % MAX_COLS);
    }

    // Return true if this makes the player win
    // false if valid but not winning.
    // Throw an exception to let caller retry for invalid input.
    static boolean playBoard (int player, int col)  throws Exception{
        Exception invalidException = new Exception();

        for (int row = MAX_ROWS - 1; row >= 0; row--) {
            if (0 == board[row][col]) {
                board[row][col] = player;

                return false;
            }
        }
        throw invalidException;
    }

    //
    static int isRowWinDir(boolean left, int row, int col, int matchCount) {
        int matchedCol = 0;

        for (int i = col; i < matchCount; i+ ) {
            int newCol = left ? (col - 1) : (col + 1);
            if ((col < 0) || (col > MAX_COLS))
                break;

            if (board[row][newCol] == board[row][col])
                matchedCol++;
            if (matchCount == matchedCol)
                break;
        }
        return matchedCol;
    }

    static boolean isRowWin (int row, int col) {
        int matchedCount = 0;

        // Go Right
        matchedCount += isRowWinDir(false, row, col, 4);
        if (matchedCount < 4)
            matchedCount += isRowWinDir(true, row, col, 4 - matchedCount);
        return (matchedCount == 4);

    }

    static boolean isBoardFull ()
    {
        // Any place has unoccupied cell is not full
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLS; col++) {
                if (0 == board[row][col])
                    return false;
            }
        }
        return true;
    }

    public static void main (String args []) {

        // While Board is not filled
        // OR
        // One of the players has not won
        // keep generating a random column and
        // present the input for each player
        // toggling the turns.
        while (true) {
            boolean winner = false;


            do {
                int col = getRandomColumn();
                try {

                    winner = playBoard(player1Turn ? 1 : 2, col);
                    drawBoard();
                } catch (Exception e)  {
                    System.out.println("Cannot use the column: " + col + ", trying another column");
                    continue;
                }
                break;
            } while (true);

            if (winner) {
                System.out.println("Player " + player1Turn + ", WINS!!!");
                break;
            }

            // Board full ?
            if (isBoardFull()) {
                System.out.println("Board is full");
                break;
            }

            // Toggle the player turn for next run
            player1Turn = !player1Turn;
        }
    }
}
