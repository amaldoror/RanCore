package org.rancore.ad.ad_1_3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Das Sammeln der Eingabe in einem Bag und
 * das anschließende Iterieren ermöglicht
 * verschiedene statistische Auswertungen.
 * Die Reihenfolge der Elemente ist dabei nicht von
 * Interesse. <br><br>
 * Bags (dt. Multimenge) sind Sammlungen, <br>
 * - die das Entfernen der Elemente nicht unterstützen <br>
 * - deren Iterator keine Reihenfolge für die Elemente zusichert <br>
 * - die im Gegensatz zu Mengen gleiche Elemente mehrfach enthalten können <br>
 * Zweck: Clients können Elemente sammeln und darüber iterieren.<br>
 */
public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());
        int N = numbers.size();
        double sum = 0.0;
        for (double d : numbers)
            sum += d;
        double mean = sum/N;
        sum = 0.0;
        for (double d : numbers)
            sum += (d-mean)*(d-mean);
        double std = Math.sqrt(sum/(N-1));
        StdOut.printf("Mittelwert: %.2f\n",mean);
        StdOut.printf("Standard Abweichung: %.2f\n",std);
    }
}
