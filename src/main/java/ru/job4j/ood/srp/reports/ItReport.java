package ru.job4j.ood.srp.reports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class ItReport extends ReportEngine {

    private static final String FILE_TEMPLATE = "./src/main/java/ru/job4j/ood/srp"
            + "/reports/html/template.html";

    public ItReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ls = System.lineSeparator();
        text.append("Name; Hired; Fired; Salary").append(ls);
        for (Employee emp
                : super.getStore().findBy(filter)) {
            text.append(emp.getName()).append(";");
            text.append(emp.getHired()).append(";");
            text.append(emp.getFired()).append(";");
            text.append(emp.getSalary()).append(";");
            text.append(ls);
        }
        return text.toString();
    }

    public String getHtmlReport(String title, String body, String outFile) {
        Path path = Path.of(FILE_TEMPLATE);
        Path outPath = Path.of(outFile);
        String rsl = "";
        try {
            String read = Files.readString(path);
            rsl = read.replace("$title", title).replace("$body", body);
            Files.writeString(outPath, rsl);
            System.out.println("HTML Report was generated successfully and written to a file: "
                    + outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
