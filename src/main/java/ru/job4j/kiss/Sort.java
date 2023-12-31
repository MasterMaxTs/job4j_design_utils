package ru.job4j.kiss;

import java.util.Comparator;

class Sort<T> implements Comparator<T> {

    private static final String CLASS_NAME = "String";

    @Override
    public int compare(T o1, T o2) {
        if (o1.getClass().getName().endsWith(CLASS_NAME)) {
            return o1.toString().compareTo(o2.toString());
        }
        return Integer.parseInt(o1.toString()) - Integer.parseInt(o2.toString());
    }
}
