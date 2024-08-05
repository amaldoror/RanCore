package org.rancore.ad.ad_1_3;

import java.util.EmptyStackException;

/**
 * A fixed-capacity stack implementation with dynamic resizing.
 * <p>
 * Performance optimized by explicitly managing the number of elements in N.
 *
 * @param <Item> The type of elements held in this stack
 */
public class FixedCapacityStack<Item> {
    private Item[] a;
    private int N;


    public FixedCapacityStack(int cap){
        if (cap <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        a = (Item[]) new Object[cap];
    }

    /**
     * Adds an item to the top of the stack.
     * <p>
     * If the stack is full, it will automatically resize to double its current capacity.
     *
     * @param item The item to be pushed onto the stack
     */
    public void push(Item item){
        // Vergrößern des Arrays
        if (N==a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * Returns the last inserted element from the stack.
     * <p>
     * Note: This method does not check if N &lt; 0.
     *
     * @return The last inserted element
     * @throws EmptyStackException if the stack is empty
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

    /**
     * Returns the number of items in the stack.
     *
     * @return The number of items in the stack
     */
    public int size() {return N;}

    /**
     * Resizes the capacity of the stack.
     * <p>
     * This method creates a new array with the specified capacity and copies
     * the existing elements to the new array.
     *
     * @param max The new capacity for the stack
     */
    private void resize(int max) {
        Item[] newA = (Item[]) new Object[max];
        if (N >= 0) System.arraycopy(a, 0, newA, 0, N);
        a = newA;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {return N==0;}
}
