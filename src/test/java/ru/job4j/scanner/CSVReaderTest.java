package ru.job4j.scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.io.namedargs.ArgsName;

import java.io.File;
import java.nio.file.Files;
import java.util.NoSuchElementException;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterAllColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        Files.writeString(file.toPath(), data);
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=education,last_name,name,age"
        });
        String expected = String.join(
                System.lineSeparator(),
                "education;last_name;name;age",
                "Bachelor;Smith;Tom;20",
                "Undergraduate;Johnson;Jack;25",
                "Secondary special;Brown;William;30"
        ).concat(System.lineSeparator());
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenInvalidFilterThanException() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter=name,surname"
        };
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = csvReader.validate(args);
        csvReader.handle(argsName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenInvalidArgumentsThanException() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=" + target.getAbsolutePath(),
                "-filter="
        };
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = csvReader.validate(args);
        csvReader.handle(argsName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenInvalidValueInOUT() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(),
                "-delimiter=;",
                "-out=sdotout",
                "-filter=name,age"
        };
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = csvReader.validate(args);
        csvReader.handle(argsName);
    }
}