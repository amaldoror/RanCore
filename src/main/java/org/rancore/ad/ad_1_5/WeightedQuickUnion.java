package org.rancore.ad.ad_1_5;

public class WeightedQuickUnion extends QuickUnion {

    // Array für die Größe der Bäume
    private int[] sz; // Verwaltet die Größe des Baumes für die Wurzel des Baumes

    // Zu Beginn haben alle Teilbäume die Größe 1 (bestehen aus einem Knoten).
    public WeightedQuickUnion(int n){
        super(n);
        sz = new int[n];
        for (int i =0; i <n; i++) {
            sz[i]=1;
        }
    }

    // union nutzt die Größe, um den kleineren Baum zu bestimmen und diesen an den größeren zu hängen.
    // Dabei wird die Größe des größeren Baumes aktualisiert.
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

}
