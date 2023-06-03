package rancore.math;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>NumPrimBis</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The NumPrimBis class provides methods for counting the number of prime numbers between 2 and a given value.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p>None</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p>None</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>numPrimsBisRekusiv(int n):</code> Calculates and returns the number of prime numbers between 2 and the specified value <code>n</code> recursively.</p>
 * <p><code>numPrimsBisEndrekursiv(int n, int ergebnis):</code> Calculates and returns the number of prime numbers between 2 and the specified value <code>n</code> using an end recursive approach.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/05/20</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */

public class Prime {
    public int numPrimeRecursive(int n) {
        if (n == 1) {
            return 0;
        } else {
            return numPrimeRecursive(n - 1) + (isPrime(n) ? 1 : 0);
        }
    }

    public int numPrimeEndRecursive(int n, int result) {
        if (n == 1) {
            return result;
        } else {
            return numPrimeEndRecursive(n - 1, result + (isPrime(n) ? 1 : 0));
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Prime numPrime = new Prime();
        System.out.println(numPrime.numPrimeRecursive(10));
        System.out.println(numPrime.numPrimeEndRecursive(10, 0));
    }
}
