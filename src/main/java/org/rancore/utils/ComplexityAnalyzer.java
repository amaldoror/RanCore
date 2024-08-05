package org.rancore.utils;

/**
 * This class provides functionality to analyze the time and space complexity of a given method.
 */
public class ComplexityAnalyzer {

    /**
     * Functional interface representing a method to be analyzed.
     */
    @FunctionalInterface
    public interface MethodToAnalyze {
        void execute();
    }

    /**
     * Analyzes the given method by measuring its execution time and memory usage.
     *
     * @param method the method to be analyzed
     */
    public static void analyze(MethodToAnalyze method) {
        // Measure time complexity
        long startTime = System.nanoTime();
        method.execute();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Measure space complexity
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Call garbage collector to get more accurate measurement
        long startMemory = runtime.totalMemory() - runtime.freeMemory();
        method.execute();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = endMemory - startMemory;

        // Print results
        System.out.println("Time:\t" + duration + " nanoseconds");
        System.out.println("Memory:\t" + memoryUsed + " bytes");
    }

    /**
     * Main method to demonstrate the usage of the ComplexityAnalyzer.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] arraySmall = new int[10];
        int[] arrayMedium = new int[1000];
        int[] arrayLarge = new int[1_000_000];

        fillArray(arraySmall);
        fillArray(arrayMedium);
        fillArray(arrayLarge);

        analyze(() -> sumArray(arraySmall));
        analyze(() -> sumArray(arrayMedium));
        analyze(() -> sumArray(arrayLarge));
    }

    /**
     * Example method to analyze that sums the elements of an array.
     * This method has a time complexity of O(n^2) due to the nested loops.
     *
     * @param array the array whose elements are to be summed
     */
    public static void sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            for (int num2 : array) {
                sum += num2;
            }
            sum += num;
        }
    }

    /**
     * Fills the given array with incremental values starting from 1.
     *
     * @param array the array to be filled
     */
    private static void fillArray(int[] array) {
        for (int i = 0; i<array.length; i++) {
            array[i] = i+1;
        }
    }
}
