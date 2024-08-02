package org.rancore.ad.ad_2_1;

import java.util.Arrays;

public class SortClassCommons {

    /**
     * Die Methode enthält den Sortieralgorithmus und ist spezifisch für jedes Sortierverfahren.<br>
     * Sortieren arbeitet in-place, d.h., der Inhalt des Arrays wird durch sort verändert.<br>
     * Es werden keine Kopien des Arrays verwendet.<br>
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void sort(T[] a){
        // TODO implement sort method
        System.out.println("Sorting lol");
    }
    /**
     * Die Methode prüft welches der Elemente a und b kleiner ist. <br>
     * Wird von allen Vergleichs-basierten Sortierverfahren genutzt.
     * @param t1
     * @param t2
     * @return
     * @param <T>
     */
    public static <T extends Comparable<? super T>> boolean less(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    /**
     * Die Methode tauscht in dem T[]-Array a das Element an Position i gegen das Element an Position j.
     * @param a
     * @param i
     * @param j
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void exch(T[]a ,int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Die Methode gibt den Inhalt des T[]-Arrays a aus.
     * @param a
     * @param <T>
     */
    public static <T> void show(T[] a){
        System.out.println(Arrays.toString(a));
    }

    /**
     * Methode zur Verifikation des Sortierergebnisses <br>
     * Die Methode prüft, ob das T[]-Arrays a sortiert ist.
     * Verwendet als Post-Condition zur Überprüfung der Invariante,
     * dass alle Sortierverfahren ein sortiertes Arrays erzeugen müssen.
     * @param a
     * @return
     * @param <T>
     */
    public static <T extends Comparable<? super T>> boolean isSorted(T[] a){
        int N = a.length;
        for (int i =1; i <N; i++){
            if (less(a[i],a[i-1])) {
                return false;
            }
        }
        return true;
    }
}