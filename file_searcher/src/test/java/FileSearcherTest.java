import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import searcher.ArgsName;
import searcher.FileSearcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FileSearcherTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenSearchByFileNameWithoutExtension() throws IOException {
        File root = temporaryFolder.getRoot();
        File file1 = temporaryFolder.newFile("log.bin");
        File file2 = temporaryFolder.newFile("log.tmp");
        File file3 = temporaryFolder.newFile("log.txt");
        File target = temporaryFolder.newFile("target.txt");
        ArgsName argsName = ArgsName.of(
                new String[]
                        {
                            "-d=" + root,
                            "-c=log",
                            "-t=name",
                            "-o=" + target
                        }
        );
        Path directory = Path.of(argsName.get("d"));
        String condition = argsName.get("c");
        String typeSearch = argsName.get("t");
        Path out = Path.of(argsName.get("o"));
        FileSearcher searcher = new FileSearcher();
        searcher.searchFiles(directory, condition, typeSearch, out);
        try (Scanner sc = new Scanner(out)) {
            List<String> rsl = new ArrayList<>(
                    List.of(sc.nextLine(), sc.nextLine(), sc.nextLine())
            );
            /* Добавлена сортировка для исправления ошибки тестирования на
            сервере Travis CI */
            rsl.sort(String::compareTo);
            List<String> expected = List.of(
                    file1.getAbsolutePath(),
                    file2.getAbsolutePath(),
                    file3.getAbsolutePath()
            );
            assertThat(rsl, is(expected));
        }
    }

        @Test
        public void whenSearchByFullFileName() throws IOException {
            File root = temporaryFolder.getRoot();
            File file1 = temporaryFolder.newFile("log.bin");
            File file2 = temporaryFolder.newFile("log.tmp");
            File file3 = temporaryFolder.newFile("log.txt");
            File target = temporaryFolder.newFile("target.txt");
            ArgsName argsName = ArgsName.of(
                new String[]
                        {
                            "-d=" + root,
                            "-c=log.tmp",
                            "-t=name",
                            "-o=" + target
                        }
            );
            Path directory = Path.of(argsName.get("d"));
            String condition = argsName.get("c");
            String typeSearch = argsName.get("t");
            Path out = Path.of(argsName.get("o"));
            FileSearcher searcher = new FileSearcher();
            searcher.searchFiles(directory, condition, typeSearch, out);
            try (Scanner sc = new Scanner(out)) {
                List<String> files = new ArrayList<>();
                while (sc.hasNextLine()) {
                    files.add(sc.nextLine());
                }
                assertThat(files.get(0), is(file2.getAbsolutePath()));
                assertThat(files.size(), is(1));
            }
        }

        @Test
    public void whenSearchUsageRegex() throws IOException {
            File root = temporaryFolder.getRoot();
            File file1 = temporaryFolder.newFile("log.bin");
            File file2 = temporaryFolder.newFile("log.tmp");
            File file3 = temporaryFolder.newFile("log.py");
            File target = temporaryFolder.newFile("target.txt");
            ArgsName argsName = ArgsName.of(
                    new String[]
                            {
                                "-d=" + root,
                                "-c=.+\\.py\\Z",
                                "-t=regex",
                                "-o=" + target
                            }
            );
            Path directory = Path.of(argsName.get("d"));
            String condition = argsName.get("c");
            String typeSearch = argsName.get("t");
            Path out = Path.of(argsName.get("o"));
            FileSearcher searcher = new FileSearcher();
            searcher.searchFiles(directory, condition, typeSearch, out);
            try (Scanner sc = new Scanner(out)) {
                List<String> files = new ArrayList<>();
                while (sc.hasNextLine()) {
                    files.add(sc.nextLine());
                }
                assertThat(files.get(0), is(file3.getAbsolutePath()));
                assertThat(files.size(), is(1));
            }
        }

    @Test
    public void whenSearchUsageMask() throws IOException {
        File root = temporaryFolder.getRoot();
        File file1 = temporaryFolder.newFile("log.txt");
        File file2 = temporaryFolder.newFile("log.tmp");
        File file3 = temporaryFolder.newFile("words.tmp");
        File file4 = temporaryFolder.newFile("bit.bmp");
        File target = temporaryFolder.newFile("target.txt");
        ArgsName argsName = ArgsName.of(
                new String[]
                        {
                            "-d=" + root,
                            "-c=*?mp",
                            "-t=mask",
                            "-o=" + target
                        }
        );
        Path directory = Path.of(argsName.get("d"));
        String condition = argsName.get("c");
        String typeSearch = argsName.get("t");
        Path out = Path.of(argsName.get("o"));
        FileSearcher searcher = new FileSearcher();
        searcher.searchFiles(directory, condition, typeSearch, out);
        try (Scanner sc = new Scanner(out)) {
            List<String> files = new ArrayList<>();
            while (sc.hasNextLine()) {
                files.add(sc.nextLine());
            }
            /* Добавлена сортировка для исправления ошибки тестирования на
            сервере Travis CI */
            files.sort(String::compareTo);
            List<String> expected = List.of(
                    file4.getAbsolutePath(),
                    file2.getAbsolutePath(),
                    file3.getAbsolutePath()
            );
            assertThat(files, is(expected));
            assertThat(files.size(), is(3));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidNumberOfEntryArgumentsThanException() {
        String[] args = new String[]
                {
                    "-d=c:/",
                    "-c=**.tmp",
                    "-t=mask"
                };
        FileSearcher searcher = new FileSearcher();
        searcher.validate(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDirectoryForSearchNotExist() throws IOException {
        File file = temporaryFolder.newFile("folder");
        String[] args = new String[]
                {
                    "-d=" + file,
                    "-c=*.?mp",
                    "-t=mask",
                    "-o=c:/target/files.txt"
                };
        FileSearcher searcher = new FileSearcher();
        searcher.validate(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidEntryArgumentThanException() {
        String[] args = new String[]
                {
                    "-d=c:/",
                    "-c=",
                    "-t=regex",
                    "-o=c:/target/files.txt"
                };
        FileSearcher searcher = new FileSearcher();
        searcher.validate(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidValueInTypeSearchParameterThanException() {
        String[] args = new String[]
                {
                    "-d=c:/",
                    "-c=result.txt",
                    "-t=fileName",
                    "-o=c:/target/files.txt"
                };
        FileSearcher searcher = new FileSearcher();
        searcher.validate(args);
    }
}