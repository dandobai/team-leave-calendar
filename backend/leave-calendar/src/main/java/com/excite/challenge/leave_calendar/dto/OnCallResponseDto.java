package com.excite.challenge.leave_calendar.dto;

import com.excite.challenge.leave_calendar.model.TeamMember;
import java.time.LocalDate;

public record OnCallResponseDto(
        int weekNumber,
        int year,
        LocalDate weekStart,
        LocalDate weekEnd,
        TeamMember onCallMember,
        boolean hasConflict
) {}
