package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public class WhControlQuality implements Strategy {

    private final Store store;

    public WhControlQuality(Store store) {
        this.store = store;
    }

    @Override
    public void redistribute(List<Food> products, Calendar current) {
        for (Food p
                : products) {
            if (delta(p, current) > 0.75 && delta(p, current) <= 1) {
                store.add(p);
            }
        }
    }
}
