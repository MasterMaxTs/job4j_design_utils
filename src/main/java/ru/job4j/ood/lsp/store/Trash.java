package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Trash extends Store {

    private final List<Food> trProducts = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        if (accept(product)) {
            trProducts.add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Food product) {
        return trProducts.remove(product);
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(trProducts);
    }

    @Override
    public boolean accept(Food food) {
        return delta(food) <= 0;
    }
}
