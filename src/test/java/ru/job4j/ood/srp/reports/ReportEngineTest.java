package ru.job4j.ood.srp.reports;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportEngineTest {

    private MemStore memStore;
    private Store store;
    private Calendar now;
    private static String ls;

    @Before
    public void whenSetUp() {
        memStore = new MemStore();
        store = memStore;
        ls = System.lineSeparator();
        now = Calendar.getInstance();
        Employee emp1 = new Employee("Igor", now, now, 84000.0);
        Employee emp2 = new Employee("Viktor", now, now, 112000.0);
        memStore.add(emp1);
        memStore.add(emp2);
    }

    @Test
    public void whenConvertSalaryToAnotherCurrency() {
        AccountingReport acr = new AccountingReport(store);
        double coefficient = 0.5;
        List<Employee> employees = acr.modifySalary(memStore.getEmployees(),
                coefficient);
        List<Double> rsl = List.of(
                employees.get(0).getSalary(),
                employees.get(1).getSalary()
        );
        List<Double> expected = List.of((double) 42000, (double) 56000);
        assertEquals(expected, rsl);
   }

    @Test
    public void whenGenerateReportForAccountingDepartment() {
        String expected = "Name; Hired; Fired; Salary"
                            + ls
                            + "Igor;"
                            + now + ";"
                            + now + ";"
                            + 84000.0 + ";"
                            + ls
                            + "Viktor;"
                            + now + ";"
                            + now + ";"
                            + 112000.0 + ";"
                            + ls;
        AccountingReport acr = new AccountingReport(store);
        assertEquals(expected, acr.generate(em -> true));
    }

    @Test
    public void whenGenerateReportForHRDepartment() {
        String expected = "Name; Salary"
                            + ls
                            + "Viktor;"
                            + 112000.0 + ";"
                            + ls
                            + "Igor;"
                            + 84000.0 + ";"
                            + ls;
        HrReport hrr = new HrReport(store);
        assertEquals(expected, hrr.generate(em -> true));
    }

    @Test
    public void whenGenerateReportForITDepartment() {
        String text = "Name; Hired; Fired; Salary"
                        + ls
                        + "Igor;"
                        + now + ";"
                        + now + ";"
                        + 84000.0 + ";"
                        + ls
                        + "Viktor;"
                        + now + ";"
                        + now + ";"
                        + 112000.0 + ";"
                        + ls;
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