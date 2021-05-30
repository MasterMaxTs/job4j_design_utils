package ru.job4j.collection.statistics;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        int diffA = 0;
        int diffC = 0;
        int diffD = 0;
        if (previous.size() == current.size()
                                              && previous.containsAll(current)) {
            return new Info(diffA, diffC, diffD);
        }
        Map<Integer, User> hm0 = new HashMap<>();
        Map<Integer, User> hm1 = new HashMap<>();
        for (User u
                : previous) {
            hm0.put(u.getId(), u);
        }
        for (User u
                : current) {
            hm1.put(u.getId(), u);
        }
        for (User u
                : current) {
            if (!hm0.containsKey(u.getId())) {
                diffA++;
            }
            if (hm0.containsKey(u.getId())
                                         && !hm0.get(u.getId()).getName().equals(u.getName())) {
                diffC++;
            }
        }
        for (User u
                : previous) {
            if (hm1.get(u.getId()) == null) {
                diffD++;
            }
        }
        return new Info(diffA, diffC, diffD);                   // O(4N) ~ O(N)
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
