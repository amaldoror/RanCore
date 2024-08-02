package org.rancore.ad.ad_1_4;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

public class TwoSumFast {
    // Berechnet die Anzahl der Paare, die summiert 0 ergeben
    public static int count(int[] a){
        int cnt = 0;
        Arrays.sort(a);     // 𝑵 log𝟐 𝑵
        int n = a.length-1;

        for (int i =0; i <= n; i++){        // 𝑵
            int val = a[i];
            int index = BinarySearch.rank(-val,a);  // log𝟐 𝑵
            if (index >i) cnt++;
        }
        return cnt;
    }
}
