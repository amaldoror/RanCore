package rancore.riddle;

/**
 * Das <b>Josephus-Problem</b> ist ein theoretisches Problem aus der Informatik und Mathematik, das wie folgt beschrieben wird:
 * <p>
 * Es gibt n Personen, die in einem Kreis angeordnet sind und jede k-te Person wird eliminiert, bis nur noch eine Person übrig bleibt.
 * Das Ziel des Problems ist es, die Position der letzten verbleibenden Person zu bestimmen.
 * <p>
 * Das Problem kann rekursiv oder iterativ gelöst werden. Die rekursive Lösung verwendet die Formel:
 * <p>
 *     <b>Josephus(n, k) = (Josephus(n-1, k) + k) % n</b>
 * <p>
 * mit der Basisbedingung Josephus(1, k) = 0. Diese Formel berechnet die Position der letzten verbleibenden Person für n Personen
 * und jeden k-ten Eliminationsschritt.
 * <p>
 * Die iterative Lösung kann mit einer Schleife berechnet werden, die die Position der letzten verbleibenden Person bestimmt,
 * indem sie schrittweise die Position jeder eliminierenden Person berechnet.
 * <p>
 * Der Rückgabewert beider Lösungen ist die Position der sicheren Person im nullbasierten Index. Um die Position im einbasierten
 * Index zu erhalten, muss 1 zur Lösung addiert werden.
 */
public class Josephus {

    /**
     * Hauptmethode zum Testen der JosephusRecursive Klasse.
     *
     * @param args Die Eingabeparameter (nicht verwendet).
     */
    public static void main(String[] args) {
        int n = 7; // Anzahl der Personen im Kreis
        int k = 3; // Jede k-te Person wird eliminiert

        System.out.println("n = " + n + ", k = " + k);
        int safePosition = josephusRecursive(n, k);
        System.out.println("[Rekursiv] Der sichere Platz ist " + (safePosition + 1)); // +1, um auf 1-basierte Indexierung zu wechseln


        safePosition = josephusIterative(n, k);
        System.out.println("[Iterativ] Der sichere Platz ist " + (safePosition + 1)); // +1, um auf 1-basierte Indexierung zu wechseln

    }

    /**
     * Löst das Josephus-Problem rekursiv.
     *
     * @param n Die Anzahl der Personen im Kreis.
     * @param k Jede k-te Person wird eliminiert.
     * @return Der sichere Platz, der übrig bleibt.
     */
    public static int josephusRecursive(int n, int k) {
        if (n == 1) {
            return 0;
        } else {
            return (josephusRecursive(n - 1, k) + k) % n;
        }
    }

    /**
     * Löst das Josephus-Problem iterativ.
     *
     * @param n Die Anzahl der Personen im Kreis.
     * @param k Jede k-te Person wird eliminiert.
     * @return Der sichere Platz, der übrig bleibt.
     */
    public static int josephusIterative(int n, int k) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = (result + k) % i;
        }
        return result;
    }
}
