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
        array = new Integer[] {1, 2, null, 4, null, 6};
    }
    @Test
    public void whenAddFirstEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(3);
        assertThat(array[0], is (3));
    }

    @Test
    public void whenAddSecondEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.add(3);
        sa.add(5);
        assertThat(array[1], is (5));
    }

    @Test
    public void whenIndexExistThenSetEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.set(2, 3);
        assertThat(array[2], is (3) );
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenIndexNotExist() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.set(6, 7);
    }

    @Test
    public void whenIndexExistThenRemoveEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.remove(2);
        assertThat(array[2], is (4));
    }

    @Test
    public void whenIndexExistThenGetEl() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        assertThat(sa.get(0), is (1));
        assertThat(sa.get(1), is (2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElWithNotExistIndex() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        sa.get(6);
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> sa = new SimpleArray<>(array);
        Iterator<Integer> it = sa.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is (1));
        assertThat(it.next(), is (2));
        assertThat(it.next(), is (4));
        assertThat(it.next(), is (6));
        assertFalse(it.hasNext());
    }
}