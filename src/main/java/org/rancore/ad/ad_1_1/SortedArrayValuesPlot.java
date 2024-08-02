package org.rancore.ad.ad_1_1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;

public class SortedArrayValuesPlot {
    public static void main(String[] args) {
        int N = 50;
        double[] a = new double[N];
        StdDraw.setPenColor(Color.RED);
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniformDouble();
        }
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N;
            double y = a[i] / 2.0;
            double rw = 0.25 / N;
            double rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
