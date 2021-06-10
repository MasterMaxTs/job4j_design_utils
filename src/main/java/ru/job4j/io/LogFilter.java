package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    static final int STATUS_POSITION = 8;

    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file))
        ) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] strArr = s.split(" ");
                if (strArr[STATUS_POSITION].equals("404")) {
                    rsl.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            for (String str
                    : log) {
                out.println(str);
            }
            System.out.println("log has been written in file \"" + file + "\"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> logs = filter("log.txt");
        logs.forEach(System.out::println);
        save(logs, "404.txt");
    }
}
