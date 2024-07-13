package org.rancore.riddle;

/**
 * Die Klasse NQueens löst das N-Damen-Problem.
 * Das Ziel ist es, N Damen auf einem NxN-Schachbrett so zu platzieren,
 * dass keine zwei Damen sich gegenseitig bedrohen.
 */
public class NQueens {

    private int[] board;
    private int N;

    /**
     * Konstruktor zur Initialisierung des N-Damen-Problems.
     *
     * @param N Die Anzahl der Damen sowie die Größe des Schachbretts (NxN).
     */
    public NQueens(int N) {
        this.N = N;
        this.board = new int[N];
    }

    /**
     * Die Hauptmethode zum Lösen des N-Damen-Problems.
     *
     * @param args Die Eingabeparameter (nicht verwendet).
     */
    public static void main(String[] args) {
        int N = 8; // Beispiel für ein 8x8 Schachbrett
        NQueens nQueens = new NQueens(N);
        if (nQueens.solve()) {
            nQueens.printSolution();
        } else {
            System.out.println("Es gibt keine Lösung für " + N + " Damen.");
        }
    }

    /**
     * Löst das N-Damen-Problem durch rekursives Backtracking.
     *
     * @return true, wenn eine Lösung gefunden wurde, andernfalls false.
     */
    public boolean solve() {
        return solve(0);
    }

    /**
     * Rekursive Hilfsmethode zum Platzieren der Damen.
     *
     * @param row Die aktuelle Zeile, in der eine Dame platziert werden soll.
     * @return true, wenn die Platzierung möglich ist, andernfalls false.
     */
    private boolean solve(int row) {
        if (row == N) {
            return true; // Alle Damen wurden erfolgreich platziert
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row] = col; // Platziere die Dame in der aktuellen Spalte
                if (solve(row + 1)) {
                    return true;
                }
            }
        }
        return false; // Keine gültige Platzierung möglich
    }

    /**
     * Überprüft, ob es sicher ist, eine Dame an die Position (row, col) zu setzen.
     *
     * @param row Die Zeile, in der die Dame platziert werden soll.
     * @param col Die Spalte, in der die Dame platziert werden soll.
     * @return true, wenn die Position sicher ist, andernfalls false.
     */
    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || // Überprüfe die gleiche Spalte
                    board[i] - i == col - row || // Überprüfe die Hauptdiagonale
                    board[i] + i == col + row) { // Überprüfe die Nebendiagonale
                return false;
            }
        }
        return true;
    }

    /**
     * Druckt die Lösung des N-Damen-Problems auf die Konsole.
     */
    private void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
