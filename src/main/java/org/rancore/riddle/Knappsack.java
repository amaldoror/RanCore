package org.rancore.riddle;

/**
 * Die Klasse Knappsack implementiert das Knappe-Rucksack-Problem.
 * Das Ziel ist es, eine Teilmenge von Gegenständen auszuwählen, die in einen Rucksack passen
 * und einen maximalen Wert haben, unter Berücksichtigung des Gewichts.
 */
public class Knappsack {

    /**
     * Löst das Knappe-Rucksack-Problem mit dynamischem Programmieren.
     *
     * @param W Die maximale Kapazität des Rucksacks.
     * @param weights Ein Array, das die Gewichte der Gegenstände enthält.
     * @param values Ein Array, das die Werte der Gegenstände enthält.
     * @param n Die Anzahl der Gegenstände.
     * @return Der maximale Wert, der in den Rucksack passt.
     */
    public static int knappsack(int W, int[] weights, int[] values, int n) {
        int[][] K = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    K[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    K[i][w] = Math.max(values[i - 1] + K[i - 1][w - weights[i - 1]], K[i - 1][w]);
                } else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }
        return K[n][W];
    }

    /**
     * Hauptmethode zum Testen der Knappsack Klasse.
     *
     * @param args Die Eingabeparameter (nicht verwendet).
     */
    public static void main(String[] args) {
        int[] values = {60, 100, 120}; // Werte der Gegenstände
        int[] weights = {10, 20, 30}; // Gewichte der Gegenstände
        int W = 50; // Maximale Kapazität des Rucksacks
        int n = values.length; // Anzahl der Gegenstände

        int maxValue = knappsack(W, weights, values, n);
        System.out.println("Der maximale Wert, der in den Rucksack passt, beträgt " + maxValue);
    }
}
