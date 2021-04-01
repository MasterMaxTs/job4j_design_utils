package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int size = 0;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    public void add(T model) {
        array[size++] = model;
    }


    public void set(int index, T model) {
        Objects.checkIndex(index, array.length);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, array.length);
        System.arraycopy(
                array, index + 1,
                array, index,
                array.length - 1 - index
        );
    }

    public T get(int index) {
        Objects.checkIndex(index, array.length);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cell = 0;
            @Override
            public boolean hasNext() {
                return cell < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (array[cell] == null) {
                    cell++;
                }
                return array[cell++];
            }
        };
    }
}
