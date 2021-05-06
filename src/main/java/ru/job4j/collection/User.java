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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                                         && Objects.equals(birthday, user.birthday);
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
         * Два объекта с большей долей вероятности попадут в разные бакеты, т.к вычисленные
         * автоматически значения key.hashCode() и соответственно
         * hash(key.hashCode()) будут разными и индекс бакета
         * i = hash(key.hashCode()) & (table.length - 1) для первого и второго
         * добавленного в мапу объекта будет отличаться.
         * Метод hashCode() наследуется по умолчанию от класса Object, и в основе
         * его алгоритма вычисления значения хэшкода положен генератор случайных чисел.
         * Таким образом,при каждом создании объекта класса User без переопределения метода
         * hashCode() будут получаться разные key.hashCode() и метод equals()
         * вызываться не будет, т.к объекты не равны по хэшкоду!
        */
    }
}
