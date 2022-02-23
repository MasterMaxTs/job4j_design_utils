package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Store {

    private final List<Food> shProducts = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        if (accept(product)) {
            shProducts.add(product);
            return true;
        }
        return false;
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
    public boolean accept(Food food) {
        if (delta(food) >= 0.25 && delta(food) <= 0.75) {
            return true;
        }
        if (delta(food) > 0 && delta(food) < 0.25) {
            food.setPrice(
                    food.getPrice() * (1 - (double) food.getDiscount() / 100)
            );
            getDiscountInfo(food);
            return true;
        }
        return false;
    }

    private void getDiscountInfo(Food food) {
        System.out.println(food
                + " has price including discount\n");
    }
}
