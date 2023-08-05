package Main;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean isGameOver;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        isGameOver = false;
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X goes first. Players take turns entering row and column numbers (0-2).");

        while (!isGameOver) {
            printBoard();
            makeMove();
            checkGameOver();
            switchPlayer();
        }

        printBoard();
        if (isDraw()) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + currentPlayer + " wins!");
        }

        scanner.close();
    }

    private void makeMove() {
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter row (0-2): ");
            row = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter column (0-2): ");
            col = scanner.nextInt();
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("Invalid move. Row and column should be between 0 and 2.");
            return false;
        }

        if (board[row][col] != '-') {
            System.out.println("Invalid move. The cell is already occupied.");
            return false;
        }

        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void checkGameOver() {
        if (checkWin() || isBoardFull()) {
            isGameOver = true;
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            // Check columns
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDraw() {
        return !checkWin() && isBoardFull();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}
