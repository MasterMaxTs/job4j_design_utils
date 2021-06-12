package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.config.Config;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/java/ru/job4j/io/config/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user.name"), is("maxim"));
        assertThat(config.value(
                "hibernate.connection.url"), is ("jdbc:postgresql://127.0.0.1:5432/trackstudio")
        );
        assertThat(config.value("user.age"), is (nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsNotInPropertiesThanException() {
        String path = "./src/main/java/ru/job4j/io/config/app2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDelimiterIsNotInPropertiesThanException() {
        String path = "./src/main/java/ru/job4j/io/config/app3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFileWithPropertiesIsEmptyThanException() {
        String path= "./src/main/java/ru/job4j/io/config/app4.properties";
        Config config = new Config(path);
        config.load();
    }
}