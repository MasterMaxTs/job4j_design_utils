package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public abstract class Store {

    protected static Calendar current = Calendar.getInstance();

    protected abstract boolean add(Food product);

    protected abstract boolean remove(Food product);

    protected abstract List<Food> extract();

    protected abstract List<Food> get();

    protected abstract boolean accept(Food food);

    protected double delta(Food product) {
        long expireDateInMillis = product.getExpiryDate().getTime().getTime();
        long createDateInMillis = product.getCreateDate().getTime().getTime();
        long currentDateInMillis = current.getTime().getTime();
        long delta1 = expireDateInMillis - currentDateInMillis;
        long delta2 = expireDateInMillis - createDateInMillis;
        return (double) delta1 / delta2;
    }
}
