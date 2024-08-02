package org.rancore.ad.ad_3_1;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key,Val> implements STInterface<Key ,Val> {
    private int n;
    private Node first;

    private class Node {
        private Key key;
        Val val;
        Node next;

        public Node(Key key, Val val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Val get(Key key) {
        if (key == null) throw new IllegalArgumentException("Schlüssel dürfen nicht null sein!");
        for (Node current = first; current != null; current = current.next) {
            if (current.key.equals(key)) return current.val;
        }
        return null;
    }

    @Override
    public void put(Key key, Val val) {
        if (key == null) throw new IllegalArgumentException("Schlüssel dürfen nicht null sein!");
        if (val == null) {
            delete(key);
            return;
        }
        for (Node current = first; current != null; current = current.next) {
            if (current.key.equals(key)) {
                current.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Schlüssel dürfen nicht null sein!");
        if (!isEmpty()) {
            if (first.key.equals(key)) {
                first = first.next;
            } else {
                Node pred = first;
                while (pred.next != null && !pred.next.key.equals(key)) pred = pred.next;
                if (pred.next == null) return;
                pred.next = pred.next.next;
            }
            n--;
        }
    }

    @Override
    public int size() {
        return n;
    }

    public String[] keys() {
        Queue<Key> keys = new Queue<>();
        for (Node current = first; current != null;
             current = current.next) keys.enqueue(current.key);
        return new String[]{keys.toString()};
    }
}