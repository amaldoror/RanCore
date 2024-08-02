package org.rancore.ad.ad_1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        while(!StdIn.isEmpty())
            st.push(StdIn.readInt());
        for(int i: st)
            StdOut.println(i);
    }
}
