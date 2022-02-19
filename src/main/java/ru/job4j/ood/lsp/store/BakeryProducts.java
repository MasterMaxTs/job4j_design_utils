package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.Date;

public class BakeryProducts extends Food {

    public BakeryProducts(String name, Calendar expiryDate, Calendar createDate,
                          double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
