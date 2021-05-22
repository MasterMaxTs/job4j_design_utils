package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class SimpleMapTest {

    @Test
    public void whenPut() {
        Map<Integer,String> sm = new SimpleMap<>();
        assertTrue(sm.put(1, "one"));
        assertTrue(sm.put(2, "two"));
    }

    @Test
    public void whenPutThanGet() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        assertTrue(sm.put(2, "three"));
        assertThat(sm.get(1), is("one"));
        assertThat(sm.get(2), is("three"));
    }

    @Test
    public void whenKeyIsExistThanGet() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        assertThat(sm.get(1), is("one"));
        assertThat(sm.get(2), is("two"));
    }

    @Test
    public void whenKeyIsNotExistThanGetNull() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        assertNull(sm.get(0));
    }

    @Test
    public void whenPutSameKeyThanGetNewValue() {
        Map<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(1, "two");
        assertThat(sm.get(1), is("two"));
    }

    @Test
    public void whenRemove() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        assertTrue(sm.remove(1));
        assertFalse(sm.remove(3));
    }

    @Test
    public void whenUseIterator() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        Iterator<Integer> it = sm.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenFailFastIterator() {
        Map<Integer,String> sm = new SimpleMap<>();
        sm.put(1, "one");
        sm.put(2, "two");
        Iterator<Integer> it = sm.iterator();
        it.next();
        sm.put(3, "three");
        it.next();
    }
}