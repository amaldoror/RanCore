package org.rancore.ad.ad_2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import javax.management.ReflectionException;
import java.util.HashSet;
import java.util.Set;

/**
 * • Behauptung: <br>
 * Die Laufzeiten von Insertion-Sort und Selection-Sort sind für Arrays mit zufällig
 * angeordneten Elemente unterschiedlicher Werte quadratisch und unterscheiden sich nur in
 * einem kleinen Faktor.<br>
 * • Nachweis experimentell: <br>
 * In mehreren Experimenten ließ sich ein kleiner Faktor beobachten,
 * in der sich Insertion- und Selection-Sort unterscheidet.<br>
 * • Das Experiment zum Vergleich der Algorithmen wird mit dem SortCompare-Client
 * durchgeführt, der für N zufällig generierte Zahlenfolgen die Laufzeiten der Algorithmen misst
 * und in Relation setzt.
 */
public class SortCompare {
    public static void main(String[] args) throws ReflectionException {
        // Die zu vergleichenden Sortieralgorithmen.
        Sorter alg1 = Sorter.valueOf(args[0]);
        Sorter alg2 = Sorter.valueOf(args[1]);

        //Größe des zu sortierenden Arrays.
        int N = Integer.parseInt(args[2]);

        //Wiederholung des Versuchs.
        int repeat = Integer.parseInt(args[3]);
        compare(alg1, alg2, N, repeat);
    }

    /**
     * Führt eine Zeitmessung für alg1 und alg2 durch.<br>
     * Gibt die Zeitmessungen und das Zeitverhältnis beider Algorithmen aus.
     * @param alg1
     * @param alg2
     * @param length
     * @param repeat
     * @throws ReflectionException
     */
    private static void compare(Sorter alg1, Sorter alg2, int length, int repeat) throws ReflectionException {
        for (int i = 1; i < repeat; i++) {
            Double[] data = genData(length);
            double t1 = time(alg1, data);
            StdOut.printf("Sort of %s done in %.5f seconds\n", alg1, t1);
            double t2 = time(alg2, data.clone());
            StdOut.printf("Sort of %s done in %.5f seconds\n", alg2, t2);
            StdOut.printf("For %d random Doubles %s is %1.1f times faster than %s\n",
                    length, alg1, t2 / t1, alg2);
        }
    }

    /**
     * Generiert die Daten für die Sortierverfahren:<br>
     * Füllt ein Array der Länge length mit unterschiedlichen Zufallszahlen.
     * @param length
     * @return
     */
    private static Double[] genData(int length){
        Set<Double> controlSet = new HashSet<>();
        Double[] a = new Double[length];
        for (int i = 0; i < length; i++) {
            double d = StdRandom.uniformDouble();
            while (!controlSet.add(d)) {
                d = StdRandom.uniformDouble();
            }
            a[i] = d;
        }
        return a;
    }

    /**
     * Startet die Stoppuhr.<br>
     * Startet das Sortierverfahren und misst die Zeit.
     * @param alg
     * @param a
     * @return
     * @throws ReflectionException
     */
    private static double time(Sorter alg, Double[] a) throws ReflectionException {
        Stopwatch timer = new Stopwatch();
        SortClassWrapper.doSort(alg, a);
        return timer.elapsedTime();
    }
}
