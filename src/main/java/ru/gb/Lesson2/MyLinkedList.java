package ru.gb.Lesson2;

import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public T get(int idx) {
        if (size < 1) {
            throw new IndexOutOfBoundsException("Невозможно получить элемент, ArrayList пуст");
        }
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Невозможно получить элемент " + idx + "/ Размер ArrayList = " + size);
        }
        Node<T> current = first;
        for (int i = 0; i < idx; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public void add(T data) {
        if (data == null)
            return;
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void insertLast(T item) {
        Node newNode = new Node(last, item, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T removeFirst() {
        T temp = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return temp;
    }
    public final T getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("list is empty");
        }
        return first.getValue();
    }



    @Override
    public void insert(T data, int idx) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Невозможно заменить элемент " + idx + "/ Размер LinkedList = " + size);
        }
        if (idx == 0) {
            insertFirst(data);
            return;
        }
        if (idx == size) {
            insertLast(data);
            return;
        }

        Node current = first;
        for (int i = 0; i < idx - 1; i++) {
            current = current.getNext();
        }
        Node newNode = new Node(current, data, current.getNext());
        current.getNext().setPrev(newNode);
        current.setNext(newNode);
        size++;
    }

    @Override
    public void delete() {
        first.next = null;
        size = 0;
    }

    @Override
    public void delete(int idx) {
        if (isEmpty()) {
            throw new RuntimeException("list is empty");
        }
        if (getFirst().equals(idx)) {
            removeFirst();
        }

        Node current = first;
        while (current != null && !current.getValue().equals(idx)) {
            current = current.getNext();
        }
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {
        Node current = new Node(null, first);

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return (T) current.getValue();
        }
    }

    @Override
    public String toString() {
        Node current = first;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}

