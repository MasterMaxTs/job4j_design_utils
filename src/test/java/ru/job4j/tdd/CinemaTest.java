package ru.job4j.tdd;

import org.junit.Before;
import org.junit.Ignore;
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
        ticket = new Ticket3D();
        session = new Session3D();
    }

    @Ignore
    @Test
    public void whenBuy() {
        Ticket ticket3D = cinema.buy(account, 1, 1, date);
        assertThat(ticket3D, is(ticket));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyTicketWithAnInvalidSeatThanException() {
        Ticket ticket3D = cinema.buy(account, 10, 25, date);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyTicketWithAnInvalidDateThanException() {
        date.set(2020, Calendar.NOVEMBER, 11, 23, 15);
        Calendar invalidDate = date;
        Ticket ticket3D = cinema.buy(account, 10, 2, invalidDate);
    }

    @Ignore
    @Test
    public void whenFind() {
        cinema.add(session);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(session)));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenBuyTicketWithRedeemedSeatThanException() {
        Account account2 = new AccountCinema3D();
        Ticket ticket1 = cinema.buy(account, 10, 2, date);
        Ticket ticket2 = cinema.buy(account2, 10, 2, date);
    }
}