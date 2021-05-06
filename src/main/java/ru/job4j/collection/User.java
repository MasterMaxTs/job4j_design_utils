package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Date birthday;

    public User(String name, int children, Date birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    public static void main(String[] args) {
        Calendar db = new GregorianCalendar();
        User user1 = new User(
                "Maxim",
                1,
                new GregorianCalendar(1986, Calendar.JANUARY, 17).getTime()
        );
        User user2 = new User(
                "Maxim",
                1,
                new GregorianCalendar(1986, Calendar.JANUARY, 17).getTime()
        );
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (Map.Entry<User, Object> entry
                : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
