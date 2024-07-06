package rancore.game;


/**
 * Die Klasse ChessEngine implementiert eine grundlegende Schachengine.
 * <p>
 * Das Ziel ist es, ein Schachspiel zu simulieren, in dem zwei Spieler gegeneinander antreten.
 * Die Klasse ermöglicht es, Züge zu machen, das Brett darzustellen und den Zustand des Spiels zu überprüfen.
 */
public class ChessEngine {
    private static char[][] board = new char[8][8];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        // TODO: Schachzüge und Spielzustandsüberprüfungen
    }

    /**
     * Initialisiert das Schachbrett mit den Standardpositionen der Figuren.
     */
    private static void initializeBoard() {
        // Weiße Figuren
        board[0][0] = board[0][7] = 'R'; // Türme
        board[0][1] = board[0][6] = 'N'; // Springer
        board[0][2] = board[0][5] = 'B'; // Läufer
        board[0][3] = 'Q'; // Dame
        board[0][4] = 'K'; // König
        for (int i = 0; i < 8; i++) {
            board[1][i] = 'P'; // Bauern
        }

        // Schwarze Figuren
        board[7][0] = board[7][7] = 'r'; // Türme
        board[7][1] = board[7][6] = 'n'; // Springer
        board[7][2] = board[7][5] = 'b'; // Läufer
        board[7][3] = 'q'; // Dame
        board[7][4] = 'k'; // König
        for (int i = 0; i < 8; i++) {
            board[6][i] = 'p'; // Bauern
        }

        // Leere Felder
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Druckt das Schachbrett auf die Konsole.
     */
    private static void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // TODO: Methoden für Züge, Überprüfung von Schach und Matt, etc.
}
