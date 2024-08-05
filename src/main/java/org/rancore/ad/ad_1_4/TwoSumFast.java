package org.rancore.ad.ad_1_4;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

public class TwoSumFast {
    // Berechnet die Anzahl der Paare, die summiert 0 ergeben
    public static int count(int[] a){
        int cnt = 0;
        Arrays.sort(a);     // N log2 N
        int n = a.length-1;

        for (int i =0; i <= n; i++){        // N
            int val = a[i];
            int index = BinarySearch.rank(-val,a);  // log2 N
            if (index >i) cnt++;
        }
        return cnt;
    }
}
