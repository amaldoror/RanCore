package org.rancore.ad.abgabe02.adrian;

import ad_1_5.QuickUnion;


/**
 * Quick Union mit Pfadkompression
 * Hier nutzt die find()-Methode die Pfadkompression.
 */
public class QuickUnionPC extends QuickUnion {
    private int[] parent;

    public QuickUnionPC(int n) {
        super(n);
        parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Find mit Pfadkompression: Zeigt immer auf den Wurzelknoten von p.
     * @param p an element
     * @return Root
     */
    @Override
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}
