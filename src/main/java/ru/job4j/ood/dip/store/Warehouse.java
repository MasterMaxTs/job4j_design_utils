package ru.job4j.ood.dip.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Warehouse {

    /*
    * Поле класса представляет реализацию хранилища
    * в виде ArrayList, а не обстрацию, что является нарушением принципа DIP.
    * Если потребуется изменить реализацию хранилища, например на HashMap,
    * то в классе придется переписать логику для работы с новым
    * хранилищем, что повлечёт нарушение ещё и OCP принципа  */
    private final List<Product> products = new ArrayList<>();

    public boolean add(Product product) {
        return products.add(product);
    }

    public List<Product> find(Predicate<Product> filter) {
        return products
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public void delete(Predicate<Product> filter) {
        List<Product> removedProducts = find(filter);
        if (!removedProducts.isEmpty()) {
            removedProducts.forEach(products::remove);
            /*Нарушение DIP: зависимость логирования от реализации
            (консольный вывод)*/
            System.out.println("Delete success!");
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
