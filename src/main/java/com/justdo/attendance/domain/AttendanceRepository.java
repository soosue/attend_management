package com.justdo.attendance.domain;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByDateAndMemberIdAndRecycleMarketId(LocalDate date, Long memberId, Long recycleMarketId);
}
