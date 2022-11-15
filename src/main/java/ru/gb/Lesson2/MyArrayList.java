package ru.gb.Lesson2;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private int capacity;
    private T[] array;
    private int size;

    public MyArrayList() {
        this.capacity = 5;
        this.array = (T[]) new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public T get(int idx) {
        if (size < 1) {
            throw new IndexOutOfBoundsException("Невозможно получить элемент, ArrayList пуст");
        }
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Невозможно получить элемент " + idx + "/ Размер ArrayList = " + size);
        } else return array[idx];
    }

    @Override
    public void add(T data) {
        if (size == capacity) {
            capacity = capacity * 2;
            array = Arrays.copyOf(array, capacity);
            add(data);
        } else
            array[size] = data;
        size++;
    }

    @Override
    public void insert(T data, int idx) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Невозможно заменить элемент " + idx + "/ Размер ArrayList = " + size);
        } else
            for (int i = 0; i < capacity; i++) {
                if (i == idx) {
                    array[i] = data;
                }
            }
    }

    @Override
    public void delete() {
        size = 0;
    }

    @Override
    public void delete(int idx) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Невозможно удалить элемент " + idx + "/ Размер ArrayList = " + size);
        } else
            for (int i = idx; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator {
        int position = 0;
        @Override
        public boolean hasNext() {
            return position < size && array[position] != null;
        }

        @Override
        public T next() {
            Object temp = array[position];
            position++;
            return (T) temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
