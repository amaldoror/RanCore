package org.rancore.ad.ad_2_2;

import java.util.Arrays;

import static org.rancore.ad.ad_2_1.SortClassCommons.less;


/**
 * Naive Lösung für Merge-Sort<br>
 * Merge-Sort:<br>
 * - Divide: Kopiere die linke und die rechte Hälfte des Arrays.<br>
 * - Sortiere die beiden Hälften (rekursiver Aufruf).<br>
 * - Conquer: Mische die beiden Hälften in ein neues Ergebnisarray,
 * indem sukzessive das jeweils kleinste Element aus den beiden Arrays gewählt wird.<br>
 * Problem:<br>
 * - Kosten für das Erzeugen der Ergebnisarrays in jedem Merge-Schritt können sich erheblich auswirken,
 * da Merge-Sort das Problem in viele kleine Teilarrays zerlegt.<br>
 */
public class NaivesMerge {

    public static <T extends Comparable<? super T>> T[] sort(T[] a){
        if (a.length<=1) return a;

        // Divide: Kopiere die beiden Hälften
        int mid = a.length/2;

        T[] leftSorted = sort(Arrays.copyOf(a,mid));
        T[] rightSorted = sort(Arrays.copyOfRange(a, mid,a.length));

        // Conquer: Überführe die Teil-Ergebnisse, die sortierten Hälften, durch Mischen in das Endergebnis.
        return merge(leftSorted,rightSorted);
    }

    static <T extends Comparable<? super T>> T[] merge(T[] left, T[] right){
        // Speicher für das Ergebnis
        T[] merged =(T[]) new Comparable[left.length+right.length];

        // Lesezeiger für das linke und das rechte Teilarray
        int leftC = 0;
        int rightC = 0;


        for (int i = 0; i <merged.length; i++){
            // 1. linkes Teilarray abgearbeitet
            if (leftC >= left.length) merged[i] = right[rightC++];

            // 2. rechtes Teilarray abgearbeitet
            else if (rightC >= right.length) merged[i] = left[leftC++];

            // 3. Element rechts < Element links
            else if (less(right[rightC],left[leftC])) merged[i] = right[rightC++];

            // 4. Element links <= Element rechts (und links und rechts noch nicht erschöpft)
            else merged[i] = left[leftC++];
        }
        return merged;
    }
}