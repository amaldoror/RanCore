package org.rancore.ad.ad_1_3;

import java.util.EmptyStackException;

/**
 * Performance! Anzahl der Elemente explizit verwaltet in N. <br>
 * @param <Item>
 */
public class FixedCapacityStack<Item> {
    private Item[] a;
    private int N;

    public FixedCapacityStack(int cap){
        // Erzeugung generischer Arrays nicht möglich, daher Cast auf den Typ Item[]
        a = (Item[])new Object[cap];
    }

    /**
     * Legt item als letztes Element auf dem Stapel. <br>
     * Keine Prüfung, ob N >= a.length. <br> <br>
     * @param item
     */

    public void push(Item item){
        // Vergrößern des Arrays
        if (N==a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * Liefert das zuletzt eingefügte Element vom Stapel. <br>
     * Keine Prüfung, ob N < 0. <br>
     * @return
     */
    public Item pop(){
        // Prüft Unterlauf
        if (isEmpty()) throw new EmptyStackException();
        Item it = a[--N];
        // Verhindert Loitering (Loitering: Referenzen auf Elemente, die nicht mehr benötigt werden, bleiben erhalten.)
        a[N] = null;
        // Verkleinern des Arrays
        if (N > 0 && N==a.length/4) resize(a.length/2);
        return it;
    }

    public int size() {return N;}

    private void resize(int max) {
        Item[] newA = (Item[]) new Object[max];
        if (N >= 0) System.arraycopy(a, 0, newA, 0, N);
        a = newA;
    }

    public boolean isEmpty() {return N==0;}
}
