package org.rancore.ad.ad_2_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class MultiWay {
    public static void merge(In[] streams){
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        for (int index = 0; index < N; index++){
            if (!streams[index].isEmpty()){
                pq.insert(index,streams[index].readString());
            }
        }

        while(!pq.isEmpty()) {
            int index = pq.delMin();
            if (!streams[index].isEmpty()) {
                pq.insert(index,streams[index].readString());
            }
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i =0; i < N; i++){
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
