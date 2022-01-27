package ru.job4j.tdd.template;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    private Generator templateGenerator;
    private Map<String, String> args;
    private String template;

    @Before
    public void whenSetUp() {
        templateGenerator = new TemplateEngine();
        args = new HashMap<>(
                Map.of(
                        "name", "Petr",
                        "subject", "you"
                )
        );
        template = "I am a ${name}, Who are ${subject}?";
    }

    @Ignore
    @Test
    public void whenGenerate() {
        String result = templateGenerator.produce(template, args);
        String expected = "I am a Petr, Who are you?";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenKeyIsNotExistsThanException() {
        Map<String, String> map = Map.of("name", "Petr");
        templateGenerator.produce(template, map);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenUnknownKeyInTemplateThanException() {
        Map<String, String> map = Map.of(
                "name", "Petr",
                "otherKey", "you"
        );
        templateGenerator.produce(template, map);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenUnknownValueThanException() {
        Map<String, String> map = Map.of(
                "name", "Maxim",
                "subject", "you"
        );
        templateGenerator.produce(template, map);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenThereAreExtraKeysInTheMapThanException() {
        Map<String, String> map = Map.of(
                "name", "Petr",
                "subject", "you",
                "otherKey", "otherValue"

        );
        templateGenerator.produce(template, map);
    }
}
