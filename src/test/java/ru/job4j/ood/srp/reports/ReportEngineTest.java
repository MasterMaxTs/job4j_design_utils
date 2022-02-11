package ru.job4j.ood.srp.reports;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ReportEngineTest {

    private MemStore memStore;
    private Store store;
    private static String ls;

    private String getTextReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name; Hired; Fired; Salary").append(ls);
        for (Employee emp
                : memStore.getEmployees()) {
            sb.append(emp.getName()).append(";");
            sb.append(emp.getHired()).append(";");
            sb.append(emp.getFired()).append(";");
            sb.append(emp.getSalary()).append(";");
            sb.append(ls);
        }
        return sb.toString();
    }

    private String getTextHrReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name; Salary").append(ls);
        List<Employee> employees = memStore.getEmployees();
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        for (Employee emp
                : employees) {
            sb.append(emp.getName()).append(";");
            sb.append(emp.getSalary()).append(";");
            sb.append(ls);
        }
        return sb.toString();
    }

    @Before
    public void whenSetUp() {
        memStore = new MemStore();
        store = memStore;
        ls = System.lineSeparator();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Igor", now, now, 84000);
        Employee emp2 = new Employee("Viktor", now, now, 112000);
        memStore.add(emp1);
        memStore.add(emp2);
    }

    @Test
    public void whenConvertSalaryToAnotherCurrency() {
        AccountingReport acr = new AccountingReport(store);
        double coefficient = 0.5;
        List<Double> expected = List.of((double) 42000, (double) 56000);
        List<Double> rsl = acr.modifySalary(
                memStore.getEmployees(), coefficient)
                .stream()
                .map(Employee::getSalary)
                .collect(Collectors.toList());
        assertEquals(expected, rsl);
   }

    @Test
    public void whenGenerateReportForAccountingDepartment() {
        String expected = getTextReport();
        AccountingReport acr = new AccountingReport(store);
        assertEquals(expected, acr.generate(em -> true));
    }

    @Test
    public void whenGenerateReportForHRDepartment() {
        String expected = getTextHrReport();
        HrReport hrr = new HrReport(store);
        assertEquals(expected, hrr.generate(em -> true));
    }

    @Test
    public void whenGenerateReportForITDepartment() {
        String text = getTextReport();
        String template = "<!DOCTYPE html>"
                            + ls
                            + "<html>"
                            + ls
                            + "<head>"
                            + ls
                            + "<meta http-equiv=\"Content-Type\" "
                            + "content=\"text/html; charset=UTF-8\">"
                            + ls
                            + "<title>Report</title>"
                            + ls
                            + "</head>"
                            + ls
                            + "<body>"
                            + ls
                            + "$body"
                            + "</body>"
                            + ls
                            + "</html>";
        String expected = template.replace("$body", text);
        ItReport itr = new ItReport(store);
        assertEquals(expected, itr.generate(em -> true));
    }
}