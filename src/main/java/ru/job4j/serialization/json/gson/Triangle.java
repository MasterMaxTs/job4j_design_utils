package ru.job4j.serialization.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

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
    private final Point[] points;

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

    private void convertObjToJsonFile(Object obj, String filePath) throws IOException {
        final Gson gson = new GsonBuilder().create();
        Path out = Paths.get(filePath);
        Files.writeString(out, gson.toJson(obj));
        System.out.println("Object has been converted in JSON file "
                        + out.toAbsolutePath() + " successfully");
    }

    private Object initObjFromJsonFile(String filePath) throws IOException {
        Object result = null;
        final Gson gson = new GsonBuilder().create();
        Path in = Paths.get(filePath);
        try (BufferedReader reader = new BufferedReader(
                new FileReader(in.toFile())
        )) {
            String jsonString = reader.lines().collect(Collectors.joining());
            result = gson.fromJson(jsonString, Triangle.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Object initObjFromJsonString(String json) {
        Object result = null;
        final Gson gson = new GsonBuilder().create();
        try {
            result = gson.fromJson(json, Triangle.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        final Triangle triangle = new Triangle(
               false,
               3,
               "acute-angled",
                new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)}
        );
        /* Преобразуем объект triangle в json-строку и запишим в выходной файл */
        String out = "./src/main/java/ru/job4j/serialization/json/gson/"
            + "TriangleSettingsDefault.json";
        triangle.convertObjToJsonFile(triangle, out);
        /*Проинициализированный объект json-строкой из файла TriangleSettings.json*/
        String in = "./src/main/java/ru/job4j/serialization/json/gson/"
                + "TriangleSettings.json";
        Triangle triangleInit = (Triangle) triangle.initObjFromJsonFile(in);
        System.out.println(triangleInit);
        /* Модифицируем json-строку и инициализируем новый объект*/
        String triangleJson =
                "{"
                    + "\"isosceles\":true,"
                    + "\"height\": 6.0,"
                    + "\"triangleType\":obtuse,"
                    + "\"points\":[{\"x\":1.0,\"y\":1.0}, {\"x\":5.0,\"y\":4.0}, {\"x\":9.0,\"y\":1.0}]"
                    + "}";
        Triangle triangleMod = (Triangle) triangle.initObjFromJsonString(triangleJson);
        System.out.println(triangleMod);
    }
}
