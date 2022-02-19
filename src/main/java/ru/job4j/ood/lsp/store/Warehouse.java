package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Store {

    private final List<Food> whProducts = new ArrayList<>();

    @Override
    public void add(Food product) {
        whProducts.add(product);
    }

    @Override
    public boolean remove(Food product) {
        return whProducts.remove(product);
    }

    @Override
    public List<Food> get() {
        return whProducts;
    }
}
