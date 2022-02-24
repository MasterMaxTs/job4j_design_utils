package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Store {

    private final List<Food> shProducts = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (accept(product)) {
            shProducts.add(product);
            if (delta(product) > 0 && delta(product) < 0.25) {
                product.setPrice(
                        product.getPrice() * (1 - (double) product.getDiscount() / 100)
                );
                getDiscountInfo(product);
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean remove(Food product) {
        return shProducts.remove(product);
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(shProducts);
    }

    @Override
    public boolean accept(Food product) {
        return delta(product) > 0 && delta(product) <= 0.75;
    }

    private void getDiscountInfo(Food food) {
        System.out.println(food
                + " has price including discount\n");
    }
}
