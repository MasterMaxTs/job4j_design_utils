package ru.job4j.collection.list;

public class SimpleStack<T> {
    private ForwardLinked<T> forwardLinked = new ForwardLinked<>();

    public T pop() {
        return this.forwardLinked.deleteFirst();
    }

    public void push(T value) {
        this.forwardLinked.addFirst(value);
    }
}
