package ru.job4j.ood.srp.reports;

import java.util.List;
import java.util.function.Predicate;

public class AccountingReport extends ReportEngine {

    private static double coefficient = 1;

    public AccountingReport(Store store) {
        super(store);
    }

    public List<Employee> modifySalary(List<Employee> employees,
                                       double coefficient) {
        for (Employee emp
                : employees) {
            emp.setSalary(emp.getSalary() * coefficient);
        }
        return employees;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ls = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(ls);
        for (Employee emp
                : modifySalary(super.getStore().findBy(filter), coefficient)
             ) {
            text.append(emp.getName()).append(";");
            text.append(emp.getHired()).append(";");
            text.append(emp.getFired()).append(";");
            text.append(emp.getSalary()).append(";");
            text.append(ls);
        }
        return text.toString();
    }
}
