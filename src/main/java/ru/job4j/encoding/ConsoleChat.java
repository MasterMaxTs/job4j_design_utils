package ru.job4j.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String logPath;
    private final String botAnswers;
    public static final String OUT = "закончить";
    public static final String STOP = "стоп";
    public static final String CONTINUE = "продолжить";

    public ConsoleChat(String logPath, String botAnswers) {
        this.logPath = logPath;
        this.botAnswers = botAnswers;
    }

    private void run() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's go chat with Bot! ");
        String input = CONTINUE;
        String answer;
        while (!input.equalsIgnoreCase(OUT)) {
            System.out.println("User: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(OUT)) {
                builder.append("User: ").append(input).append("\n");
                break;
            }
            builder.append("User: ").append(input).append("\n");
            if (input.equalsIgnoreCase(STOP)) {
                do {
                    System.out.println("User: ");
                    input = scanner.nextLine();
                    builder.append("User: ").append(input).append("\n");
                    System.out.println("User: ");
                } while (!input.equalsIgnoreCase(CONTINUE));
            }
            answer = readPhrases().get(random.nextInt(readPhrases().size()));
            builder.append("Bot: ").append(answer).append("\n");
            System.out.println("User: " + input);
            System.out.println("Bot:" + answer);
        }
        String[] arr = builder.toString().split("\n");
        List<String> log = new ArrayList<>(Arrays.asList(arr));
        saveLog(log);
        System.out.println("Fludilka finished!!!");
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            phrases = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(logPath, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String logPath = ".\\src\\main\\java\\ru\\job4j\\encoding\\ConsoleLog.txt";
        String botAnswers = ".\\src\\main\\java\\ru\\job4j\\encoding\\BotAnswers.txt";
        ConsoleChat cc = new ConsoleChat(logPath, botAnswers);
        cc.run();
    }
}
