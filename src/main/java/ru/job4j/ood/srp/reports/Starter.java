package ru.job4j.ood.srp.reports;

import java.util.Calendar;

public class Starter {
    public static void main(String[] args) {
        MemStore memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Igor", now, now, 84000);
        Employee emp2 = new Employee("Viktor", now, now, 112000);
        memStore.add(emp1);
        memStore.add(emp2);
        ItReport itr = new ItReport(memStore);
        String outFile = "./src/main/java/ru/job4j/ood/srp"
                + "/reports/html/report.html";
        System.out.println(
                itr.getHtmlReport("IT_Report", itr.generate(em -> true), outFile)
        );
    }
}
