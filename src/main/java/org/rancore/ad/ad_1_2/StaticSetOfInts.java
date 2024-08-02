package org.rancore.ad.ad_1_2;

import java.util.Arrays;

// Kopie von keys vermeidet Seiteneffekte
// Verbirgt das Vorbereiten der Daten vor dem Client
// Verbirgt die Implementierung des Suchalgorithmus vor dem Client

public class StaticSetOfInts {
    private int[] a;
    public StaticSetOfInts(int[] keys){
        a = Arrays.copyOf(keys,keys.length);
        Arrays.sort(a);
    }
    public boolean contains(int key){
        return rank(key) != -1;
    }
    private int rank(int key){
        int lo = 0;
        int hi = a.length-1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if (key<a[mid]) hi = mid-1;
            else if (key > a[mid]) lo = mid+1;
            else return mid;
        }
        return -1;
    }
}
