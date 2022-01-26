package ru.job4j.tdd.template;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    private Generator templateGenerator;
    private Map<String, String> args;

    @Before
    public void whenSetUp() {
        templateGenerator = new TemplateEngine();
        args = new HashMap<>(
                Map.of(
                        "key1", "value1",
                        "key2", "value2",
                        "key3", "value3"
                )
        );
    }

    @Ignore
    @Test
    public void whenGenerate() {
        String template = "I am a ${key1}, Who are ${key2}?";
        String result = templateGenerator.produce(template, args);
        String expected = String.format(
                template.replaceAll("\\$\\{[^}]+}", "%s"),
                args.get("key1"), args.get("key2")
        );
        assertEquals(expected, result);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenKeyIsNotExistsThanException() {
        String template = "I am a ${}, Who are ${key2}?";
        String result = templateGenerator.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenUnknownKeyInTemplateThanException() {
        String template = "I am a ${key4}, Who are ${key2}?";
        String result = templateGenerator.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenThereAreExtraKeysInTheMapThanException() {

    }
}
