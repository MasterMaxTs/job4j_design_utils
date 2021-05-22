package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public boolean put(K key, V value) {
        if (count == table.length * LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> node = new MapEntry<>(key, value);
        int hash = hash(node.key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null) {
            K key0 = table[index].key;
            if (!key0.equals(key)) {
                return false;
            }
            table[index].setValue(value);
            return true;
        }
        table[index] = node;
        count++;
        modCount++;
        return true;
    }

    @Override
    public V get(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null && table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash == 0 ? 0 : hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] temp = table;
        table = new MapEntry[table.length * 2];
        for (MapEntry<K, V> oldNode : temp) {
            if (oldNode != null) {
                int hash = hash(oldNode.key.hashCode());
                int index = indexFor(hash);
                table[index] = oldNode;
            }
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int i = 0;
            int expectModCount = modCount;

            @Override
            public boolean hasNext() {
                while (i < table.length) {
                    if (table[i] != null) {
                        return true;
                    }
                    i++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[i++].key;
            }
        };
    }
}
