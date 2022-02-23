package ru.job4j.ood.lsp.store;

import java.util.List;

public class ControlQuality {

    public static void distribute(List<Food> foods, List<Store> stores) {
        stores.forEach(
                store -> {
                        for (Food f
                                : foods) {
                            store.add(f);
                        }
                });
        }
}
