package com.justdo.attendance.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.justdo.attendance.domain.Attendance;
import com.justdo.attendance.domain.AttendanceRepository;

@SpringBootTest
public class AttendanceRepositoryTest {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    void Attendance생성테스트() {
        Attendance attendance = new Attendance();
        attendanceRepository.save(attendance);

        assertThat(attendance.getId()).isNotNull();
    }
}
