package ru.job4j.ood.lsp.store;

import java.util.List;

public class ControlQuality {

    private final List<Food> foods;
    private final List<Store> stores;

    public ControlQuality(List<Food> foods, List<Store> stores) {
        this.foods = foods;
        this.stores = stores;
        distribute();
    }

    private void distribute() {
        stores.forEach(
                store -> {
                        for (Food f
                                : foods) {
                            if (store.accept(f)) {
                                store.add(f);
                            }
                        }
                });
    }

    public void resort(List<Store> stores) {
        stores.forEach(
                store -> {
                    for (Food f
                            : store.clear()) {
                        if (store.accept(f)) {
                            store.add(f);
                        }
                    }
                }
        );
    }
}
