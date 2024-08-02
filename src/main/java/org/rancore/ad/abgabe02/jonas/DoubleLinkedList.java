package org.rancore.ad.abgabe02.jonas;

import java.util.AbstractList;
import java.util.Collection;

public class DoubleLinkedList<E> extends AbstractList<E> {
    // Private WrapperKlasse um Knoteneigenschaften zu speichern.
    private static int i = 0;

    private class Node {
        E data;
        Node prev;
        Node next;
        int id;

        Node(E data) {
            this.data = data;
            id = i++;
        }

        @Override
        public String toString() {
            return "Node " + id;
        }
    }

    // Variablen
    private final Node head;
    private final Node tail;
    private int size;

    // Konstruktor der die beiden Wächter-Knoten initialisiert.
    public DoubleLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // Kopier-Konstruktor.
    public DoubleLinkedList(Collection<? extends E> c) {
        this(); // Initialisiert die leere Liste mit Wächterknoten
        // Fügt jedes Element der Collection in die Liste ein
        this.addAll(c);
    }

    // Überprüft, ob der Index gültig ist und gibt die Daten des Nodes mit dem jeweiligen Index zurück.
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    // Gibt die eigene Size-Variable zurück.
    @Override
    public int size() {
        return size;
    }

    // Überprüft, ob der Index gültig ist und überschreibt dann die Daten dieses Knotens mit dem übergebenen Element.
    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node targetNode = getNode(index);
        E oldValue = targetNode.data;
        targetNode.data = element;
        return oldValue;
    }

    // Überprüft, ob der Index gültig ist, und fügt dann vor dem angegebenen Index den neuen Knoten ein.
    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            addAtEnd(element);
        } else {
            addBefore(getNode(index), element);
        }
    }

    // Fügt ein Element ganz am Ende der Liste ein.
    @Override
    public boolean add(E element) {
        addAtEnd(element);
        return true;
    }

    // Überprüft, ob der Index gültig ist und löscht dann den passenden Knoten.
    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return remove(getNode(index));
    }

    // Hilfsmethode, die eine Knoten vor einen anderen Knoten einfügt.
    // Dafür wird erst ein neuer Knoten mit den übergebenen Daten erstellt und dann die Pointer aktualisiert,
    // sodass der neue Knoten vor dem alten steht.
    private void addAtEnd(E element) {
        addBefore(tail, element); // Fügt das Element vor dem tail-Wächterknoten ein
    }

    private void addBefore(Node node, E element) {
        Node newNode = new Node(element);
        newNode.next = node;
        newNode.prev = node.prev;
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }

    // Hilfsmethode, die eine Knoten löscht,
    // indem sie die Pointer des vorherigen und des darauffolgenden Knotens aktualisiert.
    private E remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
        return node.data;
    }

    // Findet den passenden Knoten zu einem Index.
    // Startet hierbei entweder am Anfang oder am Ende je nachdem welche Größe der Index hat, um die Suche zu beschleunigen.
    private Node getNode(int index) {
        if (index < (size / 2)) {
            Node x = head.next;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = tail.prev;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    // Hilfsmethoden zur Validierung eines Indexes.
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head.next;
        while (current != tail) {
            sb.append(current.data);
            if (current.next != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
