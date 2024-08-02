package org.rancore.ad.ad_1_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class VisualAccumulator extends SimpleAccumulator implements Accumulator {
    private static int N = 0;

    public VisualAccumulator(int trials, double max){
        StdDraw.setXscale(0,trials);
        StdDraw.setYscale(0,max);
        StdDraw.setPenRadius(0.005);
    }
    @Override
    public void addDataValue(double d) {
        N++;
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.point(N,d);
        StdDraw.setPenColor(Color.RED);
        StdDraw.point(N, d /N);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Out out = new Out(args[1]);
        while (!in.hasNextChar()) {
            in.readChar();
            out.print(in);
        }
        in.close();
        out.close();
    }
}
