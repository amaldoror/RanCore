package org.rancore.math;

import java.math.BigInteger;
import java.util.Random;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Lucas</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Lucas class provides a method for calculating the Lucas sequence,
 * similar to the Fibonacci sequence, but starts with 2 and 1.
 * Each term is the sum of the two preceding terms.
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p>bool <code>debug</code></p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>lucasSequence(int n)</code>: Calculates the Lucas sequence.</p>
 */
public class Lucas {
    private static boolean debug = true;

    /**
     * Entry point for the program. If no arguments are passed, it will demonstrate the sequence with a random number.
     * @param args "-debug" for console outputs, integer as input for sequence method.
     */
    public static void main(String[] args) {
        if (args.length == 2 && args[0].equals("-debug")) {
            debug = true;
            lucasSequence(Integer.parseInt(args[1]));
        } else if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            lucasSequence(n);
        } else if (args.length == 0){
            Random rand = new Random();
            int n = rand.nextInt(1_00);
            System.out.println("n = " + n);
            lucasSequence(n);
        }
    }

    /**
     * Iterative method to print the Lucas sequence. If <code>debug</code> is set to true,
     * it will print the numbers to the console.
     * @param n The number of terms to print in the sequence.
     */
    private static void lucasSequence(int n) {
        if (n <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
        }

        BigInteger a = BigInteger.valueOf(2);
        BigInteger b = BigInteger.valueOf(1);

        if (debug) System.out.println("Lucas Sequence:");
        for (int i = 0; i < n; i++) {
            if (debug) System.out.println(a);
            BigInteger next = a.add(b);
            a = b;
            b = next;
        }
        if (debug) System.out.println();
    }
}
