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
    private LocalDateTime attendTime;
    private LocalDateTime leaveTime;
    private Long memberId;
    private Long recycleMarketId;

    public Attendance() {
    }

    public Attendance(LocalDate date, LocalDateTime attendTime, Long memberId, Long recycleMarketId) {
        this.date = date;
        this.attendTime = attendTime;
        this.memberId = memberId;
        this.recycleMarketId = recycleMarketId;
    }

    public Attendance(LocalDate date, LocalDateTime attendTime, LocalDateTime leaveTime, Long memberId, Long recycleMarketId) {
        this.date = date;
        this.attendTime = attendTime;
        this.leaveTime = leaveTime;
        this.memberId = memberId;
        this.recycleMarketId = recycleMarketId;
    }

    public void leave(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }
}
