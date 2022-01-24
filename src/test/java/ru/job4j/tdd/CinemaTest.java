package ru.job4j.tdd;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {
    private Calendar date;
    private Cinema cinema;
    private Account account;
    private Ticket ticket;
    private Session session;

    @Before
    public void whenSetUp() {
        date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 15);
        cinema = new Cinema3D();
        account = new AccountCinema3D();
        ticket = new Ticket3D(account, 1, 1, date);
        session = new Session3D();
    }

    @Test
    public void whenBuy() {
        Ticket ticket3D = cinema.buy(account, 1, 1, date);
        assertThat(ticket3D, is(ticket));
    }

    @Test
    public void whenFind() {
        cinema.add(session);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(session)));
    }

    @Test
    public void whenAdd() {
        Cinema3D cinema3D = new Cinema3D();
        cinema3D.add(session);
        assertThat(
                cinema3D.getSessions(), is(List.of(session))
        );
    }

    @Test
    public void whenNoFind() {
        cinema.add(session);
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(List.of()));
    }
}