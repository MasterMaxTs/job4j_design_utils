package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin<T> {
    private static final int POS = 0;
    private final Comparator<T> sorter;

    public MaxMin() {
        this.sorter = new Sort<>();
    }

    public T max(List<T> values) {
        return getValue(values, t -> t < 0);
    }

    public T min(List<T> values) {
        return getValue(values, t -> t > 0);
    }

    private T getValue(List<T> values, Predicate<Integer> condition) {
        T value = values.get(POS);
        for (T t : values) {
            if (condition.test(sorter.compare(value, t))) {
                value = t;
            }
        }
        return value;
    }
}
