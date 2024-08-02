package org.rancore.ad.ad_2_1;

import static ad_2_1.SortClassCommons.exch;
import static ad_2_1.SortClassCommons.less;

public class Insertion {
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }
}