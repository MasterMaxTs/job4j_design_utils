package ru.job4j.ood.srp.reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

public class JsonReport extends ReportEngine {

    public JsonReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = super.getStore().findBy(filter);
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(employees);
    }
}
