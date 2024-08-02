package org.rancore.ad.ad_3_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class STTestClient {
    public static void main(String[] args) {
// String testString = "S E A R C H E X A M P L E";
        int type = Integer.parseInt(args[0]);
        STInterface<String, Integer> st =
                STFactory.getSTInstance(type);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String key : st.keys()) {
            StdOut.printf("%2s -> %2d\n", key, st.get(key));
        }
    }
}