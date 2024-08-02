package org.rancore.ad.abgabe02.jonas;

import ad_1_5.UF;
import ad_1_5.UnionFindFactory;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoublingTestPathCompression {
    public static void main(String[] args) {
        int rounds = 20;
        int add = 250;
        int range = 10;
        ArrayList<Integer> pArray = new ArrayList<>(List.of(0,2,1,6,4,5,1,1,9,8,8,8));
        ArrayList<Integer> qArray = new ArrayList<>(List.of(1,1,3,9,7,8,5,8,1,2,3,4));
        Random r = new Random();

        UF qU = UnionFindFactory.getInstance(2, 10);
        UF qUPathCompression = UnionFindFactory.getInstance(4, 10);



        System.out.println("QuickUnion | PathCompression");
        for(int j=0; j<rounds; j++) {
            qU = UnionFindFactory.getInstance(2, add);
            qUPathCompression = UnionFindFactory.getInstance(4, add);
            Stopwatch timer = new Stopwatch();
            for (int i = 0; i < pArray.size(); i++) {
                Integer p = pArray.get(i);
                Integer q = qArray.get(i);
                qU.union(p, q);
            }
            double qUTime = timer.elapsedTime();
            System.out.print(pArray.size() + ": " + qUTime + "  |  ");

            timer = new Stopwatch();
            for (int i = 0; i < pArray.size(); i++) {
                Integer p = pArray.get(i);
                Integer q = qArray.get(i);
                qUPathCompression.union(p, q);
            }
            double quPathCompressionTime = timer.elapsedTime();
            System.out.println(quPathCompressionTime);

            for(int t=0; t<add; t++) {
                pArray.add(r.nextInt(range));
                qArray.add(r.nextInt(range));

            }
            add = add*2;
        }
    }


    /*
12: 0.0  |  0.0
262: 0.001  |  0.0
762: 0.0  |  0.0
1762: 0.001  |  0.001
3762: 0.001  |  0.001
7762: 0.003  |  0.004
15762: 0.004  |  0.003
31762: 0.006  |  0.002
63762: 0.004  |  0.003
127762: 0.008  |  0.005
255762: 0.018  |  0.01
511762: 0.045  |  0.022
1023762: 0.066  |  0.005
2047762: 0.059  |  0.01
4095762: 0.115  |  0.023
8191762: 0.224  |  0.039
16383762: 0.461  |  0.077
32767762: 0.901  |  0.135
65535762: 1.767  |  0.291
131071762: 8.495  |  1.163

     */
}
