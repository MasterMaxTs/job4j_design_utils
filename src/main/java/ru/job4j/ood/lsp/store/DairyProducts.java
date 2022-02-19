package ru.job4j.ood.lsp.store;

import java.util.Calendar;

public class DairyProducts extends Food {

    public DairyProducts(String name, Calendar expiryDate, Calendar createDate,
                         double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
