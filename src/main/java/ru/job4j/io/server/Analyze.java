package ru.job4j.io.server;

import java.io.*;

public class Analyze {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))
        ) {
            String str;
            StringBuilder stringBuilder = new StringBuilder();
            while ((str = read.readLine()) != null) {
                String serverStatus = str.substring(0, 3);
                String statusTime = str.substring(4);
                if (serverStatus.equals("400") || serverStatus.equals("500")) {
                    stringBuilder.append(statusTime).append(";");
                    do {
                        str = read.readLine();
                        if (str == null) {
                            break;
                        }
                        serverStatus = str.substring(0, 3);
                    } while (serverStatus.equals("400") || serverStatus.equals("500"));
                    if (str != null) {
                        statusTime = str.substring(4);
                    }
                    stringBuilder.append(statusTime).append(";").append(System.lineSeparator());
                }
            }
            writer.write(stringBuilder.toString());
            System.out.println("File has been written in '" + target + " '");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analyze analyze = new Analyze();
        String source = "./src/main/java/ru/job4j/io/server/server.log";
        String target = "./src/main/java/ru/job4j/io/server/unavailable.scv";
        analyze.unavailable(source, target);
    }
}
