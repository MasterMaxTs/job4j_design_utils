package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Triangle {
    private final boolean isosceles;
    private final float height;
    private final String triangleType;
    private Point[] points = new Point[3];

    public Triangle(boolean isosceles, float height,
                    String triangleType, Point[] points) {
        this.isosceles = isosceles;
        this.height = height;
        this.triangleType = triangleType;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Triangle{"
                + "isosceles=" + isosceles
                + ", height=" + height
                + ", triangleType='" + triangleType
                + '\''
                + ", points=" + Arrays.toString(points)
                + '}';
    }

    public static void main(String[] args) throws IOException {
        final Triangle triangle = new Triangle(
               false,
               3,
               "acute-angled",
                new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)}
        );
        /* Преобразуем объект person в json-строку и запишим в выходной файл. */
        final Gson gson = new GsonBuilder().create();
        Path out = Paths.get("./src/main/java/ru/job4j/serialization/json"
                + "/TriangleSettingsDefault.json");
        Files.writeString(out, gson.toJson(triangle));

        /*Проинициализированный объект json-строкой из файла TriangleSettings
        .json*/
        Path input = Paths.get("./src/main/java/ru/job4j/serialization/json"
                + "/TriangleSettings.json");
        try (BufferedReader reader = new BufferedReader(
                new FileReader(input.toFile())
        )) {
            String jsonString = reader.lines().collect(Collectors.joining());
            final Triangle triangleInit = gson.fromJson(jsonString,
                    Triangle.class);
            System.out.println(triangleInit);
        }

        /* Модифицируем json-строку и инициализируем новый объект*/
        final String triangleJson =
                "{"
                    + "\"isosceles\":true,"
                    + "\"height\": 6.0,"
                    + "\"triangleType\":obtuse,"
                    + "\"points\":[{\"x\":1.0,\"y\":1.0}, {\"x\":5.0,\"y\":4.0}, {\"x\":9.0,\"y\":1.0}]"
                    + "}";
        final Triangle triangleMod = gson.fromJson(triangleJson, Triangle.class);
        System.out.println(triangleMod);
    }
}
