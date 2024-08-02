package org.rancore.ad.ad_1_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator acc = new SimpleAccumulator();
        for (int i =0; i < T; i++) {
            acc.addDataValue(StdRandom.uniformDouble());
        }
        StdOut.println(acc);
    }
}
