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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static Date setDateAndGet(
            int year, int month, int day, int hour,
                                                    int min, int sec, int milsec) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, min);
        date.set(Calendar.SECOND, sec);
        date.set(Calendar.MILLISECOND, milsec);
        return date.getTime();
    }

    public static void main(String[] args) {
        User user1 = new User(
                "Maxim",
                1,
                setDateAndGet(1986, 0, 17, 3, 15, 50, 700)
        );
        User user2 = new User(
                "Maxim",
                1,
                setDateAndGet(1986, 0, 17, 3, 15, 50, 700)
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
        * значения key.hashCode() и соответственно
        * hash(key.hashCode()) одинаковы.
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
