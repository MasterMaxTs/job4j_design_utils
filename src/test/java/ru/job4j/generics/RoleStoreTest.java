package ru.job4j.generics;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.is;

public class RoleStoreTest {

    @Test
    public void replace() {
        RoleStore<User> rs = new RoleStore<>();
        rs.setStore(new UserStore());
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        User u3 = new User("3", "Name3", "Surname3", 40);
        rs.add(u1);
        rs.add(u2);
        assertTrue(rs.replace("2", u3));
        assertFalse(rs.replace("4", u3));
    }

    @Test
    public void delete() {
        RoleStore<User> rs = new RoleStore<>();
        rs.setStore(new UserStore());
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        User u3 = new User("3", "Name3", "Surname3", 40);
        rs.add(u1);
        rs.add(u2);
        rs.add(u3);
        assertTrue(rs.delete("3"));
        assertFalse(rs.delete("4"));
    }

    @Test
    public void findById() {
        RoleStore<User> rs = new RoleStore<>();
        rs.setStore(new UserStore());
        User u1 = new User("1", "Name1", "Surname1", 20);
        User u2 = new User("2", "Name2", "Surname2", 30);
        rs.add(u1);
        rs.add(u2);
        assertThat(rs.findById("2"), is(u2));
        assertNull(rs.findById("3"));

    }
}