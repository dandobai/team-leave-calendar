package com.excite.challenge.leave_calendar.service.impl;

import com.excite.challenge.leave_calendar.dto.LeaveRequestDto;
import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import com.excite.challenge.leave_calendar.service.LeaveRequestService;
import com.excite.challenge.leave_calendar.service.TeamMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveCalendarFacadeImplTest {

    @Mock
    private TeamMemberService teamMemberService;

    @Mock
    private LeaveRequestService leaveRequestService;

    @InjectMocks
    private LeaveCalendarFacadeImpl leaveCalendarFacade;

    @Test
    @DisplayName("Új szabadság igénylése meglévő átfedés esetén IllegalStateException-t kell dobjon")
    void shouldThrowExceptionWhenLeaveOverlaps() {
        Long memberId = 1L;
        TeamMember mockMember = new TeamMember(memberId, "Alice");
        LocalDate start = LocalDate.of(2026, 6, 15);
        LocalDate end = LocalDate.of(2026, 6, 20);
        LeaveRequestDto inputDto = new LeaveRequestDto(null, memberId, start, end, "Nyaralás");

        when(teamMemberService.getMemberById(memberId)).thenReturn(mockMember);
        when(leaveRequestService.hasOverlappingLeave(
                memberId, start, end, List.of(LeaveStatus.PENDING, LeaveStatus.APPROVED), null
        )).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            leaveCalendarFacade.createLeaveRequest(inputDto);
        });

        assertEquals("Ezen időszakra már van aktív vagy elbírálás alatt álló szabadságigénye ennek a tagnak!", exception.getMessage());
    }
}

