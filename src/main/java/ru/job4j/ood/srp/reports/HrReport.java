package ru.job4j.ood.srp.reports;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport extends ReportEngine {

    public HrReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ls = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary").append(ls);
        List<Employee> employees = super.getStore().findBy(filter);
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        for (Employee emp
                : employees) {
            text.append(emp.getName()).append(";");
            text.append(emp.getSalary()).append(";");
            text.append(ls);
        }
        return text.toString();
    }
}
