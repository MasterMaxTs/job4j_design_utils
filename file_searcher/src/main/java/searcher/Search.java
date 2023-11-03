package searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс используется для поиска файлов по условию
 */
public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition)
                                        throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Вспомогательный класс, реализующий логику поиска, в виде хранилища
     * найденных файлов
     */
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
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (condition.test(file)) {
                files.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
