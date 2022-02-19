package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.Objects;

public class Food {

    private String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private double price;
    private final int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate,
                double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name
                + '\''
                + ", expiryDate=" + expiryDate.getTime()
                + ", createDate=" + createDate.getTime()
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price);
    }
}
