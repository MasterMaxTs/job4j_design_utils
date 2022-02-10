package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class ItReport extends ReportEngine {

    public ItReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder html = new StringBuilder();
        String ls = System.lineSeparator();
        html.append("<!DOCTYPE html>").append(ls);
        html.append("<html>").append(ls);
        html.append("<head>").append(ls);
        html.append("<meta http-equiv=\"Content-Type\" content=\"text/html;"
                + " charset=UTF-8\">").append(ls);
        html.append("<title>Report</title>").append(ls);
        html.append("</head>").append(ls);
        html.append("<body>").append(ls);
        html.append("Name; Hired; Fired; Salary").append(ls);
        for (Employee emp
                : super.getStore().findBy(filter)) {
            html.append(emp.getName()).append(";");
            html.append(emp.getHired()).append(";");
            html.append(emp.getFired()).append(";");
            html.append(emp.getSalary()).append(";");
            html.append(ls);
        }
        html.append("</body>").append(ls);
        html.append("</html>").append(ls);
        return html.toString();
    }
}
