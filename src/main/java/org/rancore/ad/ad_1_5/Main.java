package org.rancore.ad.ad_1_5;

public class Main {
    public static void main(String[] args) {
        int n = 10;

        UF quickfind = UnionFindFactory.getInstance(1, n);

        for (int i = 0; i < n; i++) {
            quickfind.union(i, i+1);
            System.out.println("Count: " + quickfind.count());
        }

        for (int i = 0; i < n; i++) {
            System.out.println(quickfind.find(i));
        }


    }
}
