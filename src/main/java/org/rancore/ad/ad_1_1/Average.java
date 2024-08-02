package org.rancore.ad.ad_1_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Average {
    public static void main(String[] args) {
        // Berechnet den Mittelwert der Zahlen in StdIn
        double sum = 0;
        int count = 0;
        while (!StdIn.isEmpty()){
            sum += StdIn.readDouble();
            count++;
        }
        double avg = sum/count;
        StdOut.printf("Average is %.5f\n", avg);
    }
}