package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private Integer[] array;

    @Before
    public void setUp() {
        array = new Integer[4];
    }
    @Test
    public void whenAddFirstEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        assertThat(array[0], is (1));
    }

    @Test
    public void whenAddSecondEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        assertThat(array[1], is (2));
    }

    @Test
    public void whenIndexExistThenSetEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        sa.set(1, 3);
        assertThat(array[1], is (3) );
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenIndexNotExist() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        sa.set(2, 3);
    }

    @Test
    public void whenIndexExistThenRemoveEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.add(4);
        sa.remove(2);
        assertThat(array[2], is (4));
    }

    @Test
    public void whenIndexExistThenGetEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        assertThat(sa.get(0), is (1));
        assertThat(sa.get(1), is (2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElWithNotExistIndex() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        sa.get(2);
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.add(4);
        Iterator<Integer> it = sa.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is (1));
        assertThat(it.next(), is (2));
        assertThat(it.next(), is (3));
        assertThat(it.next(), is (4));
        assertFalse(it.hasNext());
    }
}