package ru.job4j.ood.ocp.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TextGenerator {
    /*
    * Класс генерирует текст в виде строки из заданного списка слов,
    * размещенного к файле по url.
    * Чтобы использовать этот класс для генерации последовательности чисел,
    * нужно реализовать дополнительный метод, т.е изменить, что нарушит
    * принцип Открытости-Закрытости.
    * В наследовании нет смысла:
    * - во первых, будущий класс DigitGenerator не может являться потомком
    * класса TextGenerator (условие is A не выполнимо);
    * - во-вторых, для генерации чисел нет надобности в наследовании
    * конструктора класса родителя, в котором в обязательном порядке должен
    * быть указан url файла, в котором должны быть записаны числа.   
    * */

    private List<String> words;
    private final String url;

    public TextGenerator(String url) {
        this.url = url;
        getWords();
    }

    public String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(random.nextInt(words.size()))).append(" ");
        }
        return sb.toString();
    }

    private void getWords() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(url)
        )) {
            words = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "./src/main/java/ru/job4j/ood/ocp"
                + "/generator/words.txt";
        TextGenerator tg = new TextGenerator(url);
        System.out.println(tg.generate());
    }
}
