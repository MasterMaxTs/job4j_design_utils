package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Store {

    private final List<Food> shProducts = new ArrayList<>();

    @Override
    public void add(Food product) {
        shProducts.add(product);
    }

    @Override
    public boolean remove(Food product) {
        return shProducts.remove(product);
    }

    @Override
    public List<Food> get() {
        return shProducts;
    }
}
