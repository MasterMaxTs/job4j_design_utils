package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin<T> {
    private final Comparator<T> sorter;

    public MaxMin() {
        this.sorter = new Sort<>();
    }

    public T max(List<T> values) {
        return getValue(values, sorter);
    }

    public T min(List<T> values) {
        return getValue(values, sorter.reversed());
    }

    private T getValue(List<T> values, Comparator<T> comparator) {
        final int POS = 0;
        values.sort(comparator);
        return values.get(POS);
    }
}
