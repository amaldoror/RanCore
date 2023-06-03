package rancore.math;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Euler</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Euler class is an implementation of methods to calculate Euler's number e.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>euler</code>: A BigDecimal representing Euler's number.</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>Euler():</code> Initializes the Euler object by calculating the value of e using a default precision.</p>
 * <p><code>Euler(int n):</code> Initializes the Euler object by calculating the value of e using the specified precision.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>euler():</code> Calculates and returns the value of e with a default precision.</p>
 * <p><code>euler(int n):</code> Calculates and returns the value of e with the specified precision.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/05/20</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */

public class Euler {

    private static BigDecimal euler = BigDecimal.ONE;

    /**
     * Calculates Euler's number with a default value of n=1000.
     *
     * @return The value of Euler's number.
     */
    public static BigDecimal euler() {
        int n = 1000;
        BigDecimal euler = BigDecimal.ONE;
        BigDecimal faktor = BigDecimal.ONE;

        for (int i = 1; i <= n; i++) {
            faktor = faktor.divide(BigDecimal.valueOf(i), MathContext.DECIMAL128);
            euler = euler.add(faktor);
        }
        return euler;
    }

    /**
     * Calculates Euler's number with the specified value of n.
     *
     * @param n The number of iterations to perform the calculation.
     * @return The value of Euler's number.
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
     */
    public Euler() {
        euler = euler();
    }

    /**
     * Constructs a new Euler object and calculates Euler's number with the specified value of n.
     *
     * @param n The number of iterations to perform the calculation.
     */
    public Euler(int n) {
        euler = euler(n);
    }
}

