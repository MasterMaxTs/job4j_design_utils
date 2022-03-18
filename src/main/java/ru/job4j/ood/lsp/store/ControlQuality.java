package ru.job4j.ood.lsp.store;

import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {

    private final List<Food> foods;
    private final List<Store> stores;

    public ControlQuality(List<Food> foods, List<Store> stores) {
        this.foods = foods;
        this.stores = stores;
        distribute(foods);
    }

    private void distribute(List<Food> foods) {
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

    public void resort() {
        List<Food> redisProducts = collect();
        extract();
        distribute(redisProducts);
    }

    private void extract() {
        stores.forEach(
                store -> {
                    for (Food f
                            : foods) {
                        store.remove(f);
                    }
                }
        );
    }

    public List<Food> collect() {
        return stores
                .stream()
                .flatMap(s -> s.get().stream())
                .collect(Collectors.toList());
    }
}
