package org.rancore.ad.abgabe02.adrian;


import abgabe02.jonas.DoubleLinkedList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import abgabe02.jonas.Stopwatch;

import java.util.LinkedList;


public class DoublingTest {
    private static final int MAX = 2_000_000;

    public static void main(String[] args){

        for (int N = 250; true; N+=N){
            if (N>=MAX) break;
            // Zeit für Problemgröße N
            double time = trialTime(N);

            StdOut.printf("%10d %5.1f\n",N,time);
        }

        for (int N = 250; true; N+=N){
            if (N>=MAX) break;
            // Zeit für Problemgröße N
            double time = trialTimeLinked(N);

            StdOut.printf("%10d %5.1f\n",N,time);
        }
    }

    /**
     * - Erzeugen von N Daten (hier 6-stellige Integer) <br>
     * - Hinzufügen zur doppelt verketteten Liste
     * - Stoppuhr starten <br>
     * - Über Liste iterieren <br>
     * - Zeit messen <br>
     * @param N Anzahl Datenpunkte
     * @return Rückgabewert
     */
    private static double trialTime(int N) {
        int MAX = 1000000;
        Integer integer;
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        for (int i = 0; i < N; i++){
            integer = StdRandom.uniformInt(-MAX, +MAX);
            list.add(integer);
        }

        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < N; i++){
            list.get(i);
        }
        return timer.elapsedTime();
    }

    private static double trialTimeLinked(int N) {
        int MAX = 1000000;
        Integer integer;
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < N; i++){
            integer = StdRandom.uniformInt(-MAX, +MAX);
            list.add(integer);
        }

        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < N; i++){
            list.get(i);
        }

        return timer.elapsedTime();
    }

}
