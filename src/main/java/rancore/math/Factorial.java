package rancore.math;
/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Factorial</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Factorial class provides methods for calculating the factorial of a number.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>factorial(int n):</code> Calculates the factorial of a number.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/05/20</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */


public class Factorial {

    /**
     * Calculates the factorial of a number.
     *
     * @param n The number for which to calculate the factorial.
     * @return The factorial of the given number.
     */
    public static double factorial(int n) {
        try {
            if (n == 0) {
                return 1;
            }
            for (int i = n; i > 1; i--) {
                n = n * (i - 1);
            }
        } catch (IllegalArgumentException e) {
            System.out.print("Input must be positive.");
            System.out.print(" Input: ");
        }
        return n;
    }

    /**
     * Calculates the factorial of a number recursively.
     *
     * @param n The number for which to calculate the factorial.
     * @return The factorial of the given number.
     */
    private static int factorialRecursive(int n) {
        try {
            if (n == 0) {
                return 1;
            }
            return n * factorialRecursive(n - 1);
        } catch (IllegalArgumentException e) {
            System.out.print("Input must be positive.");
            System.out.print(" Input: " + n);
        }
        return n;
    }

    /**
     * Calculates the factorial of a number recursively.
     *
     * @param n The number for which to calculate the factorial.
     * @return The factorial of the given number.
     * @throws IllegalArgumentException if a negative number is provided.
     */
    private static int factorialRecursive02(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input: " + n + " Input must be positive.");
        }
        if (n == 0) {
            return 1;
        } else {
            return n * factorialRecursive02(n - 1);
        }
    }

    /**
     * Calculates the factorial of a number using end recursion.
     *
     * @param n        The number for which to calculate the factorial.
     * @param result The current result of the factorial calculation.
     * @return The factorial of the given number.
     */
    private static int factorialEndRecursive(int n, int result) {
        if (n == 0) {
            return 1;
        }
        return factorialEndRecursive(n - 1, result * n);
    }

    /**
     * Calculates the factorial of a number using end recursion.
     *
     * @param n        The number for which to calculate the factorial.
     * @param result The current result of the factorial calculation.
     * @return The factorial of the given number.
     * @throws IllegalArgumentException if a negative number is provided.
     */
    private static int factorialEndRecursive02(int n, int result) {
        if (n < 0) {
            throw new IllegalArgumentException("Input: " + n + " Input must be positive.");
        }
        if (n == 0) {
            return result;
        }
        return factorialEndRecursive02(n - 1, result * n);
    }

    public static void main(String[] args) {
        // Examples of using the factorial methods
        // System.out.println(factorial(0));            // return 1
        // System.out.println(factorial(5));            // return 120
        // System.out.println(factorialRecursive(0));   // return 1
        // System.out.println(factorialRecursive(5));   // return 120
        // System.out.println(factorialRecursive(-5));  // IllegalArgumentException
        // System.out.println(factorialEndRecursive(0)); // return 1
        // System.out.println(factorialEndrecursive(5)); // return 120
        // System.out.println(factorialEndrecursive(-5));// IllegalArgumentException
    }
}
