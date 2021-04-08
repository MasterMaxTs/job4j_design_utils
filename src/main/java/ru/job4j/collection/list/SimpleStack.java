package ru.job4j.collection.list;

public class SimpleStack<T> {
    private ForwardLinked<T> forwardLinked = new ForwardLinked<>();
    private int size = 0;

    public T pop() {
        size--;
        return this.forwardLinked.deleteFirst();

    }

    public void push(T value) {
        this.forwardLinked.addFirst(value);
        size++;
    }

    public int getSize() {
        return size;
    }
}
