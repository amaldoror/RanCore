package org.rancore.ad.abgabe01.neu;

import edu.princeton.cs.algs4.Accumulator;

public class SimpleAccumulator extends Accumulator {
    private int N=0;
    private double total=0.0;

    @Override
    public void addDataValue(double d) {
        N++;
        total+=d;
    }
    @Override
    public double mean() {
        return total/N;
    }

    @Override
    public String toString() {
        return String.format("Mean (%d values) : %7.5f", N, mean());
    }
}
