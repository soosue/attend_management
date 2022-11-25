package com.justdo.attendance.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdo.attendance.domain.Attendance;
import com.justdo.attendance.domain.AttendanceRepository;

@Service
@Transactional(readOnly = true)
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public boolean canAttend(LocalDate date, LocalDateTime attendTime, Long memberId, Long recycleMarketId) {
        Optional<Attendance> optional = attendanceRepository.findByDateAndMemberIdAndRecycleMarketId(date, memberId, recycleMarketId);

        if (optional.isEmpty()) {
            return true;
        }

        return false;
    }

    public Long attend(LocalDate date, LocalDateTime attendTime, Long memberId, Long recycleMarketId) {
        Attendance attendance;
        if (canAttend(date, attendTime, memberId, recycleMarketId)) {
            attendance = new Attendance(date, attendTime, memberId, recycleMarketId);
            attendanceRepository.save(attendance);

            return attendance.getId();
        }
        throw new IllegalArgumentException("출석할 수 없습니다");
    }

    public boolean canLeave(LocalDate date, LocalDateTime leaveTime, Long memberId, Long recycleMarketId) {
        Optional<Attendance> optional = attendanceRepository.findByDateAndMemberIdAndRecycleMarketId(date, memberId, recycleMarketId);

        if (optional.isEmpty()) {
            return false;
        }

        optional.get().leave(leaveTime);

        return true;
    }
}
