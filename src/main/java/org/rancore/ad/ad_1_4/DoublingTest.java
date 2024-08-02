package org.rancore.ad.ad_1_4;

import abgabe02.adrian.DoublyLinkedList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Automatisieren des Experiments <br>
 * In jedem Schritt: <br>
 * - Verdoppeln der Problemgröße N <br>
 * - Zeitmessung für Problemgröße N <br>
 */
public class DoublingTest {
    private static final int MAX = 100000;
    public static void main(String[] args){

        for (int N = 250; true; N+=N){
            if (N>=MAX) break;
            // Zeit für Problemgröße N
            double time = trialTimeAdd(N);
            StdOut.printf("%7d %5.1f\n",N,time);
        }
    }

    /**
     * - Erzeugen von N Daten (hier 6-stellige Integer) <br>
     * - Stoppuhr starten <br>
     * - Drei-Summen-Problem für N lösen <br>
     * - Zeit messen <br>
     * @param N Anzahl Datenpunkte
     * @return Rückgabewert
     */
    private static double trialTimeAdd(int N){
        int MAX = 1000000;
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Integer randInteger;

        for (int i = 0; i < N; i++) {       // N zufällige Zahlen von -MAX bis MAX einfügen
            randInteger = StdRandom.uniformInt(-MAX, +MAX);
            dll.add(randInteger);
        }
        Stopwatch timer = new Stopwatch();  // Stoppuhr gestartet
        for (int i = 0; i < N-1; i++) {

        }
        return timer.elapsedTime();
    }

}
