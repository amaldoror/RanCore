package org.rancore.ad.ad_2_1;

import static org.rancore.ad.ad_2_1.SortClassCommons.exch;
import static org.rancore.ad.ad_2_1.SortClassCommons.less;

public class Shell<T extends Comparable<? super T>>extends SortBase<T> {
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        int length = a.length;
        int h = 1;
        while (h < length / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a,j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
