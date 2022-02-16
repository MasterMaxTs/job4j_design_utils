package ru.job4j.ood.lsp;

import java.time.LocalDateTime;

public class MovieShowAdult extends MovieShow {
    public MovieShowAdult(String genre, String name, LocalDateTime date,
                          float ticketPrice) {
        super(genre, name, date, ticketPrice);
    }

    /*
     * Нарушен протокол инвариативности в подклассе
     */
    @Override
    protected void validateAgeLimit(String genre) {
        ageLimit = 18;
    }

    /*
    * Нарушен протокол постусловия и производный класс
    * генерирует исключение, которое не описано в базовом классе.
    */
    @Override
    public float correctPrice(LocalDateTime date) {
        throw new UnsupportedOperationException("Not support");
    }
}
