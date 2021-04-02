package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        if (!mem.contains(model)) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        for (int value = 0; value < mem.size(); value++) {
            if (mem.get(value).getId().equals(id)) {
                mem.set(value, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (T value
                : mem) {
            if (value.getId().equals(id)) {
                mem.remove(value);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T value
                : mem) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }

    public List<T> getMem() {
        return mem;
    }
}
