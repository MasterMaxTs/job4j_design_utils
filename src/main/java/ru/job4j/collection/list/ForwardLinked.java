package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int modCount = 0;

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            int expectModCount = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
