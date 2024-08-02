package org.rancore.ad.abgabe02.adrian;

import java.util.AbstractList;
import java.util.Collection;

/**
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    private Node<T> head;   // Wächterknoten am Anfang
    private Node<T> tail;   // Wächterknoten am Ende
    private int size;       // Zähler für die Anzahl der Elemente in der Liste


    private static class Node<T> {
        T element;
        Node<T> prev;
        Node<T> next;

        Node(T element) {
            this.element = element;
        }
    }


    public DoublyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.head.next = tail;
        this.tail.prev = head;
        this.size = 0;
    }

    // Verallgemeinerter Kopier-Konstruktor für Collections
    public DoublyLinkedList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != tail) {
            if (current.element.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    @Override
    public T set(int index, T element) {
        Node<T> nodeToReplace = getNode(index);
        T oldValue = nodeToReplace.element;
        nodeToReplace.element = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node<T> newNode = new Node<>(element);
        Node<T> current = (index == size) ? tail : getNode(index);

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
        System.out.println("Added " + element + " to index " + index);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T element = current.element;

        if (current == tail) { // Wenn das letzte Element entfernt wird
            tail = current.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }


        size--;
        return element;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // TODO toArray implementieren
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<T> current = head.next;
        while (current != tail) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }


    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        if(index == 0) return head;
        if(index == size - 1) return tail;

        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
