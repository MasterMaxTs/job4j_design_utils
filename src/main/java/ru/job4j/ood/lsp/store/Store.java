package ru.job4j.ood.lsp.store;

import java.util.List;

public abstract class Store {

    public abstract void add(Food product);

    public abstract boolean remove(Food product);

    public abstract List<Food> get();
}
