package ru.job4j.ood.lsp.park;

public abstract class Parking {

    public abstract boolean park(Vehicle vehicle);

    public abstract boolean accept(int size);

    public abstract Vehicle pulledOut(int i);
}
