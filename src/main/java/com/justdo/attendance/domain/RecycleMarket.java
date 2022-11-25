package com.justdo.attendance.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class RecycleMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime openHour;
    private LocalDateTime closeHour;

    public RecycleMarket() {
    }

    public RecycleMarket(LocalDateTime openHour, LocalDateTime closeHour) {
        this.openHour = openHour;
        this.closeHour = closeHour;
    }
}
