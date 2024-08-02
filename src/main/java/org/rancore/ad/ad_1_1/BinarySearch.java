package org.rancore.ad.ad_1_1;

/**
 * In einem sortierten Array von Zahlen wird die Mitte mit dem gesuchten Element verglichen. <br>
 * - Ist die Mitte gleich dem Element, wurde das Element gefunden. <br>
 * - Ist das Element kleiner als die Mitte wird das Element im Arraybereich [0,Mitte-1] gesucht. <br>
 * - Ist das Element größer als die Mitte, wird das Element im Arraybereich [Mitte+1,Arraylänge] gesucht. <br>
 * - Das Element wurde nicht gefunden, wenn der betrachtete Arraybereich leer ist. <br>
 * Dies lässt sich sehr einfach in eine rekursive Programmlösung umsetzen.  <br>
 * Damit nicht in jedem Schritt das Array kopiert werden muss, markieren wir mit den Parametern
 * lo und hi den zu untersuchenden Bereich in dem Array.
 */
public class BinarySearch {

    /**
     * Rekursiv
     * @param key
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key == a[mid]) return mid;
        else if (key < a[mid]) return rank(key, a, lo, mid - 1);
        else return rank(key, a, mid + 1, hi);
    }

    /**
     * Iterativ <br>
     * Voraussetzung: <br>
     * - Sortiertes Array a von Zahlen (bei Arrays von Comparable wird die allgemeine
     * Vergleichsmethode compareTo verwendet) <br>
     * - Gesucht wird das Element key in a <br>
     * - Betrachtet wird der Bereich von lo=0 bis hi=a.length-1 <br>
     * Algorithmus: <br>
     * 1. Solange lo <=hi: <br>
     * i. Bestimme die Mitte mid des Arrays <br>
     * ii. Wenn a[mid] == key, dann beende das Verfahren mit dem Ergebnis mid <br>
     * iii. Sonst wenn key < a[mid], dann setzte hi auf mid-1 und wiederhole ab 1.) <br>
     * iv. Sonst wenn key > a[mid], dann setze lo auf mid+1 und wiederhole ab 1.) <br>
     * 2. Jetzt ist lo > hi, key wurde nicht gefunden und das Ergebnis ist -1. <br>
     * @param key
     * @param a
     * @return
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
