package com.justdo.attendance.service;

import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.justdo.attendance.common.LocalDateUtils;
import com.justdo.attendance.domain.RecycleMarket;
import com.justdo.attendance.domain.RecycleMarketRepository;

@SpringBootTest
@Transactional
class AttendanceServiceTest {
    /**
     * 이 테스트에서 유저는 10000L 로 고정한다.
     * 거점은 20000L 로 고정한다.
     * 그 이유는 다른 유저와 다른 거점에 대해서 테스트를 할 필요가 없다고 판단했기 때문이다.
     * 따라서 private attend() 에는 memberId 와 recycleMarketId 가 고정이다.
     */
    private static final LocalDate NOVEMBER_FIRST = LocalDateUtils.of("2022-11-01");
    private static final Long MEMBER_ID_10000 = 10000L;
    private Long recycleMarketId;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private RecycleMarketRepository recycleMarketRepository;

    @BeforeEach
    void init() {
        RecycleMarket recycleMarket = new RecycleMarket();
        recycleMarketRepository.save(recycleMarket);
        recycleMarketId = recycleMarket.getId();
    }

    @Test
    void 첫출근가능확인() {
        LocalDateTime attendTime = parse("2022-11-01T10:15:30");

        boolean canAttend = attendanceService.canAttend(NOVEMBER_FIRST, attendTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(canAttend).isTrue();
    }

    @Test
    void 출근가능확인_출근인정시간에_출근할수있다() {
        LocalDateTime attendTime = parse("2022-11-01T10:15:30");

        boolean canAttend = attendanceService.canAttend(NOVEMBER_FIRST, attendTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(canAttend).isTrue();
    }

    @Test
    void 첫출근() {
        LocalDateTime attendTime = parse("2022-11-01T10:15:30");

        Long attendId = attendanceService.attend(NOVEMBER_FIRST, attendTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(attendId).isNotNull();
    }

    @Test
    void 퇴근가능확인_출근했으면_퇴근가능하다() {
        attend(NOVEMBER_FIRST, parse("2022-11-01T10:15:30"));
        LocalDateTime leaveTime = parse("2022-11-01T18:15:30");

        boolean canLeave = attendanceService.canLeave(NOVEMBER_FIRST, leaveTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(canLeave).isTrue();
    }

    @Test
    void 퇴근가능확인_출근기록_없으면_퇴근할_수없다() {
        LocalDateTime leaveTime = parse("2022-11-01T18:15:30");

        boolean canLeave = attendanceService.canLeave(NOVEMBER_FIRST, leaveTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(canLeave).isFalse();
    }

    @Test
    void 출근가능확인_출근한상태에서는_출근할수없다() {
        attend(NOVEMBER_FIRST, parse("2022-11-01T10:15:30"));
        LocalDateTime attendTime = parse("2022-11-01T10:16:30");

        boolean canAttend = attendanceService.canAttend(NOVEMBER_FIRST, attendTime, MEMBER_ID_10000, recycleMarketId);

        assertThat(canAttend).isFalse();
    }

    @Test
    void 출근한상태에서는_출근할수없다() {
        attendanceService.attend(NOVEMBER_FIRST, parse("2022-11-01T10:15:30"), MEMBER_ID_10000, recycleMarketId);

        LocalDateTime attendTime = parse("2022-11-01T10:16:30");

        assertThatThrownBy(() ->
                attendanceService.attend(NOVEMBER_FIRST, attendTime, MEMBER_ID_10000, recycleMarketId)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private void attend(LocalDate date, LocalDateTime attendTime) {
        attendanceService.attend(date, attendTime, MEMBER_ID_10000, recycleMarketId);
    }
}
