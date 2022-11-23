package com.justdo.attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Long memberId;
    private LocalDateTime attendTime;
    private LocalDateTime leaveTime;
    private Long recycleMarketId;

    public Attendance() {
    }

    public Attendance(LocalDate date, Long memberId, LocalDateTime attendTime, LocalDateTime leaveTime, Long recycleMarketId) {
        this.date = date;
        this.memberId = memberId;
        this.attendTime = attendTime;
        this.leaveTime = leaveTime;
        this.recycleMarketId = recycleMarketId;
    }
}
