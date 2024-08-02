package org.rancore.ad.ad_1_5;

public class QuickFind extends UFBase {

    public QuickFind(int n) {
        super(n);
    }

    public int find(int p) {
        return id[p];
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int idp = find(p);
        int idq = find(q);
        if (idp == idq) return;
        int n = id.length;
        for (int i = 0; i < n; i++) {
            if (id[i] == idp) {
                id[i] = idq;
            }
        }
        count--;
    }
}
