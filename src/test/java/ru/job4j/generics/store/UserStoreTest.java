package ru.job4j.generics.store;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {

    @Test
    public void replace() {
        UserStore us = new UserStore();
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        User u3 = new User("3", "Name3", "Surname3", 40);
        us.add(u1);
        us.add(u2);
        us.add(u3);
        assertTrue(us.replace("3", u1));
        assertFalse(us.replace("4", u3));
    }

    @Test
    public void delete() {
        UserStore us = new UserStore();
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        User u3 = new User("3", "Name3", "Surname3", 40);
        us.add(u1);
        us.add(u2);
        us.add(u3);
        assertTrue(us.delete("3"));
        assertFalse(us.delete("4"));
    }

    @Test
    public void findById() {
        UserStore us = new UserStore();
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        us.add(u1);
        us.add(u2);
        assertThat(us.findById("1"), is(u1));
        assertThat(us.findById("2"), is(u2));
        assertNull(us.findById("3"));
    }

    @Test
    public void add() {
        UserStore us = new UserStore();
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        us.add(u1);
        us.add(u2);
        assertThat(us.findById("1").getId(), is("1"));
        assertThat(us.findById("2").getId(), is("2"));
    }
}