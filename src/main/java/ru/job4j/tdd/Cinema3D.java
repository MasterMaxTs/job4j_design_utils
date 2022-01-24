package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    private final List<Session> sessions = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        List<Session> sessionList = new ArrayList<>();
        sessions.forEach(s -> {
                                if (filter.test(s)) {
                                    sessionList.add(s);
                                }
                            }
        );
        return sessionList;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D(account, row, column, date);
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
