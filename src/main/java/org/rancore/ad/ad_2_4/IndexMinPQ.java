package org.rancore.ad.ad_2_4;


public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;
    private int[] qp;
    private int[] pq;
    private Key[] keys;
    
    public IndexMinPQ(int maxN) {
        qp = new int[maxN + 1];
        pq = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public void insert(int index, Key key) {
        N++;
        qp[index] = N;
        pq[N] = index;
        keys[index] = key;
        swim(N);
    }
    
    public void change(int index, Key key) {
        keys[index] = key;
        swim(qp[index]);
        sink(qp[index]);
    }

    public void delete(int index) {
        int pos = qp[index];
        exch(pos, N--);
        swim(pos);
        sink(pos);
        qp[index] = -1;
        keys[index] = null;
    }

    private void swim(int k){
        while (k>1 && less(k/2,k)) {
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <=N){
            int j = 2*k;
            if (j <N && less(j,j+1)) j++;
            if ( !less(k,j) ) break;
            exch(k,j);
            k=j;
        }
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int posI, int posJ) {
        int indexI = pq[posI];
        qp[indexI] = posJ;
        qp[pq[posJ]] = posI;
        pq[posI] = pq[posJ];
        pq[posJ] = indexI;
    }
}
