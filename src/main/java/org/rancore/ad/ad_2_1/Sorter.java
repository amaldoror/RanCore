package org.rancore.ad.ad_2_1;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;

public enum Sorter {
    SELECTION, INSERTION, SHELL,
    MERGE, MERGEBU,
    QUICK, QUICKINSERTION, QUICKMEDIAN3,QUICKMEDIAN3INSERTION,
    QUICK3WAY, QUICK3WAYINSERTION,
    HEAP;

    public Class<?> getSorterClass() {
        return switch (this) {
            case SELECTION -> Selection.class;
            case INSERTION -> Insertion.class;
            case SHELL -> Shell.class;
            case MERGE -> Merge.class;
            case MERGEBU -> MergeBU.class;
            case QUICK -> Quick.class;
            case QUICKINSERTION -> null;
            case QUICKMEDIAN3 -> null;
            case QUICKMEDIAN3INSERTION -> null;
            case QUICK3WAY -> Quick3way.class;
            case QUICK3WAYINSERTION -> null;
            case HEAP -> Heap.class;
        };
    }
}
