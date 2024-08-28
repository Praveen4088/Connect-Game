import java.util.*;

public class connectgame {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_SLOT = '-';
    private static char[][] board = new char[ROWS][COLUMNS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (1-7): ");
            int column = scanner.nextInt() - 1;

            if (isValidMove(column) && makeMove(column)) {
                displayBoard();
                if (isWinner()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }
                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                // Switch to the other player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY_SLOT;
            }
        }
    }

    private static void displayBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private static boolean isValidMove(int column) {
        return column >= 0 && column < COLUMNS && board[ROWS - 1][column] == EMPTY_SLOT;
    }

    private static boolean makeMove(int column) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][column] == EMPTY_SLOT) {
                board[row][column] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    private static boolean isWinner() {
        // Check horizontally
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (checkConsecutive(board[row][col], board[row][col + 1], board[row][col + 2], board[row][col + 3])) {
                    return true;
                }
            }
        }

        // Check vertically
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (checkConsecutive(board[row][col], board[row + 1][col], board[row + 2][col], board[row + 3][col])) {
                    return true;
                }
            }
        }

        // Check diagonally (down-right)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (checkConsecutive(board[row][col], board[row + 1][col + 1], board[row + 2][col + 2], board[row + 3][col + 3])) {
                    return true;
                }
            }
        }

        // Check diagonally (up-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (checkConsecutive(board[row][col], board[row - 1][col + 1], board[row - 2][col + 2], board[row - 3][col + 3])) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkConsecutive(char a, char b, char c, char d) {
        return a != EMPTY_SLOT && a == b && b == c && c == d;
    }


    private static boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[ROWS - 1][col] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }
}
