package org.rancore.aleph;

/**
 * Die Klasse SubstringCounter zählt die Anzahl der Teilstrings in einem gegebenen String.
 * <p>
 * Das Ziel ist es, die Gesamtzahl der verschiedenen Teilstrings zu bestimmen. Die Lösung verwendet eine doppelte Schleife,
 * um alle möglichen Teilstrings zu generieren und zu zählen.
 */
public class SubstringCounter {

    /**
     * Zählt die Anzahl der Teilstrings in einem String.
     *
     * @param s Der Eingabestring.
     * @return Die Anzahl der Teilstrings.
     */
    public static int countSubstrings(String s) {
        int n = s.length();
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println("Anzahl der Teilstrings: " + countSubstrings(s));
    }
}
