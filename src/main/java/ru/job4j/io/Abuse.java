package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс решает задачу удаления из файла запрещенных слов
 */
public class Abuse {

    /**
     * Метод читает текст из файла, удаляет запрещенные слова
     * и записывает измененный текст
     * @param source текстовый файл - источник данных
     * @param target текстовый файл - приёмник отредактированных данных
     * @param words лист запрещенных слов на входе
     * @throws IOException перехват исключения, если файл не найден
     */
    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))
        ) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word))
                    .map(s -> s + " ")
                    .forEach(out::print);
        }
    }
}
