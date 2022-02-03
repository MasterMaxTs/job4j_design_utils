package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public abstract class ReportEngine {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public abstract String generate(Predicate<Employee> filter);

    public Store getStore() {
        return store;
    }
}
