package ru.job4j.ood.lsp.park;

import java.util.List;

public abstract class Parking {

    public abstract boolean park(Vehicle vehicle, int cell);

    public abstract int accept(int size, int cell);

    public abstract List<Integer> getAvailableCells(int start, int finish);

    public abstract Vehicle pulledOut(int cell);
}
