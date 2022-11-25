package com.justdo.attendance.common;

import java.time.LocalDate;

public class LocalDateUtils {

    public static LocalDate of(String date) {
        return LocalDate.parse(date);
    }
}
