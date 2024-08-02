package org.rancore.ad.ad_1_5;

public abstract class UFBase implements UF {
    protected int count;
    protected int[] id;
    private int acx = 0;

    public UFBase(int n) {
        id = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public int count() {
        return count;
    }

}
