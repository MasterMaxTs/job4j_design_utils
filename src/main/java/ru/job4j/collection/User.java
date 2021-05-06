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
        /*
        * Из распечатки видно, что в словаре два объекта User с
        * одинаковыми ключами имеют различные значения,
        * что видно из ссылок на участок памяти в JVM.
        * Объекты попали в один и тотже бакет, т.к вычисленные
        * автоматически значения key.hashCode() и соответственно
        * hash(key.hashCode()) одинаковы. Метод hashCode() наследуется
        * по умолчанию от класса Object.
        * Как способ разрешения коллизии, у объектов User вызывается
        * метод equals(), унаследованный по умолчанию от класса Object.
        * Согласно default реализации метода equals() у объектов сравниваются ссылки,
        * которые не будут равны!
        * Таким образом, как способ разрешения коллизии методом цепочек,
        * второй объект (второй вызов метода put()) добавляется
        * в качестве следующей ноды в связный список, а не перезаписывает
        * значение первого объекта.
        * Поэтому мы имеем два объекта в бакете, а не один!
        */
    }
}
