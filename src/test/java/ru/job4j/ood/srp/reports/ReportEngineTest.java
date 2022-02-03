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

    @Before
    public void whenSetUp() {
        memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Igor", now, now, 84000);
        Employee emp2 = new Employee("Viktor", now, now, 112000);
        memStore.add(emp1);
        memStore.add(emp2);
    }

    @Test
    public void whenConvertSalaryToAnotherCurrency() {
        AccountingReport acr = new AccountingReport(memStore);
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
        Store store = memStore;
        StringBuilder sb = new StringBuilder();
        String ls = System.lineSeparator();
        sb.append("Name; Hired; Fired; Salary").append(ls);
        for (Employee emp
                : memStore.getEmployees()) {
            sb.append(emp.getName()).append(";");
            sb.append(emp.getHired()).append(";");
            sb.append(emp.getFired()).append(";");
            sb.append(emp.getSalary()).append(";");
            sb.append(ls);
        }
        String expected = sb.toString();
        AccountingReport acr = new AccountingReport(store);
        String rsl = acr.generate(em -> true);
        assertEquals(expected, rsl);
    }

    @Test
    public void whenGenerateReportForHRDepartment() {
        Store store = memStore;
        StringBuilder sb = new StringBuilder();
        String ls = System.lineSeparator();
        sb.append("Name; Salary").append(ls);
        List<Employee> employees = memStore.getEmployees();
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        for (Employee emp
                : employees) {
            sb.append(emp.getName()).append(";");
            sb.append(emp.getSalary()).append(";");
            sb.append(ls);
        }
        String expected = sb.toString();
        HrReport hrr = new HrReport(store);
        String rsl = hrr.generate(em -> true);
        assertEquals(expected, rsl);
    }
}