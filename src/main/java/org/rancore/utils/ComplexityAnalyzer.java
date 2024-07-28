package org.rancore.utils;

public class ComplexityAnalyzer {

    // Interface for a method that we want to analyze
    @FunctionalInterface
    public interface MethodToAnalyze {
        void execute();
    }

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
        System.out.println("Time taken: " + duration + " nanoseconds");
        System.out.println("Memory used: " + memoryUsed + " bytes");
    }

    public static void main(String[] args) {
        // Example usage:
        analyze(() -> sumArray(new int[]{1, 2, 3, 4, 5}));
    }

    // Example method to analyze
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
}
