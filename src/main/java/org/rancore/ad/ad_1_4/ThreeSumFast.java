package org.rancore.ad.ad_1_4;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * Suche in einem sortierten Array nach einem Element -a[i]-a[j]
 * Wachstumsordnung: 𝑁² log2 𝑁
 */
public class ThreeSumFast {
    public static int count(int[] a){
        int n = a.length-1;
        int cnt = 0;
        Arrays.sort(a);
        for (int i =0; i <= n; i++) {
            for (int j = i; j <=n; j++) {
                if (BinarySearch.rank(-a[i]-a[j],a)> j){
                    cnt++;
                }
            }
        }
        return cnt;
    } }
