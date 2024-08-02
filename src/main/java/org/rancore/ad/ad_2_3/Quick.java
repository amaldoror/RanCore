package org.rancore.ad.ad_2_3;

import edu.princeton.cs.algs4.StdRandom;

import static ad_2_1.SortClassCommons.*;

public class Quick {
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        StdRandom.shuffle(a);
        sort( a,0, a.length - 1);
        assert isSorted(a);
    }


    static <T extends Comparable<? super T>> int partition(T[] a, int lo,int hi){
        int i=lo, j=hi+1;
        T pivot = a[lo];
        while( true ) {
            while(less(a[++i],pivot)) if (i==hi) break;
            while(less(pivot,a[--j])) if (j==lo) break;
            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    static <T extends Comparable<? super T>> void sort( T[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        T v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp <0 ) { exch(a,i++,lt++);}
            else if (cmp==0) {i++;}
            else {exch(a,i,gt--);}
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }
}