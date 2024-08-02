package org.rancore.ad.ad_1_1;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class RandomArrayValues {
    public static void main(String[] args) {
        int N = 100;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N*N);
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i <=N; i++) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i,i);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.point(i,i*i);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(i,i*Math.log(i));
        }
    }
}
