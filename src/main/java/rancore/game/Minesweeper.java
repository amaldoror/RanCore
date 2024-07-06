package rancore.game;

import java.util.Random;
import java.util.Scanner;

// TODO: Minesweeper Ausgabe der freien Felder überarbeiten
/**
 * Die Klasse Minesweeper implementiert das Minesweeper-Spiel.
 * <p>
 * Das Ziel ist es, alle Felder ohne Minen aufzudecken, indem Zahlen in benachbarten Feldern anzeigen,
 * wie viele Minen sich in der Nähe befinden. Wenn eine Mine aufgedeckt wird, endet das Spiel.
 */
public class Minesweeper {
    private static final int SIZE = 9;
    private static final int MINES = 10;
    private static char[][] board = new char[SIZE][SIZE];
    private static boolean[][] mines = new boolean[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        initializeBoard();
        placeMines();
        while (true) {
            printBoard();
            if (!playerMove()) {
                System.out.println("Spiel vorbei! Du hast eine Mine getroffen.");
                break;
            }
            if (checkWin()) {
                System.out.println("Glückwunsch! Du hast das Spiel gewonnen.");
                break;
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
                mines[i][j] = false;
                revealed[i][j] = false;
            }
        }
    }

    private static void placeMines() {
        Random rand = new Random();
        int placedMines = 0;
        while (placedMines < MINES) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    private static boolean playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.println("Geben Sie Ihre Bewegung ein (Reihe und Spalte): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && !revealed[row][col]) {
                break;
            } else {
                System.out.println("Ungültige Bewegung. Bitte versuchen Sie es erneut.");
            }
        }

        if (mines[row][col]) {
            return false;
        }

        reveal(row, col);
        return true;
    }

    private static void reveal(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || revealed[row][col]) {
            return;
        }

        revealed[row][col] = true;
        int mineCount = countMines(row, col);

        if (mineCount > 0) {
            board[row][col] = (char) (mineCount + '0');
        } else {
            board[row][col] = ' ';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        reveal(row + i, col + j);
                    }
                }
            }
        }
    }

    private static int countMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && mines[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!revealed[i][j] && !mines[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
