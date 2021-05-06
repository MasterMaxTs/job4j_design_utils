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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
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
         * Из распечатки видно, что в словаре содержится один объект User.
         * В этом случае после переопределения методов hashCode() и equals()
         * при добавлении в мапу двух объектов User с одинаковыми ключами,
         * первым делом получается, что они попадают в один бакет, т.к.
         * их хэшкоды равны.
         * Далее с помощью метода equals() происходит сравнение данных объектов
         * по содержимому их полей, они тоже равны. Таким образом, после добавления
         * второго объекта в мапу в бакете останется одна нода, значение поля
         * которой перезапишется значением второго объекта!
        */
    }
}
