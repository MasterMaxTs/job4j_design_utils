package ru.job4j.collection.list;

import java.util.*;

public class DynamicList<T> implements Iterable<T> {
    private final int minCapacity = 3;
    private Object[] elementData = new Object[minCapacity];
    private int size = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    public void add(T model) {
        if (size == elementData.length) {
            int newCapacity = (size * 3) / 2 + 1;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int cell = 0;

            @Override
            public boolean hasNext() {
                while (cell < elementData.length
                        && elementData[cell] == null) {
                    cell++;
                }
                return cell < elementData.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) elementData[cell++];
            }
        };
    }
}
