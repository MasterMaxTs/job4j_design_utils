package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public class TrashControl implements Strategy {

    private final Store store;

    public TrashControl(Store store) {
        this.store = store;
    }

    @Override
    public void redistribute(List<Food> products, Calendar current) {
        for (Food p
                : products) {
            if (delta(p, current) <= 0) {
                store.add(p);
            }
        }
    }
}
