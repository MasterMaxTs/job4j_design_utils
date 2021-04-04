package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    public int indexOf(String id) {
        int index = 0;
        for (T value
                : mem) {
            if (value.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void add(T model) {
        if (!mem.contains(model)) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            mem.remove(index);
        }
        return index != -1;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        if (index != -1) {
            return mem.get(index);
        }
        return null;
    }
}
