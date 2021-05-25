package ru.job4j.tree;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildIsExistThanFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertThat(
                List.of(
                        tree.findBy(1).get().children.get(0).getValue(),
                        tree.findBy(1).get().children.get(1).getValue()
                        ),
        is(
                List.of(2, 3)
        ));
        assertFalse(tree.add(1, 3));
    }

    @Test
    public void whenChildIsNotExistThanTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 3);
        tree.add(1, 4);
        assertTrue(tree.add(1, 5));
    }

    @Test
    public void whenParentIsNotExistThanFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertFalse(tree.add(4, 5));
    }

    @Test
    public void whenTreeIsBinary() {
        SimpleTree<Integer> simpleTree = new SimpleTree<>(1);
        simpleTree.add(1, 2);
        simpleTree.add(1, 3);
        simpleTree.add(3, 4);
        simpleTree.add(3, 5);
        assertTrue(simpleTree.isBinary());
    }

    @Test
    public void whenTreeIsNotBinary() {
        SimpleTree<Integer> simpleTree = new SimpleTree<>(1);
        simpleTree.add(1, 2);
        simpleTree.add(1, 3);
        simpleTree.add(3, 5);
        simpleTree.add(3, 6);
        simpleTree.add(3, 7);
        assertFalse(simpleTree.isBinary());
    }
}