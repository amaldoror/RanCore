package org.rancore.ad.ad_1_5;

import abgabe02.adrian.QuickUnionPC;

/**
 * Factory für folgende Algorithmen: <br>
 * type 1: QuickFind(n) <br>
 * type 2: QuickUnion(n) <br>
 * type 3: WeightedQuickUnion(n) <br>
 * type 4: QuickUnionWithPathCompression(n) <br>
 */
public class UnionFindFactory {

    /**
     * Get an Instance of specified type: <br>
     * type 1: QuickFind(n) <br>
     * type 2: QuickUnion(n) <br>
     * type 3: WeightedQuickUnion(n) <br>
     * type 4: QuickUnionWithPathCompression(n) <br>
     */
    public static UF getInstance(int type, int n) {
        return switch (type) {
            case 1 -> new QuickFind(n);
            case 2 -> new QuickUnion(n);
            case 3 -> new WeightedQuickUnion(n);
            case 4 -> new QuickUnionPC(n);
            default -> throw new IllegalArgumentException("unbekannter UF Algorithmus");
        };
    }
}
