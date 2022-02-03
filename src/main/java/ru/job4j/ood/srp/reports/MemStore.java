package ru.job4j.ood.srp.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee> employees;

    public MemStore() {
        employees = new ArrayList<>();
    }

    public void add(Employee emp) {
        employees.add(emp);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}
