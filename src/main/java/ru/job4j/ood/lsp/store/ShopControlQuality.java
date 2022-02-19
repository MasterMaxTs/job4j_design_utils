package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public class ShopControlQuality implements Strategy {

    private final Store store;

    public ShopControlQuality(Store store) {
        this.store = store;
    }

    @Override
    public void redistribute(List<Food> products, Calendar current) {
        for (Food p
                : products) {
            if (delta(p, current) >= 0.25 && delta(p, current) <= 0.75) {
                store.add(p);
            }
            if (delta(p, current) > 0 && delta(p, current) < 0.25) {
                double discount = p.getDiscount() / 100;
                p.setPrice(p.getPrice() * (1 - discount));
                store.add(p);
            }
        }
    }
}
