package ru.job4j.ood.lsp;

import java.time.LocalDateTime;

public class MovieShow3D extends MovieShow {

    public MovieShow3D(String genre, String name, LocalDateTime date,
                       float ticketPrice) {
        super(genre, name, date, ticketPrice);
    }

    /*
    * Нарушен протокол постусловия в подклассе
    */
    @Override
    public float correctPrice(LocalDateTime date) {
        if (date.getDayOfWeek().getValue() == 6
                || date.getDayOfWeek().getValue() == 7) {
            ticketPrice = 0.9f * ticketPrice;
        }
        return ticketPrice;
    }

    /*
     * Нарушен протокол предусловия в подклассе
     */
    @Override
    protected void validateAgeLimit(String genre) {
        if (genre.equalsIgnoreCase("horror")) {
            ageLimit = 18;
        }
        if (genre.equalsIgnoreCase("comedy")) {
            ageLimit = 10;
        }
        if (genre.equalsIgnoreCase("cartoon")) {
            ageLimit = 6;
        }
    }
}
