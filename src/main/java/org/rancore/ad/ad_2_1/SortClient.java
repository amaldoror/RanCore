package org.rancore.ad.ad_2_1;

import edu.princeton.cs.algs4.In;

import javax.management.ReflectionException;

import static ad_2_1.SortClassCommons.isSorted;
import static ad_2_1.SortClassCommons.show;

public class SortClient {
    public static void main(String[] args) throws ReflectionException {
        Sorter alg = Sorter.valueOf(args[0]);
        String[] a = new In().readAllStrings();

        SortClassWrapper.doSort(alg,a);
        assert isSorted(a);
        show(a);

        Insertion.sort(a);

        String[] b = new In().readAllStrings();
        Selection.sort(b);
        assert isSorted(b);
        show(b);

        String[] c = new In().readAllStrings();
        Shell.sort(c);
        assert isSorted(c);
        show(c);
    }
}