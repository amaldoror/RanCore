package org.rancore.ad.abgabe01.neu;

import java.util.Arrays;

public class InplaceSuche {
    public static void main(String[] args) {
        int[] ary1 = new int[]{1, 61, 89, 75, 16, 33, 89, 59, 28, 3, 3, 97, 61, 85, 47, 38, 78, 7, 6, 15};
        System.out.println(Arrays.toString(localMax(ary1, 2)));
    }


    public static int[] localMax(int[] arr, int radius) {
        return localMaxHelper(arr, radius, 0, arr.length - 1);
    }

    private static int[] localMaxHelper(int[] arr, int radius, int lo, int hi) {
        return null;
    }

}
