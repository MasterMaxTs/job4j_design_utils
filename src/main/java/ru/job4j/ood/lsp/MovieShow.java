package ru.job4j.ood.lsp;

import java.time.LocalDateTime;

public class MovieShow {

    private String genre;
    private String name;
    private LocalDateTime date;
    protected int ageLimit;
    protected float ticketPrice;

    public MovieShow(String genre, String name, LocalDateTime date,
                     float ticketPrice) {
        validateAgeLimit(genre);
        this.genre = genre;
        this.name = name;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    protected void validateAgeLimit(String genre) {
        if (genre.equalsIgnoreCase("horror")) {
            ageLimit = 16;
        }
        if (genre.equalsIgnoreCase("comedy")) {
            ageLimit = 12;
        }
        if (genre.equalsIgnoreCase("cartoon")) {
            ageLimit = 6;
        }
    }

    public float correctPrice(LocalDateTime date) {
        if (date.getDayOfWeek().getValue() == 7) {
            ticketPrice = 0.8f * ticketPrice;
        }
        if (date.getHour() < 8 || date.getHour() > 22) {
            ticketPrice -= ticketPrice / 3;
        }
        return ticketPrice;
    }
}
