package ru.job4j.nio.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("./");
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(file, duplicatesVisitor);
        List<FileProperty> files = duplicatesVisitor.getDuplicateFiles();
        System.out.println("--Searching duplicate files in directory: "
                + file.toAbsolutePath());
        files.forEach(System.out::println);
    }

    static class DuplicatesVisitor extends SimpleFileVisitor<Path> {
        private final Set<FileProperty> uniqueFiles = new HashSet<>();
        private final List<FileProperty> duplicateFiles = new ArrayList<>();

        public List<FileProperty> getDuplicateFiles() {
            return duplicateFiles;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            FileProperty fileProperty = new FileProperty(0L, "");
            fileProperty.setSize(file.toFile().length());
            fileProperty.setName(file.toFile().getName());
            if (!uniqueFiles.add(fileProperty)) {
                duplicateFiles.add(fileProperty);
            }
            return super.visitFile(file, attrs);
        }
    }
}
