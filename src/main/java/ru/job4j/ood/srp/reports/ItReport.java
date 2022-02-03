package ru.job4j.ood.srp.reports;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Predicate;

public class ItReport extends ReportEngine {

    private final int port = 8090;

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
        String msg = text.toString();
        try  (ServerSocket server = new ServerSocket(port)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream()) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(msg.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
