package ru.job4j.collection.list;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.Matchers.is;

public class ForwardLinkedWithRevertTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinkedWithRevert<Integer> linked = new ForwardLinkedWithRevert<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinkedWithRevert<Integer> linked = new ForwardLinkedWithRevert<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinkedWithRevert<Integer> emptyList = new ForwardLinkedWithRevert<>();
        assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinkedWithRevert<Integer> singleList = new ForwardLinkedWithRevert<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }
}