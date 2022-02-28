package ru.job4j.ood.lsp.park;

public class Car extends Vehicle {

    private static final int CAR_SIZE = 1;

    public Car() {
        super();
    }

    public static int getDefaultSize() {
        return CAR_SIZE;
    }
}