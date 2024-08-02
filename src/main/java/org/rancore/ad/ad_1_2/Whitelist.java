package org.rancore.ad.ad_1_2;

import ad_1_1.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Whitelist {
    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (BinarySearch.rank(key,whitelist) == -1) {
                StdOut.println(key);
            }
        }
    }
}