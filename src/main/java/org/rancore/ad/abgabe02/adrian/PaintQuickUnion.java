package org.rancore.ad.abgabe02.adrian;

import ad_1_5.UnionFindFactory;
import ad_1_5.UF;

public class PaintQuickUnion {

    private static final int[] pArray = new int[]{0,2,1,6,4,5,1,1,9,8,8,8};
    private static final int[] qArray = new int[]{1,1,3,9,7,8,5,8,1,2,3,4};

    private static final int n = 10;

    private static int step;
    private static int p;
    private static int q;


    // Zeichnen Sie für den Quick-Union-Algorithmus nach jedem union-Schritt den Inhalt des
    // id[] Arrays. Zeichnen Sie auch den entsprechenden Wald von Bäumen.
    public static void doQuickUnionUF() {
        UF quickUnion = UnionFindFactory.getInstance(2, n);
        for (int i = 0; i < pArray.length; i++) {
            p = pArray[i];
            q = qArray[i];
            quickUnion.union(p, q);
            step = i+1;

            System.out.println("Quick Union [" + p + ", " + q + "]" );
            //System.out.println("Count: " + quickUnion.count() + "| Steps:" + step);

            //Print ID
            System.out.print("ID´s: ");
            for(int j = 0; j < n; j++) {
                System.out.print(quickUnion.find(j) + " ");
            }
            System.out.println("\n");
        }
    }

    // Zeichnen Sie für den Weighted-Quick-Union-Algorithmus nach jedem union Schritt den
    // Inhalt des id[] und des sz[] Arrays.
    private static void doWeightedQuickUnionUF() {
        UF weightedQuickUnion = UnionFindFactory.getInstance(3, n);
        for (int i = 0; i < pArray.length; i++) {
            p = pArray[i];
            q = qArray[i];
            weightedQuickUnion.union(p, q);
            step = i + 1;
            System.out.println("Weighted Quick Union [" + p + ", " + q + "]");
            //System.out.println("Count: " + weightedQuickUnion.count() + "| Steps:" + step);

            //Print ID
            System.out.print("ID´s: ");
            for(int j = 0; j < n; j++) {
                System.out.print(weightedQuickUnion.find(j) + " ");
            }
            System.out.println("\n");
        }
    }



    public static void main(String[] args) {


        doQuickUnionUF();

        System.out.println("-------------------------\n");

        doWeightedQuickUnionUF();
    }
}
