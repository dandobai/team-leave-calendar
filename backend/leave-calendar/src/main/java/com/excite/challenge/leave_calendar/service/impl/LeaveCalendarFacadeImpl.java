package com.excite.challenge.leave_calendar.service.impl;

import com.excite.challenge.leave_calendar.dto.LeaveRequestDto;
import com.excite.challenge.leave_calendar.dto.OnCallResponseDto;
import com.excite.challenge.leave_calendar.model.LeaveRequest;
import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import com.excite.challenge.leave_calendar.service.LeaveCalendarFacade;
import com.excite.challenge.leave_calendar.service.LeaveRequestService;
import com.excite.challenge.leave_calendar.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LeaveCalendarFacadeImpl implements LeaveCalendarFacade {

    private final TeamMemberService teamMemberService;
    private final LeaveRequestService leaveRequestService;

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequestDto dto) {
        if (dto.startDate().isAfter(dto.endDate())) {
            throw new IllegalArgumentException("A kezdő dátum nem lehet későbbi, mint a végdátum!");
        }

        TeamMember member = teamMemberService.getMemberById(dto.teamMemberId());

        boolean hasOverlap = leaveRequestService.hasOverlappingLeave(
                member.getId(),
                dto.startDate(),
                dto.endDate(),
                List.of(LeaveStatus.PENDING, LeaveStatus.APPROVED),
                dto.requestId()
        );

        if (hasOverlap) {
            throw new IllegalStateException("Ezen időszakra már van aktív vagy elbírálás alatt álló szabadságigénye ennek a tagnak!");
        }

        return leaveRequestService.saveLeaveRequest(member, dto.startDate(), dto.endDate(), dto.reason());
    }

    @Override
    public List<OnCallResponseDto> getOnCallSchedule(int numberOfWeeks) {
        List<OnCallResponseDto> schedule = new ArrayList<>();
        List<TeamMember> members = teamMemberService.getAllMembers();

        if (members.isEmpty()) return schedule;

        LocalDate currentWeekStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        LocalDate baseDate = LocalDate.of(2026, 1, 5);

        for (int i = 0; i < numberOfWeeks; i++) {
            LocalDate weekStart = currentWeekStart.plusWeeks(i);
            LocalDate weekEnd = weekStart.plusDays(6);

            int weekNumber = weekStart.get(weekFields.weekOfWeekBasedYear());
            int year = weekStart.getYear();

            long weeksSinceBase = java.time.temporal.ChronoUnit.WEEKS.between(baseDate, weekStart);

            int memberIndex = (int) (Math.abs(weeksSinceBase) % members.size());
            TeamMember onCallMember = members.get(memberIndex);

            boolean hasConflict = leaveRequestService.hasOverlappingLeave(
                    onCallMember.getId(), weekStart, weekEnd, List.of(LeaveStatus.APPROVED), null
            );

            schedule.add(new OnCallResponseDto(weekNumber, year, weekStart, weekEnd, onCallMember, hasConflict));
        }

        return schedule;
    }
}
