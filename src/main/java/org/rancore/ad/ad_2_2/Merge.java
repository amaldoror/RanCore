package org.rancore.ad.ad_2_2;


import static ad_2_1.SortClassCommons.less;

/**
 * In-Place-Merge <br>
 * - Der zu mischende Bereich wird durch eine obere (hi) und untere Grenze (lo) markiert. <br>
 * - Es werden die linken und rechten Hälften zwischen (lo..mid) und (mid+1,hi) gemischt. <br><br>
 * Vier Fälle: <br>
 * 1. linke Hälfte gelesen (leftC>mid): übertrage den Rest der rechten Hälfte <br>
 * 2. rechte Hälfte gelesen (rightC>hi): übertrage den Rest der linken Hälfte <br>
 * 3. Element rechts kleiner als das Element links: übertrage das Element der rechten Hälfte <br>
 * 4. sonst: übertrage Element der linken Hälfte <br>
 */
public class Merge {

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        T[] aux = a.clone();
        sort(a, aux, 0, a.length - 1, 0);
    }

    private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int lo, int hi, int depth) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, depth + 1);
        sort(a, aux, mid + 1, hi, depth + 1);
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        // Wir setzen lo = 0 und hi = N-1
        // zu mischende Bereiche: links (lo..mid), rechts (mid+1..hi)
        int leftC = lo;
        int rightC = mid + 1;

        // Kopie des zu mischenden Bereichs nach aux[]
        // Gelesen wird in aux[]. Geschrieben in a[].
        // 2N Arrayzugriffe für das Kopieren in aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // Betrachte das Intervall (lo..hi):
        // 2N Arrayzugriffe für das Kopieren nach a[]:
        // Summiert über alle Zweige der if-else Kaskade.
        for (int k = lo; k <= hi; k++) {

            // 1.Kopiere den Rest des rechten Bereichs
            if (leftC > mid) a[k] = aux[rightC++];

            // 2. Kopiere Rest des linken Bereichs
            else if (rightC > hi) a[k] = aux[leftC++];

            // 3. Kopiere das rechte Element
            // 2N Arrayzugriffe für den Vergleich der Elemente des linken und rechten Teilarrays
            else if (less(aux[rightC], aux[leftC])) a[k] = aux[rightC++];

            // 4. Kopiere das linke Element
            else a[k] = aux[leftC++];
        }
    }
}
