package org.rancore.math;

import java.util.Random;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>CollatzConjecture</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The CollatzConjecture class provides a method for calculating the Hailstone sequence,
 * also known as the 3n+1 problem. The conjecture asks whether repeating two simple arithmetic
 * operations will eventually transform every positive integer into 1. <p>
 * Some values produce intermediates as high as 2.7*10^7 (for n = 9663).
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p>bool <code>debug</code></p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>collatzConjecture(int n)</code>: Calculates the Collatz conjecture.</p>
 */
public class CollatzConjecture {
    private static boolean debug = true;

    /**
     * Entry point for the program. If no arguments are passed, it will demonstrate the conjecture with a random number.
     * @param args "-debug" for console outputs, integer as input for conjecture method.
     */
    public static void main(String[] args) {
        if (args.length == 2 && args[0].equals("-debug")) {
            debug = true;
            collatzConjecture(Integer.parseInt(args[1]));
        } else if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            collatzConjecture(n);
        } else if (args.length == 0){
            Random rand = new Random();
            int n = rand.nextInt(1_000_000);
            System.out.println(n);
            collatzConjecture(n);
        }
    }

    /**
     * Recursive method for the conjecture. If <code>debug</code> is set to true,
     * it will print the numbers to the console.
     * @param n integer
     * @return The processed integer.
     */
    public static int collatzConjecture(int n) {
        int m = n % 2 == 0 ? n / 2 : 3 * n + 1;
        if (debug) System.out.println(m);
        if (m == 1) return m;
        return collatzConjecture(m);
    }
}
