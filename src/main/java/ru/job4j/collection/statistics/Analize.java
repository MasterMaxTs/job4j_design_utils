package ru.job4j.collection.statistics;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int diffA = 0;
        int diffC = 0;
        int diffD = 0;
        Map<Integer, User> hm = new HashMap<>();
        Set<Integer> setId = current.stream()
                                          .map(User::getId)
                                          .collect(Collectors.toSet());
        for (User u
                : previous) {
            hm.put(u.getId(), u);
            if (setId.add(u.getId())) {
                diffD++;
            }
        }
        for (User u
                : current) {
            if (hm.get(u.getId()) == null) {
                diffA++;
            }
            if (hm.get(u.getId()) != null
                    && !hm.get(u.getId()).getName().equals(u.getName())) {
                diffC++;
            }
        }
        return new Info(diffA, diffC, diffD);                   // O(3N) ~ O(N)
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}
