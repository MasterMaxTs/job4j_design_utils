package ru.job4j.nio.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("./");
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(filePath, duplicatesVisitor);
        List<FileProperty> rsl = duplicatesVisitor.getDuplicateFiles();
        System.out.println("\n--Searching " + rsl.size()
                                + " duplicate files in directory: " + filePath.toAbsolutePath());
        rsl.forEach(System.out::println);
    }

    static class DuplicatesVisitor extends SimpleFileVisitor<Path> {
        private final Set<FileProperty> uniqueFiles = new HashSet<>();
        private final List<FileProperty> duplicateFiles = new ArrayList<>();

        public List<FileProperty> getDuplicateFiles() {
            return duplicateFiles;
        }

        @Override
        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs)
                                                                        throws IOException {
            FileProperty fileProperty = new FileProperty(
                    filePath.toFile().length(), filePath.toFile().getName()
            );
            if (!uniqueFiles.add(fileProperty)) {
                duplicateFiles.add(fileProperty);
            }
            System.out.println("Scanning:\n" + filePath.toAbsolutePath());
            return super.visitFile(filePath, attrs);
        }
    }
}
