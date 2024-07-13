package org.rancore.math;

import java.util.Arrays;

/**
 * Die Klasse Erathostenes implementiert das Sieb des Eratosthenes zur Berechnung aller Primzahlen bis zu einer gegebenen Grenze.
 * <p>
 * Das Ziel ist es, alle Primzahlen bis zu einer vorgegebenen Zahl n effizient zu finden. Das Sieb des Eratosthenes ist ein algorithmischer Ansatz,
 * bei dem alle Vielfachen einer Primzahl als Nicht-Primzahlen markiert werden.
 */
public class Erathostenes {

    /**
     * Berechnet alle Primzahlen bis zu einer gegebenen Grenze n.
     *
     * @param n Die obere Grenze.
     * @return Ein Array, das angibt, ob die Zahl an einem bestimmten Index eine Primzahl ist (true) oder nicht (false).
     */
    public static boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        return prime;
    }

    public static void main(String[] args) {
        int n = 300;
        boolean[] primes = sieve(n);
        System.out.print("Primzahlen bis " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
    }
}

