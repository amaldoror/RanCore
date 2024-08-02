package org.rancore.ad.ad_1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            String[] tokens = StdIn.readLine().split(" ");
            for (String token : tokens) {
                if (token.equals("(")) {
                    System.out.println("Ignored");
                    // Ignorieren
                    //... ANALOG FÜR DIE ANDEREN OPERATOREN ...
                }
                else if (token.equals(")")) {
                    String op = ops.pop();
                    double v = vals.pop();
                    if (op.equals("+")) v = vals.pop() + v;
                    //... ANALOG FÜR DIE ANDEREN OPERATOREN ...
                    vals.push(v);
                } else vals.push(Double.parseDouble(token));
            }
        }
        StdOut.println(vals.pop());
    }
}

