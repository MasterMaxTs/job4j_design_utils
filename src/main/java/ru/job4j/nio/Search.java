package ru.job4j.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".java"))
                                        .forEach(System.out::println);
        System.out.println("------ Searching file count in directory "
                + start.toAbsolutePath() + " : "
                + search(start, p -> p.toFile().getName().endsWith(".java")).size());
    }

    public static List<Path> search(Path root, Predicate<Path> condition)
                                        throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    static class SearchFiles extends SimpleFileVisitor<Path> {
        private final List<Path> files = new ArrayList<>();
        private final Predicate<Path> condition;

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        public List<Path> getPaths() {
            return files;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                                    throws IOException {
            if (this.condition.test(file)) {
                files.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
