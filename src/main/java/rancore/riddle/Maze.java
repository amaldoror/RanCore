package rancore.riddle;

import java.util.*;

/**
 * Das Labyrinth-Suchproblem besteht darin, einen Weg von einem Startpunkt zu einem Zielpunkt in einem gegebenen Labyrinth zu finden.
 * <p>
 * Das Labyrinth wird durch ein 2D-Array dargestellt, wobei 0 für offene Felder und 1 für Wände steht. Der Weg sollte nur durch
 * offene Felder führen und sich innerhalb der Grenzen des Labyrinths bewegen.
 * <p>
 * Ziel:
 *     Finde den kürzesten Weg vom Startpunkt zum Zielpunkt im Labyrinth.
 * <p>
 * Algorithmen:
 *     Es gibt mehrere Algorithmen, um das Labyrinth-Suchproblem zu lösen, darunter:
 *     1. Tiefensuche (Depth-First Search, DFS): Untersucht so tief wie möglich entlang jedes Astes bevor es zurückgeht.
 *     2. Breitensuche (Breadth-First Search, BFS): Untersucht alle Nachbarn auf der aktuellen Tiefe bevor es in die nächste Tiefe geht.
 *     3. A*-Algorithmus: Eine informierte Suchstrategie, die die besten Kandidaten für die nächste Erweiterung auswählt.
 * <p>
 * Die Breitensuche (BFS) eignet sich besonders gut für die Suche nach dem kürzesten Weg in ungewichteten Graphen wie einem Labyrinth.
 * Sie verwendet eine Warteschlange, um alle möglichen Positionen systematisch zu durchsuchen und die kürzeste Entfernung zu gewährleisten.
 * <p>
 * Der BFS-Algorithmus arbeitet wie folgt: <br>
 *     1. Beginne am Startpunkt und markiere ihn als besucht.<br>
 *     2. Füge den Startpunkt in eine Warteschlange ein.<br>
 *     3. Solange die Warteschlange nicht leer ist:<br>
 *         a. Entferne den Vorderpunkt aus der Warteschlange.<br>
 *         b. Untersuche alle benachbarten Felder.<br>
 *         c. Wenn ein Nachbar das Ziel ist, wird der Weg rekonstruiert und zurückgegeben.<br>
 *         d. Wenn ein Nachbar ein offenes Feld ist und noch nicht besucht wurde,
 *         markiere es als besucht und füge es zur Warteschlange hinzu.<br>
 *     4. Wenn die Warteschlange leer ist und das Ziel nicht erreicht wurde, existiert kein Pfad.<br>
 * <p>
 * Der Rückgabewert des Algorithmus ist eine Liste von Koordinaten, die den gefundenen Pfad darstellen,
 * oder eine leere Liste, wenn kein Pfad existiert.
 */
public class Maze {

    private static final int[] ROW_MOVES = {-1, 1, 0, 0}; // Bewegungen: oben, unten, links, rechts
    private static final int[] COL_MOVES = {0, 0, -1, 1};

    /**
     * Die Methode löst das Labyrinth-Suchproblem.
     *
     * @param maze Das Labyrinth als 2D-Array, wobei 0 für offene Felder und 1 für Wände steht.
     * @param startRow Die Startzeile.
     * @param startCol Die Startspalte.
     * @param endRow Die Zielzeile.
     * @param endCol Die Zielspalte.
     * @return Eine Liste der Koordinaten des Pfades vom Startpunkt zum Zielpunkt.
     */
    public List<int[]> solveMaze(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        int[][] parent = new int[numRows * numCols][2];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        parent[startRow * numCols + startCol] = new int[]{-1, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                return constructPath(parent, startRow, startCol, endRow, endCol, numCols);
            }

            for (int i = 0; i < 4; i++) {
                int newRow = row + ROW_MOVES[i];
                int newCol = col + COL_MOVES[i];

                if (isValidMove(maze, newRow, newCol, visited)) {
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    parent[newRow * numCols + newCol] = new int[]{row, col};
                }
            }
        }

        return Collections.emptyList(); // Kein Pfad gefunden
    }

    /**
     * Überprüft, ob der nächste Zug gültig ist.
     *
     * @param maze Das Labyrinth als 2D-Array.
     * @param row Die Zeile des Zuges.
     * @param col Die Spalte des Zuges.
     * @param visited Ein 2D-Array der besuchten Positionen.
     * @return true, wenn der Zug gültig ist, andernfalls false.
     */
    private boolean isValidMove(int[][] maze, int row, int col, boolean[][] visited) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length &&
                maze[row][col] == 0 && !visited[row][col];
    }

    /**
     * Konstruiert den Pfad vom Startpunkt zum Zielpunkt.
     *
     * @param parent Ein Array, das die Vorgängerkoordinaten jedes Punktes speichert.
     * @param startRow Die Startzeile.
     * @param startCol Die Startspalte.
     * @param endRow Die Zielzeile.
     * @param endCol Die Zielspalte.
     * @param numCols Die Anzahl der Spalten im Labyrinth.
     * @return Eine Liste der Koordinaten des Pfades.
     */
    private List<int[]> constructPath(int[][] parent, int startRow, int startCol, int endRow, int endCol, int numCols) {
        List<int[]> path = new LinkedList<>();
        for (int[] at = new int[]{endRow, endCol}; at[0] != -1; at = parent[at[0] * numCols + at[1]]) {
            path.add(0, at);
        }
        return path;
    }

    /**
     * Die Hauptmethode zum Testen der Maze Klasse.
     *
     * @param args Die Eingabeparameter (nicht verwendet).
     */
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int startRow = 0, startCol = 0;
        int endRow = 4, endCol = 5;

        Maze solver = new Maze();
        List<int[]> path = solver.solveMaze(maze, startRow, startCol, endRow, endCol);

        if (path.isEmpty()) {
            System.out.println("Kein Pfad gefunden.");
        } else {
            System.out.println("Gefundener Pfad:");
            for (int[] position : path) {
                System.out.println(Arrays.toString(position));
            }
        }
    }
}
