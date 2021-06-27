package ru.job4j.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect entry of arguments!"
                    + " Usage java -jar dir.jar START_FOLDER_PATH, SEARCHING_FILE_EXTENSION");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                                        .forEach(System.out::println);
        System.out.println("------ Searching count of files with extension \"" + args[1]
                + "\" in directory " + start.toAbsolutePath() + " is "
                + search(start, p -> p.toFile().getName().endsWith(args[1])).size());
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
