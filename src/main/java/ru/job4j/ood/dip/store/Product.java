package ru.job4j.ood.dip.store;

import java.util.Objects;

public class Product {

    private final int vendorCode;
    private final String name;
    private final String description;

    public Product(int vendorCode, String name, String description) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.description = description;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return vendorCode == product.vendorCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode);
    }
}
