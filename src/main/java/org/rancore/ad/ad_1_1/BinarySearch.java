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
     * Iterative binary search.
     * <p>
     * Preconditions:
     * <ul>
     *     <li>Sorted array 'a' of numbers (for arrays of Comparable, the general
     *         compareTo method is used)</li>
     *     <li>Searching for element 'key' in 'a'</li>
     *     <li>Search range is from lo=0 to hi=a.length-1</li>
     * </ul>
     * <p>
     * Algorithm:
     * <ol>
     *     <li>While lo &lt;= hi:
     *         <ol type="a">
     *             <li>Determine the middle index mid of the array</li>
     *             <li>If a[mid] == key, end the procedure with the result mid</li>
     *             <li>Else if key &lt; a[mid], set hi to mid-1 and repeat from step 1</li>
     *             <li>Else if key &gt; a[mid], set lo to mid+1 and repeat from step 1</li>
     *         </ol>
     *     </li>
     *     <li>Now lo &gt; hi, key was not found and the result is -1</li>
     * </ol>
     *
     * @param key The element to search for
     * @param a The sorted array to search in
     * @return The index of the key if found, or -1 if not found
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
