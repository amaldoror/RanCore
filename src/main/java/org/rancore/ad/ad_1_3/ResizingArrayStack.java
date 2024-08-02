package org.rancore.ad.ad_1_3;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    //... WEITERE METHODEN ...
    int N = 0;
    Item[] a;

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int cursor = N;
        @Override
        public boolean hasNext() {
            return cursor > 0;
        }
        @Override
        public Item next() {
            return a[--cursor];
        }
    }
}