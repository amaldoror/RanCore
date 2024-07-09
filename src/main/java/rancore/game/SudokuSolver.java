package rancore.game;


/**
 * Die Klasse SudokuSolver implementiert einen Algorithmus zum Lösen von Sudoku-Rätseln.
 * <p>
 * Das Ziel ist es, ein 9x9-Sudoku-Rätsel zu lösen, indem die leeren Felder so ausgefüllt werden, dass jede Zeile,
 * jede Spalte und jeder 3x3-Block die Ziffern 1 bis 9 genau einmal enthält.
 * Der Algorithmus verwendet Backtracking, um eine gültige Lösung zu finden.
 */
public class SudokuSolver {
    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("Keine Lösung existiert.");
        }
    }

    /**
     * Löst das Sudoku-Rätsel mit Backtracking.
     *
     * @param board Das Sudoku-Board.
     * @return true, wenn eine Lösung gefunden wurde, andernfalls false.
     */
    public static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Überprüft, ob eine Zahl in einer bestimmten Position gültig ist.
     *
     * @param board Das Sudoku-Board.
     * @param row Die Zeile.
     * @param col Die Spalte.
     * @param num Die zu prüfende Zahl.
     * @return true, wenn die Zahl gültig ist, andernfalls false.
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Druckt das Sudoku-Board.
     *
     * @param board Das Sudoku-Board.
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
