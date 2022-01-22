package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    private final MaxMin object = new MaxMin();
    private final Sort<String> sortForString = new Sort<>();
    private final Sort<Integer> sortForInteger = new Sort<>();

    @Test
    public void whenFindMaxElementFromListOfStrings() {
        List<String> strings = new ArrayList<>(List.of("a", "c", "b"));
        assertThat(object.max(strings, sortForString), is("c"));
    }

    @Test
    public void whenFindMinElementFromListOfStrings() {
        List<String> strings = new ArrayList<>(List.of("c", "d", "a"));
        assertThat(object.min(strings, sortForString), is("a"));
    }

    @Test
    public void whenFindMaxElementFromListOfInteger() {
        List<Integer> digits = new ArrayList<>(List.of(-4, 10, 20));
        assertThat(object.max(digits, sortForInteger), is(20));
    }

    @Test
    public void whenFindMinElementFromListOfInteger() {
        List<Integer> digits = new ArrayList<>(List.of(3, 1, 4));
        assertThat(object.min(digits, sortForInteger), is(1));
    }
}