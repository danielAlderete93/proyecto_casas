package com.estancias.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime convertiCadena(String fecha) {
        LocalDate localDate = LocalDate.parse(fecha, FORMATTER);
        return localDate.atStartOfDay();
    }
}
