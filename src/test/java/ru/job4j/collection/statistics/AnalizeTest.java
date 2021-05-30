package ru.job4j.collection.statistics;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.collection.statistics.Analize.User;
import ru.job4j.collection.statistics.Analize.Info;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    private List<User> previous = new ArrayList<>();
    private List<User> current = new ArrayList<>();

    @Before
    public void setUp() {
        User u01 = new User(1, "Maxim");
        User u02 = new User(2, "Sasha");
        User u03 = new User(3, "Vasya");
        User u04 = new User(4, "Fedor");
        User u05 = new User(5, "Yuriy");
        User u06 = new User(6, "Petr");
        previous = List.of(u01, u02, u03, u04, u05, u06);
        User u11 = new User(7, "Ilya");
        User u12 = new User(8, "Nikolay");
        User u13 = new User(2, "Sergey");
        User u14 = new User(4, "Egor");
        current = List.of(u11, u12, u13, u14);
    }

    @Test
    public void whenDiff() {
        Analize stat = new Analize();
        Info rsl = stat.diff(previous, current);
        assertThat(rsl.getAdded(), is(2));
        assertThat(rsl.getChanged(), is(2));
        assertThat(rsl.getDeleted(), is(4));
    }

    @Test
    public void whenNotDiffThan0() {
        previous = List.of(
                new User(1, "Maxim"),
                new User(2, "Sasha")
        );
        current = previous;
        Analize stat = new Analize();
        Info rsl = stat.diff(previous, current);
        assertThat(rsl.getAdded(), is(0));
        assertThat(rsl.getChanged(), is(0));
        assertThat(rsl.getDeleted(), is(0));
    }
}