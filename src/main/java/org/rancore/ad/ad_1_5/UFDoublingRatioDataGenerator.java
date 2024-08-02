package org.rancore.ad.ad_1_5;

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.io.IOException;

public class UFDoublingRatioDataGenerator {

    private static final String PREFIX = "ufdrdata";
    private static final String EXT = "data";

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        generate(N,trials);
    }

    public static String filename(int n){
        return System.getProperty("user.dir") +
                File.separator+PREFIX+n+"."+EXT;
    }

    public static void generate( int n, int trials) throws IOException {

        for (int i =0; i < trials; i++) {
            // WeightedQuickUnion für das Generieren bei großen N (type 3)
            UF uf = UnionFindFactory.getInstance(3,n); 
            Out f = new Out(filename(n));
            while (uf.count() > 1) {
                int p = StdRandom.uniformInt(0, n);
                int q = StdRandom.uniformInt(0, n);
                if (!uf.connected(p,q)) {
                    uf.union(p, q);
                    f.println(p);
                    f.println(q);
                }
            }
            f.close();
            n+=n;
        }
    }
}
