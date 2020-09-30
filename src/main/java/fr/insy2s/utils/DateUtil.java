package fr.insy2s.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
    public static String convertToFrenchDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH));
    }
}
