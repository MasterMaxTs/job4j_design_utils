package ru.job4j.serialization.json.org.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    public boolean isIsosceles() {
        return isosceles;
    }

    public float getHeight() {
        return height;
    }

    public String getTriangleType() {
        return triangleType;
    }

    public Point[] getPoints() {
        return points;
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

    private JSONObject toJsonObject(Triangle triangle) {
        JSONObject jsonObject = new JSONObject();
        List<Point> pointsList = Arrays.asList(triangle.getPoints());
        JSONArray jsonPoints = new JSONArray(pointsList);
        /* JSONObject напрямую методом put */
        jsonObject.put("isosceles", triangle.isIsosceles());
        jsonObject.put("height", triangle.getHeight());
        jsonObject.put("triangleType", triangle.getTriangleType());
        jsonObject.put("points", jsonPoints);
        return jsonObject;
    }

    private String toJson(Triangle triangle) {
        return new JSONObject(triangle).toString();
    }

    public static void main(String[] args) throws IOException {
        final Triangle triangle = new Triangle(
               false,
               3,
               "acute-angled",
                new Point[]{new Point(0, 0), new Point(1, 1), new Point(2, 2)});
        System.out.println(triangle.toJsonObject(triangle));
        System.out.println(triangle.toJson(triangle));
    }
}
