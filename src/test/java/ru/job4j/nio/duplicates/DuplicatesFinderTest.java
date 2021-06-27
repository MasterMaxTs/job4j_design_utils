package ru.job4j.nio.duplicates;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.nio.duplicates.DuplicatesFinder.DuplicatesVisitor;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DuplicatesFinderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenDuplicatesAreExist() throws IOException {
        File tempFolder = folder.newFolder("folder");
        Path dir = Paths.get(tempFolder.getAbsolutePath());
        System.out.println(dir.toAbsolutePath());
        File file1 = new File(tempFolder, "file1.txt");
        File file2 = new File(tempFolder, "file2.txt");
        File nestedFolder1 = new File(tempFolder, "folder1");
        nestedFolder1.mkdir();
        File file3 = new File(nestedFolder1, "file3.txt");
        File file4 = new File(nestedFolder1, "file2.txt");
        File nestedFolder2 = new File(nestedFolder1, "folder2");
        nestedFolder2.mkdir();
        File file5 = new File(nestedFolder2, "file4.txt");
        File file6 = new File(nestedFolder2, "file1.txt");
        List<File> fileList = List.of(file1, file2, file3, file4, file5, file6);
        for (File f
                : fileList) {
            try (PrintWriter writer = new PrintWriter(f)) {
                writer.print("abc");
            }
        }
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(dir, duplicatesVisitor);
        List<FileProperty> rsl = duplicatesVisitor.getDuplicateFiles();
        assertThat(
                rsl, is(
                        List.of(
                new FileProperty(3L, "file2.txt"),
                new FileProperty(3L, "file1.txt")
        )));
    }
}