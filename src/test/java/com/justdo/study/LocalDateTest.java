package com.justdo.study;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class LocalDateTest {
    @Test
    void parse테스트() {
        LocalDate date = LocalDate.parse("2022-11-23");
        System.out.println(date);
    }
}
