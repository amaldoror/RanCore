package rancore.riddle;

/**
 * Die Klasse TowerOfHanoi implementiert das Rätsel der Türme von Hanoi.
 * Es besteht aus drei Stäben und einer Anzahl von Scheiben verschiedener Größe,
 * die von einem Startstab zu einem Zielstab bewegt werden sollen, unter Einhaltung
 * der Regel, dass nie eine größere Scheibe auf einer kleineren liegen darf.
 */
public class TowerOfHanoi {

    /**
     * Die Methode löst das Türme von Hanoi Problem für die gegebene Anzahl von Scheiben.
     *
     * @param n Die Anzahl der Scheiben, die bewegt werden sollen.
     * @param start Der Startstab, von dem die Scheiben bewegt werden sollen.
     * @param ziel Der Zielstab, auf den die Scheiben bewegt werden sollen.
     * @param hilfsstab Der Hilfsstab, der für die Zwischenspeicherung verwendet wird.
     */
    public static void solve(int n, char start, char ziel, char hilfsstab) {
        // Basisfall: Wenn nur eine Scheibe zu bewegen ist, bewege sie direkt vom Start zum Ziel.
        if (n == 1) {
            System.out.println("Bewege Scheibe 1 von " + start + " nach " + ziel);
        } else {
            // Rekursiver Fall: Verschiebe die oberen n-1 Scheiben vom Start zum Hilfsstab
            solve(n - 1, start, hilfsstab, ziel);
            // Bewege die größte Scheibe direkt vom Start zum Ziel
            System.out.println("Bewege Scheibe " + n + " von " + start + " nach " + ziel);
            // Verschiebe die n-1 Scheiben vom Hilfsstab zum Zielstab
            solve(n - 1, hilfsstab, ziel, start);
        }
    }

    /**
     * Die Hauptmethode zum Testen der TowerOfHanoi Klasse.
     *
     * @param args Die Eingabeparameter (nicht verwendet).
     */
    public static void main(String[] args) {
        int n = 3; // Anzahl der Scheiben
        char start = 'A', ziel = 'C', hilfsstab = 'B'; // Stäbe benannt als A, B, und C

        System.out.println("Löse das Rätsel der Türme von Hanoi für " + n + " Scheiben:");
        solve(n, start, ziel, hilfsstab);
    }
}
