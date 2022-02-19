package ru.job4j.ood.lsp.store;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    private final Strategy strategy;

    public ControlQuality(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(List<Food> products, Calendar currentDate) {
        strategy.redistribute(products, currentDate);
    }
}
