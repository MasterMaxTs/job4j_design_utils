package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Trash extends Store {

    private final List<Food> trProducts = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (accept(product)) {
            trProducts.add(product);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean remove(Food product) {
        return trProducts.remove(product);
    }

    @Override
    public List<Food> clear() {
        List<Food> rsl = new ArrayList<>(trProducts);
        trProducts.clear();
        return rsl;
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
