package org.rancore.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Euler</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Euler class provides methods to calculate Euler's number (e) to a specified precision.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>euler</code>: A BigDecimal representing the calculated value of Euler's number.</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>Euler():</code> Initializes the Euler object by calculating the value of e using a default precision.</p>
 * <p><code>Euler(int n):</code> Initializes the Euler object by calculating the value of e using the specified precision.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>euler():</code> Calculates and returns the value of e with a default precision.</p>
 * <p><code>euler(int n):</code> Calculates and returns the value of e with the specified precision.</p>
 */
public class Euler {

    private static BigDecimal euler;

    /**
     * Calculates Euler's number with a default value of n=1000.
     *
     * @return The value of Euler's number as a BigDecimal.
     * <p>
     * This method provides a convenient way to calculate Euler's number using
     * a default iteration count, ensuring a reasonably high precision.
     */
    public static BigDecimal euler() {
        int n = 1000;
        return euler(n);
    }

    /**
     * Calculates Euler's number with the specified value of n.
     *
     * @param n The number of iterations to perform the calculation.
     * @return The value of Euler's number as a BigDecimal.
     * <p>
     * This method uses a series expansion to calculate the value of Euler's number (e).
     * The higher the value of n, the more accurate the result, as more terms are included
     * in the series.
     */
    public static BigDecimal euler(int n) {
        BigDecimal euler = BigDecimal.ONE;
        BigDecimal faktor = BigDecimal.ONE;

        for (int i = 1; i <= n; i++) {
            faktor = faktor.divide(BigDecimal.valueOf(i), MathContext.DECIMAL128);
            euler = euler.add(faktor);
        }
        return euler;
    }

    /**
     * Constructs a new Euler object and calculates Euler's number with the default value of n=1000.
     * <p>
     * This constructor allows the creation of an Euler object with the value of e pre-calculated
     * to a default precision.
     */
    public Euler() {
        euler = euler();
    }

    /**
     * Constructs a new Euler object and calculates Euler's number with the specified value of n.
     *
     * @param n The number of iterations to perform the calculation.
     * <p>
     * This constructor allows the creation of an Euler object with the value of e pre-calculated
     * to a specified precision.
     */
    public Euler(int n) {
        euler = euler(n);
    }

    /**
     * Main method for testing the Euler class.
     *
     * @param args Command-line arguments (not used).
     * <p>
     * This method demonstrates the usage of the Euler class by calculating Euler's number
     * and printing the results to the console.
     */
    public static void main(String[] args) {
        int[] precisions = {10, 100, 1000, 10000, 100000};

        System.out.println("Euler's Number Calculation");
        System.out.println("==========================");

        for (int precision : precisions) {
            Instant start = Instant.now();
            BigDecimal eulerValue = euler(precision);
            Instant end = Instant.now();

            Duration duration = Duration.between(start, end);

            System.out.println("Precision: " + precision);
            System.out.println("Value: " + eulerValue);
            System.out.println("Time taken: " + duration.toMillis() + " milliseconds");
            System.out.println("--------------------------");
        }

        // Additional test using the default constructor and method
        Instant start = Instant.now();
        BigDecimal eulerDefault = euler();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.println("Default Precision (1000)");
        System.out.println("Value: " + eulerDefault);
        System.out.println("Time taken: " + duration.toMillis() + " milliseconds");
        System.out.println("==========================");
    }
}

