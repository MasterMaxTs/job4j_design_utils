package ru.job4j.io.validation;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("File is not found +%s", file.getAbsoluteFile())
            );
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Directory is not found %s", file.getAbsoluteFile())
            );
        }
        System.out.printf("Size is %d", file.getTotalSpace());
        for (File subfile
                : file.listFiles()) {
            if (!subfile.isDirectory()) {
                System.out.printf("file [%s - %d bytes]%n", subfile.getName(), subfile.length());
            } else {
                System.out.println("Directory <" + subfile.getName() + ">");
            }
        }
    }
}
