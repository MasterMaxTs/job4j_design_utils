package ru.job4j.collection.set;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User2 {
    private String name;
    private String gender;
    private int age;

    public User2(String name, String gender, int age) {
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
        User2 user2 = (User2) o;
        return age == user2.age && Objects.equals(name, user2.name)
                                                        && Objects.equals(gender, user2.gender);
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
        User2 u1 = new User2("Igor", "male", 20);
        User2 u2 = new User2("Denis", "male", 30);
        User2 u3 = new User2("Olga", "female", 25);
        User2 u4 = new User2("Denis", "male", 30);
        System.out.println("u2.equals(u4): " + u2.equals(u4));
        boolean rsl = u2.hashCode() == u4.hashCode();
        System.out.println("u2.hashCode() = u4.hashCode(): " + rsl);
        Set<User2> hs = new HashSet<>(Set.of(u1, u2, u3));
        hs.add(u4);
        for (User2 u
                : hs) {
            System.out.println(u);
        }
    }
}
