package ru.job4j.collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class User {
    private String name;
    private String gender;
    private int age;

    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User userSet = (User) o;
        return age == userSet.age && Objects.equals(name, userSet.name)
                                            && Objects.equals(gender, userSet.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name
                + '\''
                + ", gender='" + gender
                + '\''
                + ", age=" + age
                + '}';
    }

    public static void main(String[] args) {
        User u1 = new User("Igor", "male", 20);
        User u2 = new User("Denis", "male", 30);
        User u3 = new User("Olga", "female", 25);
        User u4 = new User("Denis", "male", 30);
        System.out.println("u2.equals(u4): " + u2.equals(u4));
        boolean rsl = u2.hashCode() == u4.hashCode();
        System.out.println("u2.hashCode() = u4.hashCode(): " + rsl);
        Set<User> hs = new HashSet<>(Set.of(u1, u2, u3));
        hs.add(u4);
        for (User u
                : hs) {
            System.out.println(u);
        }
    }
}
