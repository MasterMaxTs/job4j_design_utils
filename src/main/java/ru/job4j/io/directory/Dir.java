package ru.job4j.io.directory;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists + %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        System.out.printf("Project directory size: %s%n", file.getTotalSpace());
        for (File subFile
                : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%s" + " - " + "%d%n", subFile.getName(), subFile.length());
        }
    }
}
