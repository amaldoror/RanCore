package org.rancore.aleph;

/**
 * Die Klasse KMPAlgorithm implementiert den Knuth-Morris-Pratt-Algorithmus (KMP) zum Finden eines Musters in einem Text.
 * <p>
 * Das Ziel ist es, das erste Vorkommen eines Musters in einem Text effizient zu finden. Der KMP-Algorithmus verwendet
 * eine Vorverarbeitung des Musters, um Rückschritte zu vermeiden und die Suchzeit zu optimieren.
 */
public class KMPAlgorithm {

    /**
     * Sucht das erste Vorkommen eines Musters in einem Text.
     *
     * @param text Der Text, in dem gesucht wird.
     * @param pattern Das Muster, das gesucht wird.
     * @return Der Index des ersten Vorkommens des Musters im Text, oder -1, wenn das Muster nicht gefunden wird.
     */
    public static int KMPSearch(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int[] lps = new int[m];
        int j = 0;

        computeLPSArray(pattern, m, lps);

        int i = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                return i - j;
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    /**
     * Berechnet das LPS (Longest Prefix Suffix) Array für das Muster.
     *
     * @param pattern Das Muster.
     * @param m Die Länge des Musters.
     * @param lps Das zu füllende LPS Array.
     */
    private static void computeLPSArray(String pattern, int m, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "abxabcabcabyjuju";
        String pattern = "juju";
        System.out.println("Das Muster wird gefunden bei Index: " + KMPSearch(text, pattern));
    }
}
