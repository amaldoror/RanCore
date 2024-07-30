package org.rancore.utils;

import java.util.Arrays;

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

    private static void fillArray(int[] array) {
        for (int i = 0; i<array.length; i++) {
            array[i] = i+1;
        }
    }
}
