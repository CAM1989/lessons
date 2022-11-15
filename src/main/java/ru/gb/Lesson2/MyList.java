package ru.gb.Lesson2;

public interface MyList<T> extends Iterable{
    T get (int idx);
    void add(T data);
    void insert(T data, int idx);
    void delete();
    void delete(int idx);
}
