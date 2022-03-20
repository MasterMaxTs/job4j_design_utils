package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Store {

    private final List<Food> whProducts = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (accept(product)) {
            whProducts.add(product);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean remove(Food product) {
        return whProducts.remove(product);
    }

    @Override
    public List<Food> clear() {
        List<Food> rsl = new ArrayList<>(whProducts);
        whProducts.clear();
        return rsl;
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(whProducts);
    }

    @Override
    public boolean accept(Food food) {
        return delta(food) > 0.75 && delta(food) <= 1;
    }
}
