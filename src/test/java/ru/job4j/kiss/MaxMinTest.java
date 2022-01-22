package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    private final MaxMin<String> stringObj = new MaxMin<>();
    private final MaxMin<Integer> integerObj = new MaxMin<>();

    @Test
    public void whenFindMaxElementFromListOfStrings() {
        List<String> strings = new ArrayList<>(List.of("a", "c", "b"));
        assertThat(stringObj.max(strings), is("c"));
    }

    @Test
    public void whenFindMinElementFromListOfStrings() {
        List<String> strings = new ArrayList<>(List.of("c", "d", "a"));
        assertThat(stringObj.min(strings), is("a"));
    }

    @Test
    public void whenFindMaxElementFromListOfInteger() {
        List<Integer> digits = new ArrayList<>(List.of(-4, 10, 20));
        assertThat(integerObj.max(digits), is(20));
    }

    @Test
    public void whenFindMinElementFromListOfInteger() {
        List<Integer> digits = new ArrayList<>(List.of(3, 1, 4));
        assertThat(integerObj.min(digits), is(1));
    }
}