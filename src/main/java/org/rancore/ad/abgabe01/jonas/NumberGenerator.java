package org.rancore.ad.abgabe01.jonas;

import java.util.Random;

/**
 * Generiert Random Integers oder Doubles oder eine Mischung anhand der Random Klasse
 * und gibt sie durch ein Leerzeichen getrennt auf der Console aus.
 * Anzahl, Max und Min Wert können frei gewählt werden.
 */
public class NumberGenerator {
    private final int min;
    private final int max;
    private final Random random;

    public NumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        this.random = new Random();
    }

    /**
     * Generiert Random Integer.
     * @param N Anzahl der Werte
     */
    public void generateIntegers(int N) {
        for (int i = 0; i < N; i++) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            System.out.print(randomNumber + " ");
        }
        System.out.println();
    }
    /**
     * Generiert Random Doubles.
     * @param N Anzahl der Werte
     */
    public void generateDoubles(int N) {
        for (int i = 0; i < N; i++) {
            double randomNumber = min + (max - min) * random.nextDouble();
            System.out.print(randomNumber + " ");
        }
        System.out.println();
    }

    /**
     * Generiert eine gleichmäßige Mischung aus Integer und Doubles.
     * @param N Anzahl der Werte
     */
    public void generateIntegerAndDoubles(int N) {
        for (int i = 0; i < N; i++) {
            if(random.nextBoolean()) {
                generateIntegers(1);
            }
            else {
                generateDoubles(1);
            }
        }
    }

    /**
     * Nimmt drei Argumente und printed dann eine Mischung aus Random Integern und Doubles.
     * @param args
     */
    public static void main(String[] args) {
        //noinspection DuplicatedCode
        if (args.length < 3) {
            System.out.println("Usage: java NumberGenerator <N> <min> <max>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);

        NumberGenerator generator = new NumberGenerator(min, max);

        generator.generateIntegerAndDoubles(N);
    }
}
