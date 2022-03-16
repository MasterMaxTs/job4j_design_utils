package ru.job4j.ood.dip.student;

import java.util.Objects;

public class Student {

    private final int id;
    private final String name;
    private final String surname;
    private final int course;
    private final double avgGrade;

    public Student(int id, String name, String surname, int faculty, double avgGrade) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.course = faculty;
        this.avgGrade = avgGrade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCourse() {
        return course;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", surname='" + surname
                + '\''
                + ", course=" + course
                + ", avgGrade=" + avgGrade
                + '}';
    }
}
