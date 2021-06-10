package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.config.Config;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String readPath = "./src/main/java/ru/job4j/io/config/app.properties";
        String writePath = "./data/pair_without_comment.properties";
        Config config = new Config(readPath, writePath);
        config.load();
        assertThat(config.value("user.name"), is("maxim"));
        assertThat(config.value(
                "hibernate.connection.url"), is ("jdbc:postgresql://127.0.0.1:5432/trackstudio")
        );
        assertThat(config.value("user.age"), is (nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsNotInPropertiesThanException() {
        String readPath = "./src/main/java/ru/job4j/io/config/app2.properties";
        String writePath = "./data/pair_without_comment.properties";
        Config config = new Config(readPath, writePath);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsNotInPropertiesThanException() {
        String readPath = "./src/main/java/ru/job4j/io/config/app3.properties";
        String writePath = "./data/pair_without_comment.properties";
        Config config = new Config(readPath, writePath);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDelimiterIsNotInPropertiesThanException() {
        String readPath = "./src/main/java/ru/job4j/io/config/app4.properties";
        String writePath = "./data/pair_without_comment.properties";
        Config config = new Config(readPath, writePath);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFileWithPropertiesIsEmptyThanException() {
        String readPath = "./src/main/java/ru/job4j/io/config/app5.properties";
        String writePath = "./data/pair_without_comment.properties";
        Config config = new Config(readPath, writePath);
        config.load();
    }
}