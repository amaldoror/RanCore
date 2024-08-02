package org.rancore.ad.ad_1_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.IOException;

public class UFDoublingRatio {

    public static void main(String[] args) throws IOException {
        // Einlesen der Versuchsgrößen
        int type = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        int trials = Integer.parseInt(args[2]);

        // Arrays für die Protokolldaten
        int[] edges = new int[trials];
        double[] times = new double[trials];

        // Erster Durchlauf
        count(type, N, 0, edges, times);

        // Versuch wiederholen
        for (int t = 1; t < trials; t++) {
            // Dabei jeweils N verdoppeln
            N += N;
            count(type, N, t, edges, times);

            // Testergebnis pro Versuch
            StdOut.printf("N=%d edges=%d time=%1.3f time ratio=%1.3f\n", N, edges[t], times[t], times[t] / times[t - 1]);
        }
    }

    private static void count(int type, int N, int t, int[] edges, double[] times) {
        // Referenz auf die Implementierung
        UF uf = UnionFindFactory.getInstance(type, N);

        String filename = UFDoublingRatioDataGenerator.filename(N);

        // Testdaten lesen
        In data = new In(filename);
        int[] pairs = data.readAllInts();
        int index = 0;

        // Stoppuhr starten
        Stopwatch timer = new Stopwatch();

        // Alle Paare müssen in einer Komponente liegen!
        while (uf.count() > 1) {
            // Paare lesen
            int p = pairs[index++];
            int q = pairs[index++];

            // Hier ist die Prüfung auf connected ausgelassen, um die
            // Testergebnisse nicht zu verfälschen
            // Die Testdaten enthalten nur Paare, die jeweils in unterschiedlichen Komponenten liegen

            // Paare vereinigen
            uf.union(p, q);
                // Anzahl der Kanten für den Versuch protokollieren
                edges[t]++;
        }

        // Zeit für den Versuch protokollieren
        times[t] = timer.elapsedTime();
    }
}

