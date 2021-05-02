package ru.job4j.collection.set;

import java.util.Iterator;
import java.util.Objects;

import ru.job4j.collection.list.DynamicList;

public class SimpleSet<T> implements Set<T> {
    private DynamicList<T> set = new DynamicList<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T item
                : set) {
            if (Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
