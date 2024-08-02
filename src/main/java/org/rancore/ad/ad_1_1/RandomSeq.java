package org.rancore.ad.ad_1_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.jetbrains.annotations.NotNull;


public class RandomSeq {
    public static void main(@NotNull String[] args) {
        // Gibt N Zufallswerte im Intervall [lo,hi) aus
        int N = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);
        for (int i = 0; i <N; i++){
            double x = StdRandom.uniformDouble(lo, hi);
            StdOut.printf("%.2f\n",x);
        }
    }
}