package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Trash extends Store {

    private final List<Food> trProducts = new ArrayList<>();

    @Override
    public void add(Food product) {
        trProducts.add(product);
    }

    @Override
    public boolean remove(Food product) {
        return trProducts.remove(product);
    }

    @Override
    public List<Food> get() {
        return trProducts;
    }
}
