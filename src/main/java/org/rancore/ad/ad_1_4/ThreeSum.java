package org.rancore.ad.ad_1_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Bestimme für eine Sammlung von Zahlen
 * alle Tripel, deren Summe 0 ergibt.
 */
public class ThreeSum {
    public static void main(String[] args) {
        // Beobachte für Dateien (1Kints.txt, 2Kints.txt, 4Kints.txt etc.)
        // unterschiedlicher Größe die Zunahme der Laufzeit des Programms.
        // Messungen mittels Software-Stoppuhr Stopwatch.
        int[] a = new In(args[0]).readAllInts();
        int N = a.length;
        Stopwatch timer = new Stopwatch();
        int counted = count(a);
        double time = timer.elapsedTime();
        StdOut.println(counted);
        StdOut.printf("%7d %5.1f\n", N, time);
    }

    public static int count(int[] a) {
        int n = a.length;
        int count= 0;
        for (int i=0; i <n; i++) {
            for (int j= i+1; j <n; j++){
                for (int k=j+1; k < n; k++){
                    if (a[i]+a[j]+a[k] == 0)
                        count++;
                }
            }
        }
        return count;
    }
}
