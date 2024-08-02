package org.rancore.ad.ad_1_5;


/**
 * Das id[] Array speichert Referenzen auf Knoten, die sich in derselben Komponente befinden. <br>
 * • Invariante: Wenn id[i]==i, dann ist die Wurzel der Komponente i erreicht. <br>
 * • find(p) sucht die Wurzel (die Komponente von p) <br>
 * • union(p,q) stellt die Invariante sicher, indem die Wurzel von p auf die Wurzel von q umgesetzt wird. <br>
 * • Durch dieses Verfahren entstehen Wälder von Bäumen. <br>
 */
public class QuickUnion extends UFBase {

    public QuickUnion(int n) {
       super(n);
    }

    /**
     * Sucht die Komponente von p. <br>
     * Verfolgt dazu die Referenzen in id[] bis zur Wurzel des Baumes. <br>
     * Die Komponente ist die Wurzel des Baumes, von dem p ein Knoten ist. <br>
     * Die Wurzel eines Baumes zeigt auf sich selbst. <br>
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        //int node = p;
        while (p !=id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * Verbindet zwei Komponenten, indem die Wurzel des Baumes von q zur Wurzel des Baumes von p wird.
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) { id[pRoot] = qRoot; count--;}
    }

}
