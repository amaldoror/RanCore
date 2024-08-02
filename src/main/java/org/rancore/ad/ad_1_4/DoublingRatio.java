package org.rancore.ad.ad_1_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class DoublingRatio {
    static int MAX = 1000000;

    public static double timeTrial(int n){
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSumFast.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(1000);
        for (int n= 2000; true; n+=n) {
            if (n>MAX){
                break;
            }
            double time = timeTrial(n);
            StdOut.printf("%6d %7.1f %5.1f\n", n, time, time/prev);
            prev=time;
        }
    }
}