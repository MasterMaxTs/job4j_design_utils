package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(List.of(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is (List.of(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        ListUtils.addBefore(input, 3, 4);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(List.of(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is (List.of(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        ListUtils.addAfter(input, 3, 4);
    }

    @Test
    public void whenRemoveIfLessThanZero() {
        List<Integer> input = new ArrayList<>(List.of(-2, -1, 0, 1));
        Predicate<Integer> filter = x -> x < 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, is (List.of(0, 1)));
    }

    @Test
    public void whenReplaceByZeroIfElementIsNegative() {
        List<Integer> input = new ArrayList<>(List.of(-2, 1, -3, 4));
        Predicate<Integer> filter = x -> x < 0;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input, is (List.of(0, 1, 0, 4)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(List.of(0, 1, 2, 3));
        List<Integer> elements = new ArrayList<>(List.of(2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(List.of(0, 1)));
    }
}