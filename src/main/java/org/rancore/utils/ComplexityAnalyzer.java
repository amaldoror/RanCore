package org.rancore.utils;

public class ComplexityAnalyzer {

    // Interface for a method to analyze
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
        System.out.println("Time:\t" + duration + " nanoseconds");
        System.out.println("Memory:\t" + memoryUsed + " bytes");
    }

    public static void main(String[] args) {
        // Example usage:
        analyze(() -> sumArray(new int[]{1, 2, 3, 4, 5}));
        analyze(() -> sumArray(new int[]{1,2,3,4,5,6,7,8,9,10}));
        analyze(() -> sumArray(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}));
        analyze(() -> sumArray(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}));
    }

    // Example method to analyze
    public static void sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            for (int num2 : array) {
                sum += num2;
            }
            sum += num;
        }
    }
}
