package ru.job4j.ood.lsp.park;

public abstract class Parking {

    public abstract boolean park(Vehicle vehicle, int cell);

    public abstract int accept(int size, int cell);

    public abstract Vehicle pulledOut(int i);
}
