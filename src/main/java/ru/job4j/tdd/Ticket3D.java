package ru.job4j.tdd;

import java.util.Calendar;
import java.util.Objects;

public class Ticket3D implements Ticket {

    private final Account account;
    private final int row;
    private final int column;
    private final Calendar date;

    public Ticket3D(Account account, int row, int column, Calendar date) {
        this.account = account;
        this.row = row;
        this.column = column;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row && column == ticket3D.column
                && Objects.equals(account, ticket3D.account)
                && Objects.equals(date, ticket3D.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, row, column, date);
    }
}
