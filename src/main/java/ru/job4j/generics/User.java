package ru.job4j.generics;

public class User extends Base {
    private String name;
    private String surname;
    private int age;
    public User(String id, String name, String surname, int age) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + getId()
                + "name='" + name
                + '\''
                + ", surname='" + surname
                + '\''
                + ", age=" + age
                + '}';
    }
}
