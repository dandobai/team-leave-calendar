package com.excite.challenge.leave_calendar.dto;

import java.time.LocalDate;

public record LeaveRequestDto(
        Long requestId,
        Long teamMemberId,
        LocalDate startDate,
        LocalDate endDate,
        String reason
) {}
